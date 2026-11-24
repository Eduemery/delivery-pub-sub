package delivery.model;

import delivery.model.eNum.TipoEvento;

public class PedidoCanceladoEvento extends PedidoEvento{
    public PedidoCanceladoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " CANCELADO ", TipoEvento.CANCELAMENTO_PEDIDO);
    }
}
