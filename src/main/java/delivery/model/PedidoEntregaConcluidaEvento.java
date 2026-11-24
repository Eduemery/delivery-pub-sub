package delivery.model;

import delivery.model.eNum.TipoEvento;

public class PedidoEntregaConcluidaEvento extends PedidoEvento{

    public PedidoEntregaConcluidaEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " ENTREGUE", TipoEvento.ENTREGA_CONCLUIDA);
    }
}
