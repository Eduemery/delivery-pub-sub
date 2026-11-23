package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.TipoEvento;
import delivery.service.PedidoService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class RestauranteSubscriber implements Subscriber<PedidoEvento> {

    private CountDownLatch latch;
    private PedidoService pedidoService;

    public RestauranteSubscriber(CountDownLatch latch, PedidoService pedidoService){
        this.latch = latch;
        this.pedidoService = pedidoService;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(PedidoEvento evento) {
        if(evento.getEvento() == TipoEvento.NOVO_PEDIDO){
            System.out.println("Restaurante recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
            pedidoService.aceitarPedido();
        }
        if(evento.getEvento() == TipoEvento.PEDIDO_ACEITO_RESTAURANTE){
            pedidoService.concluirPrearacao();
        }

        if(evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO){
            System.out.println("Restaurante recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
        }

        if (evento.getEvento() == TipoEvento.ENTREGA_CONCLUIDA){
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
