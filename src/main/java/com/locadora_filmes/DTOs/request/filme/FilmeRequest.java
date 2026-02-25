package com.locadora_filmes.DTOs.request.filme;


import jakarta.validation.constraints.*;

import java.time.LocalTime;

public record FilmeRequest(


        @NotBlank(message = "Titulo é obrigatório.") String titulo,
        @NotNull(message = "duração é obrigatótrio.") LocalTime duracao,
        @NotBlank(message = "Gênero é obrigatório.") String genero,
        @NotBlank(message = "Descrição é obrigatória.") String descricao,
        @NotNull(message = "Idade minima é obrigatório.") Integer idadeMinima,
        @NotNull(message = "Ano de lançamento é obrigatório.") Integer anoLancamento,
        @NotNull(message = "Preço é obrigatório.")@DecimalMin(value = "1.0", message = "Preço deve ser maior ou igual a 1.") Double preco,
        @NotNull(message = "Quantida em estoque é obrigatório.") @Min(value = 1, message = "Quantidade em estoque deve ser maior ou igual a 1.") Integer quantidadeEstoque
)
{

}

