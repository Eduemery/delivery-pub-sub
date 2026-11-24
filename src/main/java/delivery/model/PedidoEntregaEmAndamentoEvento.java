package delivery.model;

import delivery.model.eNum.TipoEvento;

public class PedidoEntregaEmAndamentoEvento  extends PedidoEvento{

    public PedidoEntregaEmAndamentoEvento(Pedido pedido){
        super(pedido, "PEDIDO " + pedido.getNumero() + " SAIU PARA ENTREGA", TipoEvento.ENTREGA_EM_ANDAMENTO);
    }
}
