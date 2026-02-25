package com.locadora_filmes.mapper;

import com.locadora_filmes.DTOs.request.usuario.UsuarioRequest;
import com.locadora_filmes.DTOs.request.usuario.UsuarioUpdateRequest;
import com.locadora_filmes.DTOs.response.usuario.UsuarioResponse;
import com.locadora_filmes.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toDto(Usuario usuario){

        if (usuario == null){
            return null;
        }
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getDataNascimento(),
                usuario.getCpfUsuario(),
                usuario.getTipo()
                );
    }

    public Usuario toEntity(UsuarioRequest request){
        return Usuario.builder()
                .nomeUsuario(request.nomeUsuario())
                .emailUsuario(request.emailUsuario())
                .cpfUsuario(request.cpfUsuario())
                .dataNascimento(request.dataNascimento())
                .tipo(request.tipoUsuario())
                .senhaUsuario(request.senhaUsuario())
                .build();
    }

    public void updateEntityFromDto(UsuarioUpdateRequest request, Usuario usuario){

        if (request.nomeUsuario() != null) {
            usuario.setNomeUsuario(request.nomeUsuario());
        }

        if (request.emailUsuario() != null) {
            usuario.setEmailUsuario(request.emailUsuario());
        }

        if (request.senhaUsuario() != null) {
            usuario.setSenhaUsuario(request.senhaUsuario());
        }

        if (request.cpfUsuario() != null) {
            usuario.setCpfUsuario(request.cpfUsuario());
        }

        if (request.dataNascimento() != null) {
            usuario.setDataNascimento(request.dataNascimento());
        }

        if (request.tipoUsuario() != null) {
            usuario.setTipo(request.tipoUsuario());
        }
    }

}
