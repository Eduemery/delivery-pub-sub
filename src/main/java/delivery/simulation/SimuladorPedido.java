package delivery.simulation;

import delivery.model.Pedido;
import delivery.publisher.PedidoPublisher;
import delivery.service.PedidoEventoService;
import delivery.service.PedidoService;
import delivery.subscriber.ClienteSubscriber;
import delivery.subscriber.EntregadorSubscriber;
import delivery.subscriber.RestauranteSubscriber;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SimuladorPedido {
    private CountDownLatch latch = new CountDownLatch(3);
    private PedidoPublisher publisher;
    private PedidoEventoService pedidoEventoService;
    private PedidoService pedidoService;
    private Inicializador inicializador;
    private RestauranteSubscriber restauranteSubscriber;
    private EntregadorSubscriber entregadorSubscriber;
    private ClienteSubscriber clienteSubscriber;
    private Dados dadosPedido;

    public SimuladorPedido(){
        this.publisher = new PedidoPublisher();
        this.pedidoEventoService = new PedidoEventoService();
        this.pedidoService = new PedidoService(this.publisher, this.pedidoEventoService);
        this.inicializador = new Inicializador(publisher, pedidoEventoService, pedidoService);
        this.restauranteSubscriber = new RestauranteSubscriber(latch, pedidoService);
        this.entregadorSubscriber = new EntregadorSubscriber(latch, pedidoService);
        this.clienteSubscriber = new ClienteSubscriber(latch, pedidoService);
        this.dadosPedido = new Dados();
    }

    public void simular(){
        publisher.subscribe(clienteSubscriber);
        publisher.subscribe(restauranteSubscriber);
        publisher.subscribe(entregadorSubscriber);

        List<Pedido> pedidos = dadosPedido.criarPedidos();

        System.out.println("----------------------------------------START--------------------------------------");
        inicializador.iniciar(pedidos);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("----------------------------------------END----------------------------------------");

        for(Pedido p : pedidos){
            pedidoService.estadoFinalPedido(p);
        }
    }
}
