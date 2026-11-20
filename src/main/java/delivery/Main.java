package delivery;

import delivery.model.CalculadoraPedido;
import delivery.model.Cliente;
import delivery.model.Endereco;
import delivery.model.Pedido;
import delivery.model.PedidoEmPreparacao;
import delivery.model.PedidoEntregue;
import delivery.model.PedidoSaiuEntrega;
import delivery.model.Produto;
import delivery.publisher.PedidoPublisher;
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
        RestauranteSubscriber restauranteSubscriber = new RestauranteSubscriber(latch);
        EntregadorSubscriber entregadorSubscriber = new EntregadorSubscriber(latch);
        ClienteSubscriber clienteSubscriber = new ClienteSubscriber(latch);

        publisher.subscribe(restauranteSubscriber);
        publisher.subscribe(entregadorSubscriber);
        publisher.subscribe(clienteSubscriber);

        Cliente cliente = new Cliente("EDUARDO EMERY");
        Endereco endereco = new Endereco("BRASIL", "ESPÍRITO SANTO", "ALEGRE", "29500-000", "CENTRO", "RUA PADRE ANCHIETA", 165);
        Produto item1 = new Produto("PIZZA", 1, 79.99);
        Produto item2 = new Produto("REFRIGERANTE LATA", 1, 9.00);
        List<Produto> itens = List.of(item1, item2);
        Pedido pedido1 = new Pedido("PEDIDO 1", LocalDateTime.now(), cliente, endereco, itens);
        CalculadoraPedido calculadoraPedido = new CalculadoraPedido();
        pedido1.setValorTotal(calculadoraPedido.calcular(pedido1));
        System.out.println(pedido1.toString());


        publisher.publicarEvento(pedido1.getStatus());
        pedido1.setStatus(new PedidoEmPreparacao(pedido1));
        publisher.publicarEvento(pedido1.getStatus());
        pedido1.setStatus(new PedidoSaiuEntrega(pedido1));
        publisher.publicarEvento(pedido1.getStatus());
        pedido1.setStatus(new PedidoEntregue(pedido1));
        publisher.publicarEvento(pedido1.getStatus());
        publisher.close();

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
