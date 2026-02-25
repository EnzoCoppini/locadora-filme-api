package com.locadora_filmes.DTOs.request.filme;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.time.LocalTime;

public record FilmeUpdateRequest(

        String titulo,
        LocalTime duracao,
        String genero,
        String descricao,
        Integer idadeMinima,
        Integer anoLancamento,
        @DecimalMin(value = "1.0", message = "Preço deve ser maior ou igual a 1.") Double preco,
        @Min(value = 1, message = "Quantidade em estoque deve ser maior ou igual a 1.")Integer quantidadeEstoque
)
{}
