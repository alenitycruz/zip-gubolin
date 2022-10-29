package com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.entities.Grupo;
import com.gft.entities.Participante;
import com.gft.repositories.GrupoRepository;
import com.gft.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {
	@Autowired
	private ParticipanteRepository participanteRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	public Participante create(Participante participante) {
		return participanteRepository.save(participante);
	}

	public Participante createWithId(Participante participante, Long id) {
		Optional<Grupo> grupo = grupoRepository.findById(id);
		participante.setGrupo(grupo.get());
		return participanteRepository.save(participante);
	}

	public List<Participante> readyAll() {
		return participanteRepository.findAll();
	}

	public Participante readId(Long id) throws Exception {
		Optional<Participante> participante = participanteRepository.findById(id);

		if (participante.isEmpty()) {
			throw new Exception("Participante não encontrado.");
		}

		return participante.get();
	}

	public void delete(Long id) throws Exception {
		Participante participante = readId(id);
		participante.setStatus(false);
		participanteRepository.save(participante);
	}
}
