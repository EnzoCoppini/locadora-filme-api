package com.locadora_filmes.service;


import com.locadora_filmes.entity.Filme;
import com.locadora_filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {


    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }


    public Filme cadastrarFilme(Filme filme){
        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilme (){
       return filmeRepository.findAll();
    }

    public void removerPorId(Long id){
        filmeRepository.deleteById(id);
    }
}
