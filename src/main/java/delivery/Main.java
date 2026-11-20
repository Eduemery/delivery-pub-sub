package delivery;

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

public class Main {
    public static void main(String[] args){

        PedidoPublisher publisher = new PedidoPublisher();
        RestauranteSubscriber restauranteSubscriber = new RestauranteSubscriber();
        EntregadorSubscriber entregadorSubscriber = new EntregadorSubscriber();
        ClienteSubscriber clienteSubscriber = new ClienteSubscriber();

        publisher.subscribe(restauranteSubscriber);
        publisher.subscribe(entregadorSubscriber);
        publisher.subscribe(clienteSubscriber);


        Cliente cliente = new Cliente("EDUARDO EMERY");
        Endereco endereco = new Endereco("BRASIL", "ESPÍRITO SANTO", "ALEGRE", "29500-000", "CENTRO", "RUA PADRE ANCHIETA", 165);
        Produto item1 = new Produto("PIZZA", 1, 79.99);
        Produto item2 = new Produto("REFRIGERANTE LATA", 1, 9.00);
        List<Produto> itens = List.of(item1, item2);
        Pedido pedido1 = new Pedido("PEDIDO 1", LocalDateTime.now(), cliente, endereco, itens);

        publisher.publicarEvento(pedido1.getStatus());
        pedido1.setStatus(new PedidoEmPreparacao(pedido1));
        publisher.publicarEvento(pedido1.getStatus());
        pedido1.setStatus(new PedidoSaiuEntrega(pedido1));
        publisher.publicarEvento(pedido1.getStatus());
        pedido1.setStatus(new PedidoEntregue(pedido1));
        publisher.publicarEvento(pedido1.getStatus());

        restauranteSubscriber.onComplete();
        clienteSubscriber.onComplete();
        entregadorSubscriber.onComplete();

    }
}
