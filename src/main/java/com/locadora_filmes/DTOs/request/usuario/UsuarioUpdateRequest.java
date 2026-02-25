package com.locadora_filmes.DTOs.request.usuario;
import com.locadora_filmes.entity.Usuario.tipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioUpdateRequest(

        @Size(min = 3,  max = 100, message = "Nome deve ter entre 3 a 100 caracteres.") String nomeUsuario,
        @Email(message = "Email invalido.") String emailUsuario,
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres." ) String senhaUsuario,
        String cpfUsuario,
        LocalDate dataNascimento,
        tipoUsuario tipoUsuario

) {}