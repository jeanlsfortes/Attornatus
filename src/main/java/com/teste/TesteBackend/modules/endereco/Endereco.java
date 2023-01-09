package com.teste.TesteBackend.modules.endereco;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;
import com.teste.TesteBackend.modules.pessoa.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "endereco")
public class Endereco {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco")
    private TipoEnderco tipo;
    
    private String lagouro;

    private String cep;

    private Long numero;

    private String cidade;

    @JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name= "pessoa_endereco"))
    private Pessoa pessoa;
}
