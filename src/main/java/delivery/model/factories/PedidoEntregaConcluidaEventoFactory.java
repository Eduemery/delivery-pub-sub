package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoEntregaConcluidaEvento;
import delivery.model.PedidoEvento;

public class PedidoEntregaConcluidaEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoEntregaConcluidaEvento(pedido);
    }
}
