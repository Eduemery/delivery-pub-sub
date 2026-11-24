package delivery.model.factories;

import delivery.model.Pedido;
import delivery.model.PedidoAceitoEvento;
import delivery.model.PedidoEvento;

public class PedidoAceitoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoAceitoEvento(pedido);
    }
}
