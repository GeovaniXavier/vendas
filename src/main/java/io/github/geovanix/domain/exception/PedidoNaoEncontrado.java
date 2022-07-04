package io.github.geovanix.domain.exception;

public class PedidoNaoEncontrado extends RuntimeException {

    public PedidoNaoEncontrado() {
        super("Pedido não encontrado.");
    }
}
