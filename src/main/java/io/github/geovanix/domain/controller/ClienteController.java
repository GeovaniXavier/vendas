package io.github.geovanix.domain.controller;

import io.github.geovanix.domain.entity.Cliente;
import io.github.geovanix.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/*
Ao Usar RestController ao invés de controller ele já vem com a annotation @ResponseBody Embutida,
Ou seja, não precisa colocar @ResponseBody em cima de cada metodo.
 */
@RestController

//Ao usar o @RequestMapping, usar uma url universal e se necessario ir passando os parametros como por exemplo: {id}
@RequestMapping("/api/cliente/")
public class ClienteController {

    @Autowired
    private ClienteRepository clientesRepository;

    //BuscarPorId
    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientesRepository.findById(id)
                       .orElseThrow(() ->
                       new ResponseStatusException(HttpStatus.NOT_FOUND,
                       "Cliente não encontrando."));

    }

    //Metodo Salvar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
        return clientesRepository.save(cliente);
    }


    //Metodo Deletar
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientesRepository.findById(id)
                .map(cliente -> {
                    clientesRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));
    }

    //Metodo de Atualizar
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,
                                    @RequestBody @Valid Cliente cliente) {
        clientesRepository.findById(id).map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientesRepository.save(cliente);
            return clienteExistente;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
           "Cliente não encontrado"));
    }

    //Fazer Pesquisa por filtros com atualizações do Java8.
    @GetMapping
    public List<Cliente> find (Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                     ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clientesRepository.findAll(example);

    }

}