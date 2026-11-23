package delivery.model;

public class PedidoPreparadoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoPreparadoEvento(pedido);
    }
}
