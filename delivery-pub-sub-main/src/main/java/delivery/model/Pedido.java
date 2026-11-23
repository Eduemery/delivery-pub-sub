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
    private Restaurante restaurante;
    private Entregador entregador;

    public Pedido(String numero, LocalDateTime emissao, Cliente cliente, Endereco enderecoEntrega, List<Produto> itens) {
        this(numero, emissao, cliente, enderecoEntrega, itens,
                new Restaurante("R00", "RESTAURANTE NAO INFORMADO"));
    }

    public Pedido(String numero, LocalDateTime emissao, Cliente cliente, Endereco enderecoEntrega,
            List<Produto> itens, Restaurante restaurante) {
        this.numero = numero;
        this.emissao = emissao;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = List.copyOf(itens);
        this.restaurante = restaurante;
        this.status = SituacaoPedido.PEDIDO_REALIZADO;
    }

    public String getNumero() {
        return this.numero;
    }

    public LocalDateTime getEmissao() {
        return this.emissao;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Double getValorTotal() {
        return this.valorTotal;
    }

    public Endereco getEnderecoEntrega() {
        return this.enderecoEntrega;
    }

    public List<Produto> getItens() {
        return this.itens;
    }

    public SituacaoPedido getStatus() {
        return status;
    }

    public void setStatus(SituacaoPedido status) {
        this.status = status;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public String getRestauranteResponsavel() {
        return restaurante.getNome();
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    @Override
    public String toString() {
        String nomeEntregador = entregador == null
                ? "A DEFINIR"
                : entregador.getNome();

        return "Pedido: " + getNumero()
                + " | Restaurante: " + getRestaurante().getNome()
                + " | Entregador: " + nomeEntregador
                + " | Cliente: " + getCliente().getNome()
                + " | Valor: " + getValorTotal()
                + " | Criado em: " + getEmissao();
    }
}
