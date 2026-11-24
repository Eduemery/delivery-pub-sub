package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoEvento;
import delivery.model.PedidoNovoEvento;

public class PedidoNovoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoNovoEvento(pedido);
    }
}
