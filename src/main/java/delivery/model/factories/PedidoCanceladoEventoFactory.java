package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoCanceladoEvento;
import delivery.model.PedidoEvento;

public class PedidoCanceladoEventoFactory implements IPedidoEventoFactory{
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoCanceladoEvento(pedido);
    }
}
