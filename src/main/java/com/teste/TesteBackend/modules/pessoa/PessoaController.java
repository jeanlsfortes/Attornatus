package com.teste.TesteBackend.modules.pessoa;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Cliente")
public class PessoaController {

    @GetMapping("/listar")
    public List<Pessoa> listar() {
        Pessoa carlos = new Pessoa();
        


        return null;
    }
    
}
