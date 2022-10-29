package com.gft.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "raking_tb")
public class Ranking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_raking;
	
	@OneToMany
	@JoinColumn(name = "id_pontuacao")
	private List<Pontuacao> pontuacoes = new ArrayList<>();
	
	@OneToMany
	//@JoinColumn(name = "id_grupo")
	private List<Grupo> grupos = new ArrayList<>();
	
	public Integer calcularGrupo() {

		Integer total = 0;
		for(Grupo grupo : grupos) {
		for(Pontuacao pontuacao : pontuacoes) {
			
				if(grupo.getParticipantes() == pontuacao.getParticipantes()) {
					total += pontuacao.calcularParticipante();
				}
			}
		}

		return total;
	}


	public Long getId_raking() {
		return id_raking;
	}

	public void setId_raking(Long id_raking) {
		this.id_raking = id_raking;
	}

	public List<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	public void setPontuacoes(List<Pontuacao> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	

}
