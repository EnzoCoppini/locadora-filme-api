package com.locadora_filmes.service;


import com.locadora_filmes.entity.Usuario;
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
        if(usuarioRepository.findByEmail(usuario.getEmailUsuario()).isPresent()){
            throw new RuntimeException("Email ja cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
}
