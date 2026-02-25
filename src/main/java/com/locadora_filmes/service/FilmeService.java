package com.locadora_filmes.service;


import com.locadora_filmes.DTOs.request.filme.FilmeRequest;
import com.locadora_filmes.DTOs.request.filme.FilmeUpdateRequest;
import com.locadora_filmes.DTOs.response.filme.FilmeResponse;
import com.locadora_filmes.mapper.FilmeMapper;
import com.locadora_filmes.entity.Filme;
import com.locadora_filmes.exception.EntidadeNaoEncontradaException;
import com.locadora_filmes.exception.RegraDeNegocioException;
import com.locadora_filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {


    private final FilmeRepository filmeRepository;
    private final FilmeMapper filmeMapper;

    public FilmeService(FilmeRepository filmeRepository, FilmeMapper filmeMapper) {
        this.filmeRepository = filmeRepository;
        this.filmeMapper = filmeMapper;
    }

    public FilmeResponse cadastrarFilme(FilmeRequest filmeRequest) {
        if (filmeRepository.existsByTituloIgnoreCase(filmeRequest.titulo())) {
            throw new RegraDeNegocioException("Titulo ja cadastrado.");
        }
        Filme filme = filmeMapper.toEntity(filmeRequest);
        Filme filmeSalvo = filmeRepository.save(filme);
        return filmeMapper.toDto(filmeSalvo);

    }

    public FilmeResponse buscarPorTitulo(String titulo) {

        Filme filme = filmeRepository.findByTituloIgnoreCase(titulo)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException("Título: " + titulo + " não encontrado"));

        return filmeMapper.toDto(filme);
    }

    //locacao
    public List<FilmeResponse> listarFilmes() {

        List<Filme> filmes = filmeRepository.findAll();
        if (filmes.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum filme cadastrado");
        }
        return filmes.stream().map(filmeMapper::toDto).toList();
    }

    public void removerPorId(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Não foi possível deletar. Id: " + id + " não encontrado.");
        }
        filmeRepository.deleteById(id);
    }

    public FilmeResponse atualizar(Long id, FilmeUpdateRequest filmeDTO) {

        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar. Id: " + id + " não encontrado."));

        filmeMapper.updateEntityFromDto(filmeDTO, filme);

        Filme filmeAtualizado = filmeRepository.save(filme);

        return filmeMapper.toDto(filmeAtualizado);

    }
}
