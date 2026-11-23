package delivery.model;

public class PedidoPreparadoEvento extends PedidoEvento{

    public PedidoPreparadoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " PRONTO PARA RETIRADA NO RESTAURANTE", TipoEvento.PREPARACAO_FINALIZADA);
    }
}
