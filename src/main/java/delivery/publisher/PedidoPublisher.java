package delivery.publisher;

import delivery.model.PedidoEvento;
import java.util.concurrent.SubmissionPublisher;

public class PedidoPublisher extends SubmissionPublisher<PedidoEvento> implements IPublishers {

    public void publicarEvento(PedidoEvento evento){
        submit(evento);
    }
    public void encerrarPublicacao(){
        close();
    }
}
