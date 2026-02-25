package com.locadora_filmes.repository;

import com.locadora_filmes.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    List<Locacao> findByUsuarioIdAndDevolvidoFalse(Long usuarioId);
    List<Locacao> findByUsuarioId(Long usuarioId);


}
