package com.locadora_filmes.DTOs.request.usuario;

import com.locadora_filmes.entity.Usuario.tipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UsuarioRequest(
        @NotBlank(message = "Nome é obrigatório.") @Size(min = 3, max = 100, message = "Nome deve ter entre 3 a 100 caracteres.") String nomeUsuario,
        @NotBlank(message = "Email é obritatório.")@Email(message = "Email invalido.") String emailUsuario,
        @NotBlank(message = "Senha é obrigatória.") @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.") String senhaUsuario,
        @NotBlank(message = "CPF é obrigatório.") String cpfUsuario,
        @NotNull(message = "Data de lançamento é obrigatória.") LocalDate dataNascimento,
        @NotNull(message = "Tipo de usuário é obrigatório.") tipoUsuario tipoUsuario

    )
{}
