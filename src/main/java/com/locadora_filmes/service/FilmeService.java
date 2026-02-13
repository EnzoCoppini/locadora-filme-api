package com.locadora_filmes.service;


import com.locadora_filmes.DTOs.FilmeDTO;
import com.locadora_filmes.entity.Filme;
import com.locadora_filmes.exception.EntidadeNaoEncontradaException;
import com.locadora_filmes.exception.RegraDeNegocioException;
import com.locadora_filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {


    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }

    public Filme cadastrarFilme(Filme filme){
       if (filmeRepository.existsByTituloIgnoreCase(filme.getTitulo())){
           throw new RegraDeNegocioException("Titulo ja cadastrado.");
       }
        return filmeRepository.save(filme);
    }

    //locacao
    public Filme buscarPorTitulo(String titulo){
        return filmeRepository.findByTituloIgnoreCase(titulo).orElseThrow(() -> new EntidadeNaoEncontradaException("Titulo não encontrado."));
    }

    //locacao
    public List<Filme> listarFilme (){

        List<Filme> filmes = filmeRepository.findAll();
        if (filmes.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum filme cadastrado");
        }
        return filmeRepository.findAll();
    }

    public void removerPorId(Long id){
        if (!filmeRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Nenhum filme com ID: " + id + " foi encontrado");
        }
        filmeRepository.deleteById(id);
    }

    public Filme atualizar(Long id, FilmeDTO filmeDTO){
        Filme filme = filmeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado"));

        if (filmeDTO.getTitulo() != null) {
            filme.setTitulo(filmeDTO.getTitulo());
        }

        if (filmeDTO.getDuracao() != null) {
            filme.setDuracao(filmeDTO.getDuracao());
        }

        if (filmeDTO.getGenero() != null) {
            filme.setGenero(filmeDTO.getGenero());
        }

        if (filmeDTO.getDescricao() != null) {
            filme.setDescricao(filmeDTO.getDescricao());
        }

        if (filmeDTO.getIdadeMinima() != null) {
            filme.setIdadeMinima(filmeDTO.getIdadeMinima());
        }

        if (filmeDTO.getAnoLancamento() != null) {
            filme.setAnoLancamento(filmeDTO.getAnoLancamento());
        }

        if (filmeDTO.getPreco() != null) {
            filme.setPreco(filmeDTO.getPreco());
        }

        if (filmeDTO.getQuantidadeEstoque() != null) {
            filme.setQuantidadeEstoque(filmeDTO.getQuantidadeEstoque());
        }

        return filmeRepository.save(filme);

    }

}
