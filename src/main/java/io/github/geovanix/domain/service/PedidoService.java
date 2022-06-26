package io.github.geovanix.domain.service;

import io.github.geovanix.domain.dto.PedidoDTO;
import io.github.geovanix.domain.entity.Pedido;
import org.springframework.stereotype.Service;

@Service
public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);
}
