package delivery.publisher;

import delivery.model.PedidoEvento;

public interface IPublishers {
    public void publicarEvento(PedidoEvento evento);
    public void encerrarPublicacao();
}
