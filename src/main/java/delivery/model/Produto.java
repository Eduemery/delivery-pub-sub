package delivery.model;

public class Produto {
    private String descricao;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;

    public Produto(String descricao, Integer quantidade, Double valorUnitario){
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = quantidade * valorUnitario;
    }

    public String getDescricao(){
        return this.descricao;
    }
    public Integer getQuantidade(){
        return this.quantidade;
    }
    public Double getValorUnitario(){
        return this.valorUnitario;
    }
    public Double getValorTotal(){return this.valorTotal;}

    @Override
    public String toString(){
        return "Produto: " + getDescricao();
    }

}
