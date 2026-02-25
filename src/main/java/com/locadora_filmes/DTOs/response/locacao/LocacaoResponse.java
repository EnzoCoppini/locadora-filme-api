package com.locadora_filmes.DTOs.response.locacao;

import java.time.LocalDate;

public record LocacaoResponse(

    Long idLocacao,
    String nomeUsuario,
    Long idUsuario,
    String tituloFilme,
    Long idFilme,
    LocalDate dataLocacao,
    LocalDate dataDevolucao,
    boolean devolvido
)
{}