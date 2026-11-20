package delivery.model;

public class PedidoCriado extends PedidoEvento{

    public PedidoCriado(Pedido pedido){
        super(pedido, "PEDIDO CRIADO", TipoEvento.PEDIDO_CRIADO);
    }
}
