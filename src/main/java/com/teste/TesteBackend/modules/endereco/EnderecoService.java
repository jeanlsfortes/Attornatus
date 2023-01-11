package com.teste.TesteBackend.modules.endereco;

import java.util.List;

import com.teste.TesteBackend.modules.pessoa.Pessoa;

public interface EnderecoService {
    
    boolean verificarCondicaoEndereço(List<Endereco> enderecos);

    Endereco getEnderecoPrincipal(Pessoa pessoa);
}
