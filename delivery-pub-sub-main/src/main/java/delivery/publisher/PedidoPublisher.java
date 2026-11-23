
package delivery.publisher;

import delivery.model.PedidoEvento;
import java.util.concurrent.SubmissionPublisher;

public class PedidoPublisher extends SubmissionPublisher<PedidoEvento> {

    public void publicarEvento(PedidoEvento evento) {
        submit(evento);
    }
}
