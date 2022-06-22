package io.github.geovanix.domain.controller;

import io.github.geovanix.domain.entity.Produto;
import io.github.geovanix.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/produto/")
public class ProdutoController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return produtosRepository.findById(id)
                                 .orElseThrow(() ->
                                 new ResponseStatusException(HttpStatus.NOT_FOUND,
                                 "Produto não encontrado"));
    }

}
