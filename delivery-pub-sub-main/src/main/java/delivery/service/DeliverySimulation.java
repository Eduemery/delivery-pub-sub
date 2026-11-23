package delivery.service;

import delivery.model.Entregador;
import delivery.model.Pedido;
import delivery.model.Restaurante;
import delivery.publisher.PedidoPublisher;
import delivery.subscriber.ClienteSubscriber;
import delivery.subscriber.EntregadorSubscriber;
import delivery.subscriber.RestauranteSubscriber;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DeliverySimulation {

    public void executar() {
        PedidoFactory factory = new PedidoFactory();
        List<Pedido> pedidos = factory.criarListaPedidos(20);
        List<Restaurante> restaurantes = factory.getRestaurantes();
        List<Entregador> entregadores = factory.getEntregadores();

        System.out.println("RESTAURANTES CADASTRADOS: " + restaurantes);
        System.out.println("ENTREGADORES CADASTRADOS: " + entregadores);

        int totalSubscribers = 1 + restaurantes.size() + entregadores.size();
        CountDownLatch latch = new CountDownLatch(totalSubscribers);
        PedidoPublisher publisher = new PedidoPublisher();

        publisher.subscribe(new ClienteSubscriber(latch));
        for (Restaurante restaurante : restaurantes) {
            publisher.subscribe(new RestauranteSubscriber(latch, restaurante));
        }
        for (Entregador entregador : entregadores) {
            publisher.subscribe(new EntregadorSubscriber(latch, entregador));
        }

        try {
            SimulacaoPedidos simulacao = new SimulacaoPedidos(publisher, entregadores);
            simulacao.iniciarSimulacao(pedidos);
        } finally {
            publisher.close();
            try {
                if (!latch.await(1, TimeUnit.MINUTES)) {
                    System.out.println("Aviso: nem todos os subscribers finalizaram no tempo esperado.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
