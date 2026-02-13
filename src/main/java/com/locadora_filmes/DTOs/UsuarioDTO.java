package com.locadora_filmes.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDTO {

        private String nomeUsuario;
        private String emailUsuario;
        private String senhaUsuario;
        private LocalDate dataNascimento;

}
