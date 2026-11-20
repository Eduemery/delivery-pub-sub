package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ClienteSubscriber implements Subscriber<PedidoEvento> {
    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        if(evento.getEvento() == TipoEvento.PEDIDO_EM_PREPARACAO || evento.getEvento() == TipoEvento.PEDIDO_SAIU_PARA_ENTREGA || evento.getEvento() == TipoEvento.PEDIDO_CRIADO){
            System.out.println("Cliente recebeu " + evento);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicação encerrada");
    }
}
