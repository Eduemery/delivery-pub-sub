package delivery.model;

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

    public Pedido(String numero, LocalDateTime emissao, Cliente cliente, Endereco enderecoEntrega, List<Produto> itens){
        this.numero = numero;
        this.emissao = emissao;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = itens;
        this.status = SituacaoPedido.PEDIDO_REALIZADO;
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

    @Override
    public String toString(){
        return "Pedido: " + getNumero() + " no valor de: " + getValorTotal() + " criado em: " + getEmissao();
    }

}
