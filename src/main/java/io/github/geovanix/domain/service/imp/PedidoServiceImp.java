package io.github.geovanix.domain.service.imp;

import io.github.geovanix.domain.repository.PedidosRepository;
import io.github.geovanix.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;


}
