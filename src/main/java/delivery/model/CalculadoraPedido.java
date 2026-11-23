package delivery.model;

public class CalculadoraPedido implements ICalculadora {

    @Override
    public Double calcular(Pedido pedido){
        Double valorTotal = 0.0;
        for(Produto item : pedido.getItens()){
            valorTotal += item.getValorTotal();
        }
        return valorTotal;
    }
}
