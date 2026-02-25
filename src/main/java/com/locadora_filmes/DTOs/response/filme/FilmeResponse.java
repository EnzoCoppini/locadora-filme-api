package com.locadora_filmes.DTOs.response.filme;

import java.time.LocalTime;

public record FilmeResponse(

    Long idFilme,
    String titulo,
    LocalTime duracao,
    String genero,
    Integer idadeMinima,
    Integer anoLancamento,
    Double preco,
    Integer quantidadeEstoque
)
{}
