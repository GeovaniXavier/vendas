package io.github.geovanix.domain.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente(@PathVariable ("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

}
