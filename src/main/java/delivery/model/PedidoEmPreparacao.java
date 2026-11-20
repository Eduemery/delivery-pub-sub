package delivery.model;

public class PedidoEmPreparacao extends PedidoEvento{

    public PedidoEmPreparacao(Pedido pedido){
        super(pedido, "PEDIDO EM PREPARAÇÃO", TipoEvento.PEDIDO_EM_PREPARACAO);
    }
}
