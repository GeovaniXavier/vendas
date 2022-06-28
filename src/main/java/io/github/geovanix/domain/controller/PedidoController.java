package io.github.geovanix.domain.controller;

import io.github.geovanix.domain.dto.InformacoesItemPedidoDto;
import io.github.geovanix.domain.dto.InformacoesPedidoDto;
import io.github.geovanix.domain.dto.PedidoDTO;
import io.github.geovanix.domain.entity.ItemPedido;
import io.github.geovanix.domain.entity.Pedido;
import io.github.geovanix.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    @GetMapping("{id}")
    public InformacoesPedidoDto getById(@PathVariable Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow(() ->
                    new ResponseStatusException(NOT_FOUND, "Pedido não encontrado.")
                );
    }
    private InformacoesPedidoDto converter(Pedido pedido) {
       return InformacoesPedidoDto
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDto> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacoesItemPedidoDto
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }


}
