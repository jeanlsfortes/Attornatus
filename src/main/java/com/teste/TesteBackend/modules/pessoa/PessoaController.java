package com.teste.TesteBackend.modules.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.teste.TesteBackend.modules.endereco.Endereco;
import com.teste.TesteBackend.modules.endereco.EnderecoService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ((BodyBuilder) ResponseEntity.ok(HttpStatus.CREATED)).body(pessoaRepository.save(pessoa));
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoaRepository.findById(id).map(pessoaAntiga -> {
            if (!enderecoService.verificarCondicaoEndereço(pessoa.getEndereco())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Não foi possivel atualizar a pessoa pois existe mais de um endereço principal");
            }
            pessoaAntiga.setId(pessoa.getId());
            pessoaAntiga.setNome(pessoa.getNome());
            pessoaAntiga.setDataNascimento(pessoa.getDataNascimento());
            pessoaAntiga.setEndereco(pessoa.getEndereco());
            pessoaRepository.save(pessoaAntiga);
            return ResponseEntity.ok().body(pessoa.getEndereco());
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> consultar(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada")));   
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/listar-enderecos/{id}")
    public ResponseEntity<List<Endereco>> pessoalistarEnderecos(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"));
        if(pessoa.getEndereco() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Essa pessoa Não tem endereços cadastrados");
        }
        return ResponseEntity.ok().body(pessoa.getEndereco());
    }

    @GetMapping("/endereco-principal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Endereco> pessoaEnderecoPrincipal(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if(pessoa == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"); 
        }else{
            return ResponseEntity.ok(enderecoService.getEnderecoPrincipal(pessoa));
        }
        

    }
}
