package delivery.model;

public class PedidoSaiuEntrega extends PedidoEvento{

    public PedidoSaiuEntrega(Pedido pedido){
        super(pedido, "PEDIDO SAIU PARA ENTREGA", TipoEvento.PEDIDO_SAIU_PARA_ENTREGA);
    }
}
