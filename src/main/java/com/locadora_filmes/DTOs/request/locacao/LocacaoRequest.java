package com.locadora_filmes.DTOs.request.locacao;

import jakarta.validation.constraints.NotNull;

public record LocacaoRequest (

    @NotNull(message = "Id do Usuário é obrigatório.") Long idUsuario,
    @NotNull(message = "Id do filme é obrigatorio.") Long idFilme
)
{}
