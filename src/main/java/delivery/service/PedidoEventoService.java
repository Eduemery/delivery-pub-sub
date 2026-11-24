package delivery.service;

import delivery.model.Pedido;
import delivery.model.factories.PedidoAceitoEventoFactory;
import delivery.model.factories.PedidoCanceladoEventoFactory;
import delivery.model.factories.PedidoEntregaConcluidaEventoFactory;
import delivery.model.factories.PedidoEntregaEmAndamentoEventoFactory;
import delivery.model.PedidoEvento;
import delivery.model.factories.IPedidoEventoFactory;
import delivery.model.factories.PedidoNovoEventoFactory;
import delivery.model.factories.PedidoPreparadoEventoFactory;
import delivery.model.eNum.SituacaoPedido;
import java.util.HashMap;
import java.util.Map;

public class PedidoEventoService {
    private Map<SituacaoPedido, IPedidoEventoFactory> factories;

    public PedidoEventoService(){
        factories = new HashMap<>();

        factories.put(
                SituacaoPedido.PEDIDO_REALIZADO,
                new PedidoNovoEventoFactory()
        );
        factories.put(
                SituacaoPedido.PEDIDO_EM_PREPARACAO,
                new PedidoAceitoEventoFactory()
        );
        factories.put(
                SituacaoPedido.PEDIDO_PRONTO_RETIRADA,
                new PedidoPreparadoEventoFactory()
        );
        factories.put(
                SituacaoPedido.PEDIDO_SAIU_PARA_ENTREGA,
                new PedidoEntregaEmAndamentoEventoFactory()
        );
        factories.put(
                SituacaoPedido.PEDIDO_ENTREGUE,
                new PedidoEntregaConcluidaEventoFactory()
        );
        factories.put(
                SituacaoPedido.PEDIDO_CANCELADO,
                new PedidoCanceladoEventoFactory()
        );
    }

    public PedidoEvento alterarEvento(Pedido pedido){
        IPedidoEventoFactory factory = factories.get(pedido.getStatus());
        return factory.criarEvento(pedido);
    }
}
