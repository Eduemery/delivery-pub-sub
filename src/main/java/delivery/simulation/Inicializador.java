package delivery.simulation;

import delivery.model.Pedido;
import delivery.publisher.IPublishers;
import delivery.service.PedidoEventoService;
import delivery.service.PedidoService;

import java.util.List;

public class Inicializador {
    private PedidoEventoService pedidoEventoService;
    private PedidoService pedidoService;

    public Inicializador(IPublishers publisher, PedidoEventoService pedidoEventoService, PedidoService pedidoService){
        this.pedidoEventoService = pedidoEventoService;
        this.pedidoService = pedidoService;
    }

    public void iniciar(List<Pedido> pedidos){
        pedidoService.definirTotalPedidos(pedidos.size());
        for(Pedido p : pedidos){
            pedidoService.putPedido(p);
        }
        for(Pedido p : pedidos){
            pedidoService.realizarPedido(p.getNumero());
        }
    }
}
