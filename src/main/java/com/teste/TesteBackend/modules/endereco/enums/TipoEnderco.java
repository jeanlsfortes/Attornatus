package com.teste.TesteBackend.modules.endereco.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoEnderco {
    
    // Como a pessoa tem um endereço principal e outros endereços secundários, o enum é utilizado para identificar o tipo de endereço

    P("PRINCIPAL"), S("SECUNDARIO");

    private final String tipo;

    private TipoEnderco(final String tipo){
        this.tipo = tipo;
    }

    public String getValue(){
        return this.name();
    }
}
