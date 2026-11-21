package delivery.service;

import delivery.model.Pedido;
import delivery.model.PedidoAceitoEvento;
import delivery.model.PedidoEntregaConcluidaEvento;
import delivery.model.PedidoEntregaEmAndamentoEvento;
import delivery.model.PedidoEvento;
import delivery.model.PedidoNovoEvento;
import delivery.model.PedidoPreparadoEvento;
import delivery.model.SituacaoPedido;

public class PedidoEventoService {

    public PedidoEvento alterarEvento(Pedido pedido){
        PedidoEvento evento = null;
        if(pedido.getStatus() == SituacaoPedido.PEDIDO_REALIZADO){
            evento = new PedidoNovoEvento(pedido);
        } else if(pedido.getStatus() == SituacaoPedido.PEDIDO_EM_PREPARACAO){
            evento = new PedidoAceitoEvento(pedido);
        } else if(pedido.getStatus() == SituacaoPedido.PEDIDO_PRONTO_RETIRADA){
            evento = new PedidoPreparadoEvento(pedido);
        } else if(pedido.getStatus() == SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA){
            evento = new PedidoEntregaEmAndamentoEvento(pedido);
        } else if(pedido.getStatus() == SituacaoPedido.PEDIDO_ENTREGUE){
            evento = new PedidoEntregaConcluidaEvento(pedido);
        }
        return evento;
    }
}
