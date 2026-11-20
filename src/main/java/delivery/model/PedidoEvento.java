package delivery.model;

import java.time.LocalDateTime;

public class PedidoEvento {
    private Pedido pedido;
    private LocalDateTime horaEvento;
    private String mensagemEvento;
    private TipoEvento evento;

    public PedidoEvento(Pedido pedido, String mensagem, TipoEvento evento){
        this.pedido = pedido;
        this.horaEvento = LocalDateTime.now();
        this.mensagemEvento = mensagem;
        this.evento = evento;
    }

    public TipoEvento getEvento(){
        return this.evento;
    }

}
