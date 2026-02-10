package com.locadora_filmes.service;


import com.locadora_filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {


    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }
}
