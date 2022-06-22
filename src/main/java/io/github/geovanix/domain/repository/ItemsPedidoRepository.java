package io.github.geovanix.domain.repository;

import io.github.geovanix.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
