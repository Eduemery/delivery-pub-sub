package delivery.service;

import delivery.model.Entregador;
import delivery.model.Pedido;
import delivery.model.SituacaoPedido;
import delivery.publisher.PedidoPublisher;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulacaoPedidos {

    private final PedidoPublisher publisher;
    private final PedidoEventoService eventoService;
    private final PedidoService pedidoService;
    private final List<Entregador> entregadores;
    private final AtomicInteger proximoEntregador;

    public SimulacaoPedidos(PedidoPublisher publisher, List<Entregador> entregadores) {
        this.publisher = publisher;
        this.eventoService = new PedidoEventoService();
        this.pedidoService = new PedidoService();
        this.entregadores = entregadores;
        this.proximoEntregador = new AtomicInteger();
    }

    public void iniciarSimulacao(List<Pedido> pedidos) {
        System.out.println("\nINICIANDO SIMULACAO DE DELIVERY\n");
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<?>> tarefas = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            tarefas.add(executor.submit(() -> processarPedido(pedido)));
        }
        executor.shutdown();

        try {
            for (Future<?> tarefa : tarefas) {
                tarefa.get();
            }
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
                throw new IllegalStateException("Tempo limite da simulacao excedido.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Simulacao interrompida.", e);
        } catch (ExecutionException e) {
            executor.shutdownNow();
            throw new IllegalStateException("Falha ao processar um pedido.", e.getCause());
        }

        exibirRelatorio(pedidos);
        System.out.println("\nSIMULACAO FINALIZADA\n");
    }

    private void processarPedido(Pedido pedido) {
        System.out.println("Novo pedido: " + pedido);
        publicarSituacao(pedido);
        pausar();

        pedidoService.iniciarPreparacao(pedido);
        publicarSituacao(pedido);
        pausar();

        int numero = Integer.parseInt(pedido.getNumero());
        switch (numero % 5) {
            case 0 -> {
                prepararParaRetirada(pedido);
                iniciarEntrega(pedido);
                concluirEntrega(pedido);
            }
            case 1 -> cancelarPedido(pedido);
            case 2 -> prepararParaRetirada(pedido);
            case 3 -> {
                prepararParaRetirada(pedido);
                iniciarEntrega(pedido);
            }
            default -> System.out.println("Pedido " + pedido.getNumero() + " continua em preparacao.");
        }
    }

    private void prepararParaRetirada(Pedido pedido) {
        atribuirEntregador(pedido);
        pedidoService.marcarPronto(pedido);
        publicarSituacao(pedido);
        pausar();
    }

    private void atribuirEntregador(Pedido pedido) {
        if (entregadores.isEmpty()) {
            throw new IllegalStateException("Nao ha entregadores cadastrados.");
        }
        int indice = proximoEntregador.getAndIncrement() % entregadores.size();
        Entregador entregador = entregadores.get(indice);
        pedido.setEntregador(entregador);
        System.out.println("Entregador " + entregador.getNome() + " atribuido ao pedido " + pedido.getNumero());
    }

    private void iniciarEntrega(Pedido pedido) {
        pedidoService.iniciarEntrega(pedido);
        publicarSituacao(pedido);
        pausar();
    }

    private void concluirEntrega(Pedido pedido) {
        pedidoService.concluirEntrega(pedido);
        publicarSituacao(pedido);
    }

    private void cancelarPedido(Pedido pedido) {
        pedidoService.cancelarPedido(pedido);
        publicarSituacao(pedido);
    }

    private void publicarSituacao(Pedido pedido) {
        publisher.publicarEvento(eventoService.alterarEvento(pedido));
    }

    private void pausar() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Processamento do pedido interrompido.", e);
        }
    }

    private void exibirRelatorio(List<Pedido> pedidos) {
        Map<SituacaoPedido, Integer> porSituacao = new LinkedHashMap<>();
        Map<String, Integer> porRestaurante = new LinkedHashMap<>();
        Map<String, Integer> porEntregador = new LinkedHashMap<>();

        for (SituacaoPedido situacao : SituacaoPedido.values()) {
            porSituacao.put(situacao, 0);
        }
        for (Pedido pedido : pedidos) {
            porSituacao.merge(pedido.getStatus(), 1, Integer::sum);
            porRestaurante.merge(pedido.getRestaurante().getNome(), 1, Integer::sum);
            if (pedido.getEntregador() != null) {
                porEntregador.merge(pedido.getEntregador().getNome(), 1, Integer::sum);
            }
        }

        System.out.println("\n========== RELATORIO FINAL ==========");
        System.out.println("Total de pedidos: " + pedidos.size());
        System.out.println("Pedidos por situacao:");
        porSituacao.forEach((situacao, total) -> System.out.println("- " + situacao + ": " + total));
        System.out.println("Pedidos por restaurante:");
        porRestaurante.forEach((nome, total) -> System.out.println("- " + nome + ": " + total));
        System.out.println("Pedidos atribuidos por entregador:");
        porEntregador.forEach((nome, total) -> System.out.println("- " + nome + ": " + total));
        System.out.println("=====================================");
    }
}
