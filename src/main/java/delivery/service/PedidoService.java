package delivery.service;

import delivery.model.Ipublishers;
import delivery.model.Pedido;
import delivery.model.SituacaoPedido;

public class PedidoService {
    private Pedido pedido;
    private Ipublishers publisher;
    private PedidoEventoService pedidoEventoService;

    public PedidoService(Pedido pedido, Ipublishers publisher, PedidoEventoService pedidoEventoService){
        this.pedido = pedido;
        this.publisher = publisher;
        this.pedidoEventoService = pedidoEventoService;
    }

    public void novoPedido(){
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
    }

    public void aceitarPedido(){
        pedido.setStatus(SituacaoPedido.PEDIDO_EM_PREPARACAO);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
    }

    public void concluirPrearacao(){
        pedido.setStatus(SituacaoPedido.PEDIDO_PRONTO_RETIRADA);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
    }

    public void buscarPedido(){
        pedido.setStatus(SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
    }

    public void concluirEntrega(){
        pedido.setStatus(SituacaoPedido.PEDIDO_ENTREGUE);
        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
        publisher.encerrarPublicacao();
    }
}
