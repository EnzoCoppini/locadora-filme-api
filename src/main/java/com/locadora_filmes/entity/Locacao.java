package com.locadora_filmes.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacao")
    private Long idLocacao;

    @Column(name = "data_locacao", nullable = false)
    private LocalDate dataLocacao;

    @Column(name = "data_devolucao", nullable = false)
    private LocalDate dataDevolucao;

    @Column(name = "devolvido", nullable = false)
    private Boolean devolvido = false;


    @ManyToOne //muitos registro de locacao  para um usuario
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    @ManyToOne // muitos resgistro de locacao para um fime
    @JoinColumn(name = "id_filme", nullable = false)
    private Filme filme;
}
