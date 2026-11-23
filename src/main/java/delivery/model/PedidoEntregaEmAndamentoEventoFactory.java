package delivery.model;

public class PedidoEntregaEmAndamentoEventoFactory implements IPedidoEventoFactory {
    public PedidoEvento criarEvento(Pedido pedido){
        return new PedidoEntregaEmAndamentoEvento(pedido);
    }
}
