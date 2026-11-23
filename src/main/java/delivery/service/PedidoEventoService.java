package delivery.service;

import delivery.model.Pedido;
import delivery.model.PedidoAceitoEventoFactory;
import delivery.model.PedidoEntregaConcluidaEventoFactory;
import delivery.model.PedidoEntregaEmAndamentoEventoFactory;
import delivery.model.PedidoEvento;
import delivery.model.IPedidoEventoFactory;
import delivery.model.PedidoNovoEventoFactory;
import delivery.model.PedidoPreparadoEventoFactory;
import delivery.model.SituacaoPedido;
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
    }

    public PedidoEvento alterarEvento(Pedido pedido){
        IPedidoEventoFactory factory = factories.get(pedido.getStatus());
        return factory.criarEvento(pedido);
    }
}
