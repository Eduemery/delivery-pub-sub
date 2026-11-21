package delivery.model;

public class PedidoNovoEvento extends PedidoEvento{

    public PedidoNovoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " REALIZADO", TipoEvento.NOVO_PEDIDO);
    }
}
