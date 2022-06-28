package io.github.geovanix.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDto {

    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private List<InformacoesItemPedidoDto> items;
    private String dataPedido;

}
