package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class RestauranteSubscriber implements Subscriber<PedidoEvento> {

    private CountDownLatch latch;

    public RestauranteSubscriber(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        if(evento.getEvento() == TipoEvento.NOVO_PEDIDO || evento.getEvento() == TipoEvento.ENTREGA_CONCLUIDA || evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO){
            System.out.println("Restaurante recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicação encerrada com o restaurante");
        this.latch.countDown();
    }
}
