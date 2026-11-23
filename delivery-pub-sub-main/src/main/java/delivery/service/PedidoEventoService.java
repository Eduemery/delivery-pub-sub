package delivery.service;

import delivery.model.Pedido;
import delivery.model.PedidoAceitoEvento;
import delivery.model.PedidoCanceladoEvento;
import delivery.model.PedidoEntregaConcluidaEvento;
import delivery.model.PedidoEntregaEmAndamentoEvento;
import delivery.model.PedidoEvento;
import delivery.model.PedidoNovoEvento;
import delivery.model.PedidoPreparadoEvento;
import delivery.model.SituacaoPedido;

public class PedidoEventoService {

    public PedidoEvento alterarEvento(Pedido pedido) {
        if (pedido.getStatus() == SituacaoPedido.PEDIDO_REALIZADO) {
            return new PedidoNovoEvento(pedido);
        }
        if (pedido.getStatus() == SituacaoPedido.PEDIDO_EM_PREPARACAO) {
            return new PedidoAceitoEvento(pedido);
        }
        if (pedido.getStatus() == SituacaoPedido.PEDIDO_PRONTO_RETIRADA) {
            return new PedidoPreparadoEvento(pedido);
        }
        if (pedido.getStatus() == SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA) {
            return new PedidoEntregaEmAndamentoEvento(pedido);
        }
        if (pedido.getStatus() == SituacaoPedido.PEDIDO_ENTREGUE) {
            return new PedidoEntregaConcluidaEvento(pedido);
        }
        if (pedido.getStatus() == SituacaoPedido.PEDIDO_CANCELADO) {
            return new PedidoCanceladoEvento(pedido);
        }
        throw new IllegalStateException("Situacao do pedido nao reconhecida.");
    }
}
