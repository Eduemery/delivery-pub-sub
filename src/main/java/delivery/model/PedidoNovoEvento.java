package delivery.model;

import delivery.model.eNum.TipoEvento;

public class PedidoNovoEvento extends PedidoEvento{

    public PedidoNovoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " REALIZADO", TipoEvento.NOVO_PEDIDO);
    }
}
