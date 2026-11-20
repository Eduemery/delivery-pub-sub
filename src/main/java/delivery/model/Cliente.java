package delivery.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Endereco> enderecos;

    public Cliente(String nome){
        this.nome = nome;
        enderecos = new ArrayList<>();
    }

    public String getNome(){return this.nome;}

    public void setEnderecos(Endereco endereco){
        this.enderecos.add(endereco);
    }

    @Override
    public String toString(){
        return "Cliente: " + getNome();
    }

}
