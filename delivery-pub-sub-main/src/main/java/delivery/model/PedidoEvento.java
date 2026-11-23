package delivery.model;

import java.time.LocalDateTime;

public class PedidoEvento {

    private final String numeroPedido;
    private final LocalDateTime horaEvento;
    private final String mensagemEvento;
    private final TipoEvento evento;
    private final SituacaoPedido situacao;
    private final String codigoRestaurante;
    private final String restauranteResponsavel;
    private final String codigoEntregador;
    private final String entregadorResponsavel;

    public PedidoEvento(Pedido pedido, String mensagem, TipoEvento evento) {
        this.numeroPedido = pedido.getNumero();
        this.horaEvento = LocalDateTime.now();
        this.mensagemEvento = mensagem;
        this.evento = evento;
        this.situacao = pedido.getStatus();
        this.codigoRestaurante = pedido.getRestaurante().getCodigo();
        this.restauranteResponsavel = pedido.getRestaurante().getNome();
        this.codigoEntregador = pedido.getEntregador() == null
                ? null : pedido.getEntregador().getCodigo();
        this.entregadorResponsavel = pedido.getEntregador() == null
                ? "NAO ATRIBUIDO" : pedido.getEntregador().getNome();
    }

    public TipoEvento getEvento() { return evento; }
    public String getNumeroPedido() { return numeroPedido; }
    public LocalDateTime getHoraEvento() { return horaEvento; }
    public String getMensagemEvento() { return mensagemEvento; }
    public SituacaoPedido getSituacao() { return situacao; }
    public String getCodigoRestaurante() { return codigoRestaurante; }
    public String getRestauranteResponsavel() { return restauranteResponsavel; }
    public String getCodigoEntregador() { return codigoEntregador; }
    public String getEntregadorResponsavel() { return entregadorResponsavel; }
}
