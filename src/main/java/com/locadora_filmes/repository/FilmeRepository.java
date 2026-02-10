package com.locadora_filmes.repository;

import com.locadora_filmes.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {


}
