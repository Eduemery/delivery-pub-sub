package delivery.subscriber;

import delivery.model.Entregador;
import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;

public class EntregadorSubscriber implements Flow.Subscriber<PedidoEvento> {

    private final CountDownLatch latch;
    private final Entregador entregador;

    public EntregadorSubscriber(CountDownLatch latch, Entregador entregador) {
        this.latch = latch;
        this.entregador = entregador;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        boolean eventoRelevante = evento.getEvento() == TipoEvento.PREPARACAO_FINALIZADA
                || evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO
                || evento.getEvento() == TipoEvento.ENTREGA_CONCLUIDA;

        if (entregador.getCodigo().equals(evento.getCodigoEntregador()) && eventoRelevante) {
            System.out.println("\n[ENTREGADOR " + entregador.getNome() + "]"
                    + "\nPedido: " + evento.getNumeroPedido()
                    + "\nRestaurante: " + evento.getRestauranteResponsavel()
                    + "\nMensagem: " + evento.getMensagemEvento()
                    + "\nHorario: " + evento.getHoraEvento());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Erro no EntregadorSubscriber: " + throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicacao encerrada com " + entregador.getNome() + ".");
        latch.countDown();
    }
}
