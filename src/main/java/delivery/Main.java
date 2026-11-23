package delivery;

import delivery.model.CalculadoraPedido;
import delivery.model.Cliente;
import delivery.model.Endereco;
import delivery.model.Pedido;
import delivery.model.Produto;
import delivery.publisher.PedidoPublisher;
import delivery.service.PedidoEventoService;
import delivery.service.PedidoService;
import delivery.subscriber.ClienteSubscriber;
import delivery.subscriber.EntregadorSubscriber;
import delivery.subscriber.RestauranteSubscriber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args){

        CountDownLatch latch = new CountDownLatch(3);
        PedidoPublisher publisher = new PedidoPublisher();

        Cliente cliente = new Cliente("EDUARDO EMERY");
        Endereco endereco = new Endereco("BRASIL", "ESPÍRITO SANTO", "ALEGRE", "29500-000", "CENTRO", "RUA PADRE ANCHIETA", 165);
        Produto item1 = new Produto("PIZZA", 1, 79.99);
        Produto item2 = new Produto("REFRIGERANTE LATA", 1, 9.00);
        List<Produto> itens = List.of(item1, item2);
        Pedido pedido1 = new Pedido("1", LocalDateTime.now(), cliente, endereco, itens);

        CalculadoraPedido calculadoraPedido = new CalculadoraPedido();
        pedido1.setValorTotal(calculadoraPedido.calcular(pedido1));

        PedidoEventoService pedidoEventoService = new PedidoEventoService();
        PedidoService pedidoService = new PedidoService(pedido1, publisher, pedidoEventoService);

        RestauranteSubscriber restauranteSubscriber = new RestauranteSubscriber(latch, pedidoService);
        EntregadorSubscriber entregadorSubscriber = new EntregadorSubscriber(latch, pedidoService);
        ClienteSubscriber clienteSubscriber = new ClienteSubscriber(latch);

        publisher.subscribe(restauranteSubscriber);
        publisher.subscribe(entregadorSubscriber);
        publisher.subscribe(clienteSubscriber);
        System.out.println(pedido1.toString());

        pedidoService.novoPedido();

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
