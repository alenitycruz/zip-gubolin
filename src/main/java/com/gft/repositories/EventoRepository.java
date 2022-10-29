package com.gft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
    List<Evento> findByNomeContains(String nome);
}
