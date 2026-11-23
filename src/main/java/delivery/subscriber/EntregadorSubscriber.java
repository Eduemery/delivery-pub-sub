package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;
import delivery.service.PedidoService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class EntregadorSubscriber implements Subscriber<PedidoEvento> {

    private CountDownLatch latch;
    private PedidoService pedidoService;

    public EntregadorSubscriber(CountDownLatch latch, PedidoService pedidoService){
        this.latch = latch;
        this.pedidoService = pedidoService;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        if(evento.getEvento() == TipoEvento.PREPARACAO_FINALIZADA){
            System.out.println("Entregador recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
            pedidoService.buscarPedido();
        }
        if(evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO){
            pedidoService.concluirEntrega();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Comunicação encerrada com o entregador");
        this.latch.countDown();
    }
}
