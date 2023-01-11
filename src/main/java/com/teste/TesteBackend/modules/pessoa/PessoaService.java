package com.teste.TesteBackend.modules.pessoa;

import java.util.List;

public interface PessoaService {

    Pessoa salvar(Pessoa pessoa);
    
    List<Pessoa> listar();
}
