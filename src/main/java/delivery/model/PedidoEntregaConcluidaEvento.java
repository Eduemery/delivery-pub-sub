package delivery.model;

public class PedidoEntregaConcluidaEvento extends PedidoEvento{

    public PedidoEntregaConcluidaEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " ENTREGUE", TipoEvento.ENTREGA_CONCLUIDA);
    }
}
