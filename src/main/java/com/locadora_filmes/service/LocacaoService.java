package com.locadora_filmes.service;

import com.locadora_filmes.DTOs.request.locacao.LocacaoRequest;
import com.locadora_filmes.DTOs.response.locacao.LocacaoResponse;
import com.locadora_filmes.mapper.LocacaoMapper;
import com.locadora_filmes.entity.Filme;
import com.locadora_filmes.entity.Locacao;
import com.locadora_filmes.entity.Usuario;
import com.locadora_filmes.exception.EntidadeNaoEncontradaException;
import com.locadora_filmes.exception.RegraDeNegocioException;
import com.locadora_filmes.repository.FilmeRepository;
import com.locadora_filmes.repository.LocacaoRepository;
import com.locadora_filmes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;
    private final FilmeRepository filmeRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocacaoMapper locacaoMapper;

    public LocacaoService(LocacaoRepository locacaoRepository, FilmeRepository filmeRepository, UsuarioRepository usuarioRepository, LocacaoMapper locacaoMapper) {
        this.locacaoRepository = locacaoRepository;
        this.filmeRepository = filmeRepository;
        this.usuarioRepository = usuarioRepository;
        this.locacaoMapper = locacaoMapper;
    }

    public LocacaoResponse realizarLocacao(LocacaoRequest request){

        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));

        Filme filme = filmeRepository.findById(request.idFilme())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado."));

        if (filme.getQuantidadeEstoque() <= 0){
            throw new RegraDeNegocioException("Sem unidades em estoque.");
        }

        List<Locacao> locacoesAtivas = locacaoRepository.findByUsuarioIdAndDevolvidoFalse(request.idUsuario());

        if (locacoesAtivas.size() >= 3){
            throw new RegraDeNegocioException("Limite máximo de 3 locações ativas.");
        }

        int idadeUsuario = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (filme.getIdadeMinima() > idadeUsuario){
            throw new RegraDeNegocioException("Usuário não possui idade mínima para este filme.");
        }

        Locacao locacao = locacaoMapper.toEntity(usuario, filme);

        locacao.setDataLocacao(LocalDate.now());
        locacao.setDataDevolucao(LocalDate.now().plusDays(3));
        locacao.setDevolvido(false);

        filme.setQuantidadeEstoque(filme.getQuantidadeEstoque() - 1);

        Locacao salva = locacaoRepository.save(locacao);

        return locacaoMapper.toResponse(salva);
    }

    public List<LocacaoResponse> listarLocacoes(){

        List<Locacao> locacoes = locacaoRepository.findAll();

        if (locacoes.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhuma locação encontrada.");
        }

        return locacoes.stream().map(locacaoMapper::toResponse).toList();
    }

    public List<LocacaoResponse> listarLocacoesPorUsuario(Long idUsuario){

        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));

        List<Locacao> locacoes = locacaoRepository.findByUsuarioId(idUsuario);

        if (locacoes.isEmpty()){
            throw new EntidadeNaoEncontradaException("Esse usuário não possui locações.");
        }

        return locacoes.stream().map(locacaoMapper::toResponse).toList();
    }

    public LocacaoResponse devolverFilme(Long idLocacao){

        Locacao locacao = locacaoRepository.findById(idLocacao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Locação não encontrada."));

        if (Boolean.TRUE.equals(locacao.isDevolvido())){
            throw new RegraDeNegocioException("Filme já devolvido.");
        }

        locacao.setDevolvido(true);

        Filme filme = locacao.getFilme();
        filme.setQuantidadeEstoque(filme.getQuantidadeEstoque() + 1);

        return locacaoMapper.toResponse(locacao);
    }
}