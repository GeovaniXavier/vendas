package io.github.geovanix.domain.service.imp;

import io.github.geovanix.domain.dto.ItemPedidoDTO;
import io.github.geovanix.domain.dto.PedidoDTO;
import io.github.geovanix.domain.entity.Cliente;
import io.github.geovanix.domain.entity.ItemPedido;
import io.github.geovanix.domain.entity.Pedido;
import io.github.geovanix.domain.entity.Produto;
import io.github.geovanix.domain.exception.RegraDeNegocio;
import io.github.geovanix.domain.repository.ClienteRepository;
import io.github.geovanix.domain.repository.ItemsPedidoRepository;
import io.github.geovanix.domain.repository.PedidosRepository;
import io.github.geovanix.domain.repository.ProdutosRepository;
import io.github.geovanix.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ItemsPedidoRepository itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {

        Integer idCliente = pedidoDTO.getCliente();

        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(() -> new RegraDeNegocio("Codigo de cliente invalido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedidos = converterItems(pedido, pedidoDTO.getItems());
        pedidosRepository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTO) {
        if (itemPedidoDTO.isEmpty()) {
            throw new RegraDeNegocio("Não é possivel realizar um pedido sem items.");
        }
        return itemPedidoDTO
                .stream()
                .map(dto -> {

                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto).orElseThrow(() ->
                            new RegraDeNegocio("Codigo de produto invalido: " + idProduto));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
