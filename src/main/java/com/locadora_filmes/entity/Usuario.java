package com.locadora_filmes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    public enum tipoUsuario{
        ADMIN,
        CLIENTE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "email_usuario", nullable = false, unique = true)
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
    @JsonIgnore
    private List<Locacao> locacoes;


}
