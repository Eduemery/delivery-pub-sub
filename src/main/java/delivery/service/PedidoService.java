package delivery.service;

import delivery.model.eNum.SituacaoPedido;
import delivery.publisher.IPublishers;
import delivery.model.Pedido;

import java.util.HashMap;
import java.util.Map;

public class PedidoService implements IPedidoService{
    private Map<String, Pedido> pedidos;
    private IPublishers publisher;
    private PedidoEventoService pedidoEventoService;
    private int totalPedidos;
    private boolean publicacaoEncerrada = false;

    public PedidoService(IPublishers publisher, PedidoEventoService pedidoEventoService){
        this.publisher = publisher;
        this.pedidoEventoService = pedidoEventoService;
        this.pedidos = new HashMap<>();
    }

    public void putPedido(Pedido pedido){
        pedidos.put(pedido.getNumero(), pedido);
    }

    public synchronized void realizarPedido(String numeroPedido){
        if(pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_REALIZADO || pedidos.get(numeroPedido).getStatus() == SituacaoPedido.PEDIDO_CANCELADO)
            return;
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedidos.get(numeroPedido)));
    }

    public synchronized void prepararPedido(String numeroPedido){
        if(pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_REALIZADO || pedidos.get(numeroPedido).getStatus() == SituacaoPedido.PEDIDO_CANCELADO)
            return;
        pedidos.get(numeroPedido).setStatus(SituacaoPedido.PEDIDO_EM_PREPARACAO);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedidos.get(numeroPedido)));
    }
    public synchronized void retirarPedido(String numeroPedido){
        if(pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_EM_PREPARACAO || pedidos.get(numeroPedido).getStatus() == SituacaoPedido.PEDIDO_CANCELADO)
            return;
        pedidos.get(numeroPedido).setStatus(SituacaoPedido.PEDIDO_PRONTO_RETIRADA);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedidos.get(numeroPedido)));
    }
    public synchronized void entregarPedido(String numeroPedido){
        if(pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_PRONTO_RETIRADA || pedidos.get(numeroPedido).getStatus() == SituacaoPedido.PEDIDO_CANCELADO)
            return;
        pedidos.get(numeroPedido).setStatus(SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedidos.get(numeroPedido)));
    }
    public synchronized void concluirPedido(String numeroPedido){
        if(pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA || pedidos.get(numeroPedido).getStatus() == SituacaoPedido.PEDIDO_CANCELADO)
            return;
        finalizarPedido(pedidos.get(numeroPedido), SituacaoPedido.PEDIDO_ENTREGUE);
    }
    public synchronized void cancelarPedido(String numeroPedido){
        if(pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_REALIZADO && pedidos.get(numeroPedido).getStatus() != SituacaoPedido.PEDIDO_EM_PREPARACAO)
            return;
        finalizarPedido(pedidos.get(numeroPedido), SituacaoPedido.PEDIDO_CANCELADO);
    }

    public void definirTotalPedidos(int total){
        this.totalPedidos = total;
    }
    private synchronized void finalizarPedido(Pedido pedido, SituacaoPedido situacaoFinal) {

        Pedido pedidoAtual = pedidos.get(pedido.getNumero());

        if (pedidoAtual == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        if (pedidoAtual.getStatus() == SituacaoPedido.PEDIDO_ENTREGUE ||
                pedidoAtual.getStatus() == SituacaoPedido.PEDIDO_CANCELADO) {
            return;
        }

        pedidoAtual.setStatus(situacaoFinal);

        publisher.publicarEvento(
                pedidoEventoService.alterarEvento(pedidoAtual)
        );

        if (!publicacaoEncerrada && todosPedidosFinalizados()) {
            publicacaoEncerrada = true;
            System.out.println("Todos os pedidos estão em seus estados finais");
            publisher.encerrarPublicacao();
        }
    }
    public synchronized boolean todosPedidosFinalizados() {

        if (pedidos.size() != totalPedidos || totalPedidos == 0) {
            return false;
        }

        for (Pedido pedido : pedidos.values()) {
            if (pedido.getStatus() != SituacaoPedido.PEDIDO_ENTREGUE &&
                    pedido.getStatus() != SituacaoPedido.PEDIDO_CANCELADO) {
                return false;
            }
        }
        return true;
    }

    public void estadoFinalPedido(Pedido pedido){
        System.out.println("\n" + "PEDIDO: " + pedido.getNumero() + " | " + " SITUAÇÃO: " + pedido.getStatus() + "\n");
    }
}
