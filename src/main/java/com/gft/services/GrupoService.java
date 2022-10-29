package com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.entities.Grupo;
import com.gft.entities.Participante;
import com.gft.repositories.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo salvarGrupo(Grupo grupo) {
		grupo.pontuacaoTotalDoGrupo();
		return grupoRepository.save(grupo);
	}
	
	public List<Grupo> listarTodosOsGrupos(){
		return grupoRepository.findAll();
	}

	public List<Participante> listarTodosOsParticipantesDoGrupo(Long id) throws Exception{
		Grupo grupo = obterGrupo(id);
		return grupo.getParticipantes();
	}
	
	public List<Grupo> listarPorNomeGrupo(String nome){
		if(nome != null) {
			return grupoRepository.findByNomeContains(nome);
		}
		
		return listarTodosOsGrupos();
	}
	
	public Grupo obterGrupo(Long id) throws Exception{
		Optional<Grupo> grupo = grupoRepository.findById(id);
		
		if(grupo.isEmpty()) {
			throw new Exception("Grupo não encontrado");
		}
		
		return grupo.get();
	}
	
	public void excluir(Long id) throws Exception{
		Grupo grupo = obterGrupo(id);
		grupo.setStatus(false);
		grupoRepository.save(grupo);
	}

}
