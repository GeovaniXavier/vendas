package io.github.geovanix.domain.controller;

import io.github.geovanix.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos/")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;



}
