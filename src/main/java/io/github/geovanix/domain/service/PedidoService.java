package io.github.geovanix.domain.service;

import io.github.geovanix.domain.dto.PedidoDTO;
import io.github.geovanix.domain.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
