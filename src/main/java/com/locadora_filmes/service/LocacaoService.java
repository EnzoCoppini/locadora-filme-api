package com.locadora_filmes.service;


import com.locadora_filmes.repository.FilmeRepository;
import com.locadora_filmes.repository.LocacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;
    private final FilmeRepository filmeRepository;

    public LocacaoService(LocacaoRepository locacaoRepository, FilmeRepository filmeRepository){
        this.locacaoRepository = locacaoRepository;
        this.filmeRepository = filmeRepository;
    }

}
