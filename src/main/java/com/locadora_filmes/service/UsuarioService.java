package com.locadora_filmes.service;


import com.locadora_filmes.DTOs.UsuarioDTO;
import com.locadora_filmes.entity.Usuario;
import com.locadora_filmes.exception.EntidadeNaoEncontradaException;
import com.locadora_filmes.exception.RegraDeNegocioException;
import com.locadora_filmes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario){
        if(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent()){
            throw new RegraDeNegocioException("O email " + usuario.getEmailUsuario() + " ja está em uso");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        if(usuarioRepository.count() == 0){
            throw new EntidadeNaoEncontradaException("Nenhum cadastro feito no sistema.");
        }
        return usuarioRepository.findAll();
    }

    public Usuario listarPorEmail(String emailUsuario){
        if(usuarioRepository.count() == 0){
            throw new EntidadeNaoEncontradaException("Nenhum cadastro feito no sistema.");
        }
        return usuarioRepository.findByEmailUsuario(emailUsuario).orElseThrow(() -> new EntidadeNaoEncontradaException("Email: " +emailUsuario + " não encontrado."));
    }

    public Usuario listarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Id: " + id + " não encontrado."));
    }

    public void deletarPorId(Long id){
       if (!usuarioRepository.existsById(id)){
           throw new EntidadeNaoEncontradaException("Não foi possível deletar. ID: " + id + " não encontrado.");
       }
        usuarioRepository.deleteById(id);
    }

    /*public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Id: " + id + " não encontrado"));

        usuarioExistente.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioExistente.setEmailUsuario(usuarioAtualizado.getEmailUsuario());
        usuarioExistente.setSenhaUsuario(usuarioAtualizado.getSenhaUsuario());
        usuarioExistente.setCpfUsuario(usuarioAtualizado.getCpfUsuario());
        usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        usuarioExistente.setTipo(usuarioAtualizado.getTipo());

        return usuarioRepository.save(usuarioExistente);
    }*/

    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Id: " + id + " não foi encontrado"));

        if (usuarioDTO.getNomeUsuario() != null) {
            usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
        }

        if (usuarioDTO.getEmailUsuario() != null) {
            usuario.setEmailUsuario(usuarioDTO.getEmailUsuario());
        }

        if (usuarioDTO.getSenhaUsuario() != null) {
            usuario.setSenhaUsuario(usuarioDTO.getSenhaUsuario());
        }

        if (usuarioDTO.getDataNascimento() != null) {
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());
        }

        return usuarioRepository.save(usuario);

    }


}
