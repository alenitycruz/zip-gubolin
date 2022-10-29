package com.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.entities.Pontuacao;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {

}
