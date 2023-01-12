package com.teste.TesteBackend.modules.endereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPessoaId(Long pessoaId);
    
    Endereco findByPessoaIdAndTipo(Long pessoaId, TipoEnderco tipo);
}
