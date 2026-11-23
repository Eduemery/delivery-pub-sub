package delivery.service;

import delivery.model.Pedido;
import delivery.model.SituacaoPedido;

public class PedidoService {

    public void iniciarPreparacao(Pedido pedido) {
        validarSituacao(pedido, SituacaoPedido.PEDIDO_REALIZADO);
        pedido.setStatus(SituacaoPedido.PEDIDO_EM_PREPARACAO);
    }

    public void marcarPronto(Pedido pedido) {
        validarSituacao(pedido, SituacaoPedido.PEDIDO_EM_PREPARACAO);
        if (pedido.getEntregador() == null) {
            throw new IllegalStateException("O pedido precisa de um entregador antes de ficar pronto.");
        }
        pedido.setStatus(SituacaoPedido.PEDIDO_PRONTO_RETIRADA);
    }

    public void iniciarEntrega(Pedido pedido) {
        validarSituacao(pedido, SituacaoPedido.PEDIDO_PRONTO_RETIRADA);
        pedido.setStatus(SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA);
    }

    public void concluirEntrega(Pedido pedido) {
        validarSituacao(pedido, SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA);
        pedido.setStatus(SituacaoPedido.PEDIDO_ENTREGUE);
    }

    public void cancelarPedido(Pedido pedido) {
        SituacaoPedido situacao = pedido.getStatus();
        if (situacao != SituacaoPedido.PEDIDO_REALIZADO
                && situacao != SituacaoPedido.PEDIDO_EM_PREPARACAO) {
            throw new IllegalStateException("Cancelamento nao permitido para pedidos nesta situacao.");
        }
        pedido.setStatus(SituacaoPedido.PEDIDO_CANCELADO);
    }

    private void validarSituacao(Pedido pedido, SituacaoPedido esperada) {
        if (pedido.getStatus() != esperada) {
            throw new IllegalStateException("Transicao invalida para o pedido " + pedido.getNumero() + ".");
        }
    }
}
