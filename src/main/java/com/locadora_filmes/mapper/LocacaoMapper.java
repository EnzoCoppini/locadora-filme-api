package com.locadora_filmes.mapper;

import com.locadora_filmes.DTOs.response.locacao.LocacaoResponse;
import com.locadora_filmes.entity.Filme;
import com.locadora_filmes.entity.Locacao;
import com.locadora_filmes.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LocacaoMapper {

    public Locacao toEntity(Usuario usuario, Filme filme){
        return Locacao.builder()
                .usuario(usuario)
                .filme(filme)
                .build();
    }

    public LocacaoResponse toResponse(Locacao locacao){
        if (locacao == null){
            return null;
        }

        return new LocacaoResponse(
                locacao.getIdLocacao(),
                locacao.getUsuario().getNomeUsuario(),
                locacao.getUsuario().getId(),
                locacao.getFilme().getTitulo(),
                locacao.getFilme().getIdFilme(),
                locacao.getDataLocacao(),
                locacao.getDataDevolucao(),
                locacao.isDevolvido()
        );
    }
}