package com.teste.TesteBackend.modules.pessoa;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
/*   Criar uma pessoa
    Editar uma pessoa
    Consultar uma pessoa
    Listar pessoas
    Criar endereço para pessoa
    Listar endereços da pessoa
    Poder informar qual endereço é o principal da pessoa  
*/  

@Autowired
private PessoaService pessoaService;

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa){
        return pessoaService.salvar(pessoa);
    }

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaService.listar();
    }
    
}
