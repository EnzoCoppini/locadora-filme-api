package com.locadora_filmes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Long idFiilme;

    @Column(name = "titulo", nullable = false)
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
    private List<Locacao> historicoLocacoes;

}
