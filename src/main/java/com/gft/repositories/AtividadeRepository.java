package com.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.entities.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
