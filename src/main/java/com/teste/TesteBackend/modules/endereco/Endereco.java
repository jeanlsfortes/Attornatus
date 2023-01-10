package com.teste.TesteBackend.modules.endereco;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco")
    private TipoEnderco tipo;
    
    private String lagouro;

    private String cep;

    private Long numero;

    private String cidade;

}
