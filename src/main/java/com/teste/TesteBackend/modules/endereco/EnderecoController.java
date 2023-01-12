package com.teste.TesteBackend.modules.endereco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;
import com.teste.TesteBackend.modules.pessoa.Pessoa;
import com.teste.TesteBackend.modules.pessoa.PessoaRepository;



@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/pessoa/{id}")
    public ResponseEntity <List<Endereco>> listarEnderecosPessoa(@PathVariable long id) {

        List<Endereco> enderecos = enderecoRepository.findByPessoaId(id);
        
        if(enderecos == null){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Endereco não encontrado");
        }
        return ResponseEntity.ok().body(enderecos);
        
    }

    @GetMapping("/principal/pessoa/{id}")
    public ResponseEntity <Endereco> getEnderecoPrincipalPessoa(@PathVariable long id) {

        Endereco enderecos = enderecoRepository.findByPessoaIdAndTipo(id,TipoEnderco.P);
        
        if(enderecos == null){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Endereco Principal não encontrado");
        }
        return ResponseEntity.ok().body(enderecos);
        
    }

}
