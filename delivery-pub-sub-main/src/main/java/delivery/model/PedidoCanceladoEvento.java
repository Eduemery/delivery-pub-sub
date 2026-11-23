
package delivery.model;

public class PedidoCanceladoEvento extends PedidoEvento {

    public PedidoCanceladoEvento(Pedido pedido) {
        super(
                pedido,
                "PEDIDO " + pedido.getNumero() + " CANCELADO",
                TipoEvento.PEDIDO_CANCELADO
        );
    }
}
