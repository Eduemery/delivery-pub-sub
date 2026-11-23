package delivery.publisher;

import delivery.model.Ipublishers;
import delivery.model.PedidoEvento;
import java.util.concurrent.SubmissionPublisher;

public class PedidoPublisher extends SubmissionPublisher<PedidoEvento> implements Ipublishers {

    public void publicarEvento(PedidoEvento evento){
        submit(evento);
    }
    public void encerrarPublicacao(){
        close();
    }
}
