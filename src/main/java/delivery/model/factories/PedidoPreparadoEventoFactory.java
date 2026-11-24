package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoEvento;
import delivery.model.PedidoPreparadoEvento;

public class PedidoPreparadoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoPreparadoEvento(pedido);
    }
}
