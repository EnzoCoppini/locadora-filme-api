package com.locadora_filmes.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class FilmeDTO {

    private String titulo;
    private LocalTime duracao;
    private String genero;
    private String descricao;
    private Integer idadeMinima;
    private Integer anoLancamento;
    private Double preco;
    private Integer quantidadeEstoque;

}
