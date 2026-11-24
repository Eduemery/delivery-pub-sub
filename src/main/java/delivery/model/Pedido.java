package delivery.model;

import delivery.model.eNum.SituacaoPedido;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private String numero;
    private LocalDateTime emissao;
    private Cliente cliente;
    private Double valorTotal;
    private Endereco enderecoEntrega;
    private List<Produto> itens;
    private SituacaoPedido status;
    private Restaurante restaurante;
    private Entregador entregador;

    public Pedido(String numero, LocalDateTime emissao, Cliente cliente, Endereco enderecoEntrega, List<Produto> itens, Restaurante restaurante, Entregador entregador){
        this.numero = numero;
        this.emissao = emissao;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = itens;
        this.status = SituacaoPedido.PEDIDO_REALIZADO;
        this.restaurante = restaurante;
        this.entregador = entregador;
    }

    public String getNumero(){
        return this.numero;
    }
    public LocalDateTime getEmissao(){
        return this.emissao;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public Double getValorTotal(){
        return this.valorTotal;
    }
    public Endereco getEnderecoEntrega(){
        return this.enderecoEntrega;
    }
    public List<Produto> getItens(){
        return this.itens;
    }
    public SituacaoPedido getStatus() {
        return status;
    }
    public void setStatus(SituacaoPedido status) {
        this.status = status;
    }
    public void setValorTotal(Double valorTotal){
        this.valorTotal = valorTotal;
    }
    public Restaurante getRestaurante() {
        return restaurante;
    }
    public Entregador getEntregador() {
        return entregador;
    }
    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    @Override
    public String toString(){
        return "Pedido: " + getNumero() + " no valor de: " + getValorTotal() + " criado em: " + getEmissao();
    }

}
