package com.locadora_filmes.DTOs.response.usuario;

import com.locadora_filmes.entity.Usuario.tipoUsuario;
import java.time.LocalDate;

public record UsuarioResponse(
    Long idUsuario,
    String nomeUsuario,
    String emailUsuario,
    LocalDate dataNascimento,
    String cpfUsuario,
    tipoUsuario tipoUsuario

)
{}


