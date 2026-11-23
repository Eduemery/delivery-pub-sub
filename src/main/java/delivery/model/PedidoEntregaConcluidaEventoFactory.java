package delivery.model;

public class PedidoEntregaConcluidaEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoEntregaConcluidaEvento(pedido);
    }
}
