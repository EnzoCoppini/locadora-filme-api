package com.locadora_filmes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Long idFilme;

    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "duracao", nullable = false)
    private LocalTime duracao;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "idade_minima", nullable = false)
    private Integer idadeMinima;

    @Column(name = "ano_lancamento", nullable = false)
    private Integer anoLancamento;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;


    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Locacao> historicoLocacoes;

}
