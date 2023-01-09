package com.teste.TesteBackend.modules.pessoa;

import java.util.Date;
import java.util.List;

import com.teste.TesteBackend.modules.endereco.Endereco;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    private String Nome;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private Date dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

}
