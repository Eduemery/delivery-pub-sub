package delivery.model;

import delivery.model.eNum.TipoEvento;

public class PedidoAceitoEvento extends PedidoEvento{

    public PedidoAceitoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " ACEITO PELO RESTAURANTE ", TipoEvento.PEDIDO_ACEITO_RESTAURANTE);
    }
}
