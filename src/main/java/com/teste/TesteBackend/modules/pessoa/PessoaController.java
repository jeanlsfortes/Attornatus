package com.teste.TesteBackend.modules.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoaRepository.findById(id).map(pessoaAntiga -> {
            if (!enderecoService.verificarCondicaoEndereço(pessoa.getEnderecos())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Não foi possivel atualizar a pessoa pois existe mais de um endereço principal");
            }
            pessoaAntiga.setId(pessoa.getId());
            pessoaAntiga.setNome(pessoa.getNome());
            pessoaAntiga.setDataNascimento(pessoa.getDataNascimento());
            pessoaAntiga.setEnderecos(pessoa.getEnderecos());
            pessoaRepository.save(pessoaAntiga);
            return pessoaAntiga;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pessoa consultar(@PathVariable Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/listar-enderecos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Endereco> pessoalistarEnderecos(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"));
        if(pessoa.getEnderecos() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Essa pessoa Não tem endereços cadastrados");
        }
        return pessoa.getEnderecos();
    }

    @GetMapping("/endereco-principal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco pessoaEnderecoPrincipal(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if(pessoa != null){
            return enderecoService.getEnderecoPrincipal(pessoa);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada"); 
        }
        

    }
}
