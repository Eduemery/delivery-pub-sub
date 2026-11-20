package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class EntregadorSubscriber implements Subscriber<PedidoEvento> {

    public CountDownLatch latch;

    public EntregadorSubscriber(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        if(evento.getEvento() == TipoEvento.PEDIDO_ENTREGUE){
            System.out.println("Entregador recebeu " + evento);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicação encerrada");
        this.latch.countDown();
    }
}
