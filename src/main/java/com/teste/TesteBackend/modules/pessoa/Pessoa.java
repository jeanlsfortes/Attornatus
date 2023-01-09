package com.teste.TesteBackend.modules.pessoa;

import java.util.Date;
import java.util.List;

import com.teste.TesteBackend.modules.endereco.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Pessoa {
    
    private String Nome;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private Date dataNascimento;

    private List<Endereco> enderecos;

}
