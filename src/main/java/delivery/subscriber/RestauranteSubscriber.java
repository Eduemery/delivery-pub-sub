package delivery.subscriber;

import delivery.model.PedidoEvento;
import delivery.model.eNum.TipoEvento;
import delivery.service.IPedidoService;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class RestauranteSubscriber implements Subscriber<PedidoEvento> {

    private CountDownLatch latch;
    private IPedidoService pedidoService;

    public RestauranteSubscriber(CountDownLatch latch, IPedidoService pedidoService){
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
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            Random random = new Random();
            int a = random.nextInt(5);
            System.out.println("Restaurante recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
            if(a == 0){
                System.out.println("Restaurante cancelou o pedido: " + evento.getNumeroPedido() + " - " + evento.getHoraEvento());
                pedidoService.cancelarPedido(evento.getNumeroPedido());
            }
            else{
                pedidoService.prepararPedido(evento.getNumeroPedido());
            }
        }
        if(evento.getEvento() == TipoEvento.PEDIDO_ACEITO_RESTAURANTE){
            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }*/
            pedidoService.retirarPedido(evento.getNumeroPedido());
        }

        if(evento.getEvento() == TipoEvento.ENTREGA_EM_ANDAMENTO){
            System.out.println("Restaurante recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
        }

        if (evento.getEvento() == TipoEvento.ENTREGA_CONCLUIDA){
            System.out.println("Restaurante recebeu: " + evento.getMensagemEvento() + " - " + evento.getHoraEvento());
        }
        if(evento.getEvento() == TipoEvento.CANCELAMENTO_PEDIDO){
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
