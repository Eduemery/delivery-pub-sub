package delivery.model;

public class PedidoAceitoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoAceitoEvento(pedido);
    }
}
