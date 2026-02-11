package com.locadora_filmes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "usuario")
public class Usuario {

    public enum tipoUsuario{
        ADMIN,
        CLiENTE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "email_usuario", nullable = false)
    private String emailUsuario;

    @Column(name = "senha_usuario")
    private String senhaUsuario;

    @Column(name = "cpf_usuario", nullable = false)
    private String cpfUsuario;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;


    @Enumerated(EnumType.STRING)
    private tipoUsuario tipo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Locacao> locacoes;


}
