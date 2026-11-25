package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.eNum.TipoEvento;
import delivery.service.IPedidoService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class EntregadorSubscriber implements Subscriber<PedidoEvento> {

    private CountDownLatch latch;
    private IPedidoService pedidoService;

    public EntregadorSubscriber(CountDownLatch latch, IPedidoService pedidoService){
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
            pedidoService.entregarPedido(evento.getNumeroPedido());
        }
        if(evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO){
            /*try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }*/
            pedidoService.concluirPedido(evento.getNumeroPedido());
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
