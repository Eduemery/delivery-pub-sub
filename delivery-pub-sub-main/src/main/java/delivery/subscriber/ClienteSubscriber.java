package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;

public class ClienteSubscriber implements Flow.Subscriber<PedidoEvento> {

    private final CountDownLatch latch;

    public ClienteSubscriber(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        if (evento.getEvento() == TipoEvento.PEDIDO_ACEITO_RESTAURANTE
                || evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO
                || evento.getEvento() == TipoEvento.ENTREGA_CONCLUIDA
                || evento.getEvento() == TipoEvento.PEDIDO_CANCELADO) {
            System.out.println("\n[CLIENTE]"
                    + "\nPedido: " + evento.getNumeroPedido()
                    + "\nRestaurante: " + evento.getRestauranteResponsavel()
                    + "\nMensagem: " + evento.getMensagemEvento()
                    + "\nHorario: " + evento.getHoraEvento());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Erro no ClienteSubscriber: " + throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicacao encerrada com os clientes.");
        latch.countDown();
    }
}
