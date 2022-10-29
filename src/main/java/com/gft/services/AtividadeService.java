package com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.entities.Atividade;
import com.gft.repositories.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;


	public Atividade create(Atividade atividade) {
		//Long id = atividade.getEvento().getId_evento();
		//Optional<Evento> evento = eventoRepository.findById(id);
		//evento.get().setAtividadeNaLista(atividade);
		return atividadeRepository.save(atividade);
	}

	public List<Atividade> readyAll() {
		return atividadeRepository.findAll();

	}

	public Atividade readId(Long id) throws Exception {
		Optional<Atividade> atividade = atividadeRepository.findById(id);

		if (atividade.isEmpty()) {
			throw new Exception("Atividade não encontrada.");
		}

		return atividade.get();
	}

	public void delete(Long id) throws Exception {
		Atividade atividade = readId(id);
		atividade.setStatus(false);
		atividadeRepository.save(atividade);
	}

	public void deleteAtividade(Atividade atividade) {
		atividadeRepository.delete(atividade);
	}
}
