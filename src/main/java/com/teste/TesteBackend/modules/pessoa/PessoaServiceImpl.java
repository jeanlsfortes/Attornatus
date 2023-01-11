package com.teste.TesteBackend.modules.pessoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService{

    @Autowired
    private PessoaRepository pessoaRepository;


    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }


    @Override
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }
    
    
}
