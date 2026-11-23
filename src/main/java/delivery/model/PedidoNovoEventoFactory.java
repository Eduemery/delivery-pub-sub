package delivery.model;

public class PedidoNovoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoNovoEvento(pedido);
    }
}
