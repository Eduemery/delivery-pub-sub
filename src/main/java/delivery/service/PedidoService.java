package delivery.service;

import delivery.model.Pedido;
import delivery.model.SituacaoPedido;
import delivery.publisher.PedidoPublisher;

public class PedidoService {

    public void alterarSitucaoPedido(Pedido pedido, PedidoPublisher publisher, PedidoEventoService pedidoEventoService){

        publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
        switch (pedido.getStatus()){
            case PEDIDO_REALIZADO:
                    pedido.setStatus(SituacaoPedido.PEDIDO_EM_PREPARACAO);
                    publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
            case PEDIDO_EM_PREPARACAO:
                    pedido.setStatus(SituacaoPedido.PEDIDO_PRONTO_RETIRADA);
                    publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
            case PEDIDO_PRONTO_RETIRADA:
                    pedido.setStatus(SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA);
                    publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
            case PEDIDO_SAIU_PARA_ENTREGA:
                    pedido.setStatus(SituacaoPedido.PEDIDO_ENTREGUE);
                    publisher.publicarEvento(pedidoEventoService.alterarEvento(pedido));
        }
    }
}
