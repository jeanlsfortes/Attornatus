package com.teste.TesteBackend.modules.endereco;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teste.TesteBackend.modules.endereco.enums.TipoEnderco;
import com.teste.TesteBackend.modules.pessoa.Pessoa;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Override
    public boolean verificarCondicaoEndereço(List<Endereco> enderecos) {
        boolean enderecoPrincipal = false;
        
        for(Endereco endereco : enderecos){
            if(endereco.getTipo() == TipoEnderco.P){
                if(enderecoPrincipal){
                    return false;
                }
                enderecoPrincipal = true;
            }
        }
        return true;
    }

    @Override
    public Endereco getEnderecoPrincipal(Pessoa pessoa) {
        for(Endereco endereco : pessoa.getEndereco()){
            if(endereco.getTipo() == TipoEnderco.P){
                return endereco;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Não Encontrada");
    }
}
