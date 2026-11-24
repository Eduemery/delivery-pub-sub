package delivery.model;

import delivery.model.eNum.TipoEvento;

import java.time.LocalDateTime;

public class PedidoEvento {
    private String numeroPedido;
    private LocalDateTime horaEvento;
    private String mensagemEvento;
    private TipoEvento evento;

    public PedidoEvento(Pedido pedido, String mensagem, TipoEvento evento){
        this.numeroPedido = pedido.getNumero();
        this.horaEvento = LocalDateTime.now();
        this.mensagemEvento = mensagem;
        this.evento = evento;
    }

    public TipoEvento getEvento(){
        return this.evento;
    }
    public String getNumeroPedido(){
        return this.numeroPedido;
    }
    public LocalDateTime getHoraEvento(){
        return this.horaEvento;
    }
    public String getMensagemEvento(){
        return this.mensagemEvento;
    }
}
