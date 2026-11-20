package delivery.model;

public class PedidoEntregue extends PedidoEvento{

    public PedidoEntregue(Pedido pedido){
        super(pedido, "PEDIDO ENTREGUE", TipoEvento.PEDIDO_ENTREGUE);
    }
}
