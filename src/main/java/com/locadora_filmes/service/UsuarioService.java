package com.locadora_filmes.service;

import com.locadora_filmes.DTOs.request.usuario.UsuarioRequest;
import com.locadora_filmes.DTOs.request.usuario.UsuarioUpdateRequest;
import com.locadora_filmes.DTOs.response.usuario.UsuarioResponse;
import com.locadora_filmes.mapper.UsuarioMapper;
import com.locadora_filmes.entity.Usuario;
import com.locadora_filmes.exception.EntidadeNaoEncontradaException;
import com.locadora_filmes.exception.RegraDeNegocioException;
import com.locadora_filmes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponse cadastrarUsuario(UsuarioRequest request){

        if (usuarioRepository.findByEmailUsuario(request.emailUsuario()).isPresent()){
            throw new RegraDeNegocioException("Email já está em uso.");
        }

        Usuario usuario = usuarioMapper.toEntity(request);
        Usuario salvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(salvo);
    }

    public List<UsuarioResponse> listarUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum usuário cadastrado.");
        }

        return usuarios.stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));

        return usuarioMapper.toDto(usuario);
    }

    public UsuarioResponse buscarPorEmail(String email){

        Usuario usuario = usuarioRepository.findByEmailUsuario(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Email não encontrado."));

        return usuarioMapper.toDto(usuario);
    }

    public void deletarPorId(Long id){

        if (!usuarioRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
        }

        usuarioRepository.deleteById(id);
    }


    public UsuarioResponse atualizarUsuario(Long id, UsuarioUpdateRequest usuarioDTO){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Id: " + id + " não foi encontrado"));

        usuarioMapper.updateEntityFromDto(usuarioDTO, usuario);

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioAtualizado);
    }
}