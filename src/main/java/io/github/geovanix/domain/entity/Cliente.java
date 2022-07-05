package io.github.geovanix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table( name = "cliente" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo Nome é obrigatório.")
    @CPF(message = "Informe um CPF válido.")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo CPF é obrigatório.")
    private String cpf;

    @JsonIgnore
    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

}
