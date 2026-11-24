package delivery.service;

import delivery.model.Pedido;

public interface IPedidoService {
    public void realizarPedido(String numeroPedido);
    public void prepararPedido(String numeroPedido);
    public void retirarPedido(String numeroPedido);
    public void entregarPedido(String numeroPedido);
    public void concluirPedido(String numeroPedido);
    public void cancelarPedido(String numeroPedido);
}
