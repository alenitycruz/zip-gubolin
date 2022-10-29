package com.gft.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_grupo")
public class Grupo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_grupo;
	
	private String nome;
	
	@OneToMany(mappedBy = "grupo")
	private List<Participante> participantes = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
            name = "tb_grupos_eventos",
              joinColumns = {@JoinColumn(name = "id_evento")}
            , inverseJoinColumns = {@JoinColumn(name = "id_grupo")}
        )
	private List<Evento> eventos = new ArrayList<>();
	
	private boolean status;

	public void setParticipantesNaLista(Participante participante) {
		participantes.add(participante);
	}
	
	public Integer quantidadeParticipante() {
		return participantes.size();
	}
	
	public int pontuacaoTotalDoGrupo() {
		int total = 0;
		int i = 0;
		int a = 0;
		
		for(Participante participante : participantes) {
			total += participante.totalPontuacaoPorParticipante();
		}
		
		for(Participante participante : participantes) {
			if(participante.presencaBonus() == true) {
				i++;
			}
		}
		
		if(i == participantes.size()) {
			total += 5;
		}
		
		for(Participante participante : participantes) {
			if(participante.atividadeBonus() == true) {
				a++;
			}
		}
		
		if(a == participantes.size()) {
			total += 5;
		}
		
		return total;
	}

	public Long getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Long id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
