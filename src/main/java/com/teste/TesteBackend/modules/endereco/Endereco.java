package com.teste.TesteBackend.modules.endereco;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;
import com.teste.TesteBackend.modules.pessoa.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco")
    private TipoEnderco tipo;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name="fk_pessoa_endereço"))
    private Pessoa pessoa;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "cidade")
    private String cidade;

}
