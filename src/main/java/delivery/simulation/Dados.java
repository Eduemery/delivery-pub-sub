package delivery.simulation;

import delivery.model.CalculadoraPedido;
import delivery.model.Cliente;
import delivery.model.Endereco;
import delivery.model.Entregador;
import delivery.model.Pedido;
import delivery.model.Produto;
import delivery.model.Restaurante;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dados {
    private List<Restaurante> restaurantes;
    private List<Entregador> entregadores;

    public Dados(){
        restaurantes = List.of(
                new Restaurante("R01", "PIZZARIA CENTRAL"),
                new Restaurante("R02", "LANCHONETE SABOR"),
                new Restaurante("R03", "RESTAURANTE BOM PRATO")
        );
        entregadores = List.of(
                new Entregador("E01", "JOAO"),
                new Entregador("E02", "MARCOS"),
                new Entregador("E03", "LUCAS"),
                new Entregador("E04", "RAFAEL"),
                new Entregador("E05", "GABRIEL")
        );
    }

    public List<Restaurante> getRestaurantes(){
        return this.restaurantes;
    }
    public List<Entregador> getEntregadores(){
        return this.entregadores;
    }
    public List<Pedido> criarPedidos(){
        Random random = new Random();
        int quantidade = random.nextInt(10);
        if(quantidade == 0)
            quantidade = 1;

        CalculadoraPedido calculadoraPedido = new CalculadoraPedido();
        List<Pedido> pedidos = new ArrayList<>();

        String[] nomes = {
                "EDUARDO EMERY", "MARIA SILVA", "JOAO SANTOS", "ANA OLIVEIRA",
                "CARLOS SOUZA", "JULIANA COSTA", "PEDRO ALMEIDA", "FERNANDA LIMA",
                "RICARDO ROCHA", "BEATRIZ MARTINS", "LUIZA MENDES", "MATEUS LIMA",
                "SOFIA ROCHA", "GUSTAVO ALVES", "ISABELA COSTA", "THIAGO SANTOS",
                "CAMILA SOUZA", "VINICIUS MARTINS", "LAURA OLIVEIRA", "FELIPE ROCHA"
        };

        String[] bairros = {
                "CENTRO", "CENTRO", "VILA NOVA", "PRÓXIMO AO CENTRO", "GUARAREMA",
                "CENTRO", "VILA NOVA", "GUARAREMA", "CENTRO", "VILA NOVA"
        };
        String[] ruas = {
                "RUA PADRE ANCHIETA", "RUA SETE DE SETEMBRO", "RUA DAS FLORES",
                "RUA FELIPE CALMON", "RUA DOS IPES", "RUA QUINZE DE NOVEMBRO",
                "RUA DA LIBERDADE", "RUA PRINCIPAL", "RUA JOSE ALEXANDRE", "RUA SANTA TEREZA"
        };
        for (int i = 0; i < quantidade; i++) {
            Cliente cliente = new Cliente(nomes[i % nomes.length]);
            Endereco endereco = new Endereco(
                    "BRASIL", "ESPÍRITO SANTO", "ALEGRE", "29500-000",
                    bairros[i % bairros.length], ruas[i % ruas.length], 100 + i
            );
            cliente.setEnderecos(endereco);

            List<Produto> itens = criarProdutos(i);
            Restaurante restaurante = restaurantes.get(i % restaurantes.size());
            Entregador entregador = entregadores.get(i % entregadores.size());
            Pedido pedido = new Pedido(String.format("%03d", i + 1), LocalDateTime.now(), cliente, endereco, itens, restaurante, entregador);
            pedido.setValorTotal(calculadoraPedido.calcular(pedido));
            pedidos.add(pedido);
            System.out.println(pedido.toString() + "\n");
        }
        return pedidos;
    }

    public List<Produto> criarProdutos(int i){
        return switch (i % 10) {
            case 0 -> List.of(new Produto("PIZZA", 1, 79.99), new Produto("REFRIGERANTE LATA", 1, 9.00));
            case 1 -> List.of(new Produto("HAMBURGUER", 2, 25.00), new Produto("BATATA FRITA", 1, 15.00));
            case 2 -> List.of(new Produto("PIZZA GRANDE", 1, 65.00), new Produto("SUCO", 2, 8.00));
            case 3 -> List.of(new Produto("SANDUICHE", 2, 18.00), new Produto("REFRIGERANTE", 2, 7.00));
            case 4 -> List.of(new Produto("LASANHA", 1, 32.00), new Produto("SUCO NATURAL", 1, 10.00));
            case 5 -> List.of(new Produto("HAMBURGUER", 1, 28.00), new Produto("BATATA FRITA", 2, 12.00));
            case 6 -> List.of(new Produto("PIZZA MEDIA", 2, 45.00), new Produto("REFRIGERANTE", 1, 9.00));
            case 7 -> List.of(new Produto("SALADA", 1, 22.00), new Produto("SUCO NATURAL", 2, 10.00));
            case 8 -> List.of(new Produto("SANDUICHE", 1, 20.00), new Produto("BATATA FRITA", 1, 12.00));
            default -> List.of(new Produto("PIZZA GRANDE", 2, 65.00), new Produto("REFRIGERANTE", 2, 9.00));
        };
    }
}
