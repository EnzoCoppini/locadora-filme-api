package com.locadora_filmes.repository;

import com.locadora_filmes.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {


    Optional<Filme> findByTituloIgnoreCase(String titulo);

    Boolean existsByTituloIgnoreCase(String titulo);
}
