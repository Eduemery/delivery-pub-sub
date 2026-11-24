package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoEntregaEmAndamentoEvento;
import delivery.model.PedidoEvento;

public class PedidoEntregaEmAndamentoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoEntregaEmAndamentoEvento(pedido);
    }
}
