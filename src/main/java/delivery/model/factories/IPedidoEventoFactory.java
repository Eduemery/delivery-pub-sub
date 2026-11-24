package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoEvento;

public interface IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido);
}
