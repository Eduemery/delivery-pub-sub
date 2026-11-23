package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.Restaurante;
import delivery.model.TipoEvento;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;

public class RestauranteSubscriber implements Flow.Subscriber<PedidoEvento> {

    private final CountDownLatch latch;
    private final Restaurante restaurante;

    public RestauranteSubscriber(CountDownLatch latch, Restaurante restaurante) {
        this.latch = latch;
        this.restaurante = restaurante;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        boolean eventoRelevante = evento.getEvento() == TipoEvento.NOVO_PEDIDO
                || evento.getEvento() == TipoEvento.PEDIDO_ACEITO_RESTAURANTE
                || evento.getEvento() == TipoEvento.PREPARACAO_FINALIZADA
                || evento.getEvento() == TipoEvento.PEDIDO_CANCELADO;

        if (restaurante.getCodigo().equals(evento.getCodigoRestaurante()) && eventoRelevante) {
            System.out.println("\n[RESTAURANTE " + restaurante.getNome() + "]"
                    + "\nPedido: " + evento.getNumeroPedido()
                    + "\nMensagem: " + evento.getMensagemEvento()
                    + "\nHorario: " + evento.getHoraEvento());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Erro no RestauranteSubscriber: " + throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicacao encerrada com " + restaurante.getNome() + ".");
        latch.countDown();
    }
}
