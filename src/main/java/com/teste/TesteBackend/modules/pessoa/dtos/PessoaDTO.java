package com.teste.TesteBackend.modules.pessoa.dtos;

import java.util.Date;
import java.util.List;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;

import lombok.Data;

@Data
public class PessoaDTO {
    
    private String nome;

    private Date dataNascimento;

    private List<enderecoDTO> enderecos;

    @Data
    public class enderecoDTO {
        
        private TipoEnderco tipo;

        private String logradouro;

        private String cep;

        private Long  numero;

        private String cidade;
    }
}
