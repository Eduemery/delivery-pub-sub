package delivery.model;

public class Endereco {
    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String logradouro;
    private Integer numero;

    public Endereco(String pais, String estado, String cidade, String cep, String bairro, String logradouro, Integer numero){
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public String getPais(){
        return this.pais;
    }
    public String getEstado(){
        return this.estado;
    }
    public String getCidade(){
        return this.cidade;
    }
    public String getCep(){
        return this.cep;
    }
    public String getBairro(){
        return this.bairro;
    }
    public String getLogradouro(){
        return this.logradouro;
    }
    public Integer getNumero(){
        return this.numero;
    }

    @Override
    public String toString(){
        return  "CEP: " + getCep() + " \n" +
                "Endereço: " + getLogradouro() + ", " + getNumero() + "-" + getBairro() + "-" + getCidade() + ", " + getEstado() + " " + getPais();
    }
}
