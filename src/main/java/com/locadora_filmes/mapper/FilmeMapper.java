package com.locadora_filmes.mapper;

import com.locadora_filmes.DTOs.request.filme.FilmeRequest;
import com.locadora_filmes.DTOs.request.filme.FilmeUpdateRequest;
import com.locadora_filmes.DTOs.response.filme.FilmeResponse;
import com.locadora_filmes.entity.Filme;
import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    public FilmeResponse toDto(Filme filme){

        if (filme == null){
            return null;
        }
        return new FilmeResponse(
                filme.getIdFilme(),
                filme.getTitulo(),
                filme.getDuracao(),
                filme.getGenero(),
                filme.getIdadeMinima(),
                filme.getAnoLancamento(),
                filme.getPreco(),
                filme.getQuantidadeEstoque()
                );
    }

    public Filme toEntity(FilmeRequest request){
        return Filme.builder()
                .titulo(request.titulo())
                .duracao(request.duracao())
                .genero(request.genero())
                .descricao(request.descricao())
                .idadeMinima(request.idadeMinima())
                .anoLancamento(request.anoLancamento())
                .preco(request.preco())
                .quantidadeEstoque(request.quantidadeEstoque())
                .build();
    }

    public void updateEntityFromDto(FilmeUpdateRequest request, Filme filme){

        if (request.titulo() != null) {
            filme.setTitulo(request.titulo());
        }

        if (request.duracao() != null) {
            filme.setDuracao(request.duracao());
        }

        if (request.genero() != null) {
            filme.setGenero(request.genero());
        }

        if (request.descricao() != null) {
            filme.setDescricao(request.descricao());
        }

        if (request.idadeMinima() != null) {
            filme.setIdadeMinima(request.idadeMinima());
        }

        if (request.anoLancamento() != null) {
            filme.setAnoLancamento(request.anoLancamento());
        }

        if (request.preco() != null){
            filme.setPreco(request.preco());
        }

        if (request.quantidadeEstoque()!= null){
            filme.setQuantidadeEstoque(request.quantidadeEstoque());
        }

        }
    }

