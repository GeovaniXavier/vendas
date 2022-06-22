package io.github.geovanix.domain.repository;

import io.github.geovanix.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto,Integer> {


}
