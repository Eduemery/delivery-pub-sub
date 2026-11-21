package delivery.model;

public class PedidoAceitoEvento extends PedidoEvento{

    public PedidoAceitoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " ACEITO PELO RESTAURANTE ", TipoEvento.PEDIDO_ACEITO_RESTAURANTE);
    }
}
