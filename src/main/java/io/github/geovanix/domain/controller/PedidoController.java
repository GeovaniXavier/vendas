package io.github.geovanix.domain.controller;

import io.github.geovanix.domain.dto.PedidoDTO;
import io.github.geovanix.domain.entity.Pedido;
import io.github.geovanix.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos/")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save (@RequestBody PedidoDTO pedidoDTO) {
      Pedido pedido = pedidoService.salvar(pedidoDTO);
      return pedido.getId();
    }


}
