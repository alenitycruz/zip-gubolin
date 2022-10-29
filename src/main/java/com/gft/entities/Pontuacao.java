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
@Table(name = "tb_pontuacao")
public class Pontuacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pontuacao;

	private Boolean presenca;

	private Boolean atividade;

	private Boolean atraso;

	//@OneToMany
	//@JoinColumn(name = "id_evento")
	//private List<Evento> eventos = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "id")
	private List<Participante> participantes = new ArrayList<>();

	public int calcularParticipante() {
		int total = 0;
		if (this.presenca == true) {
			total += 10;
		}

		if (this.atividade == true) {
			total += 5;
		}

		if (this.atraso == true) {
			total -= 2;
		}
		return total;
	}

	public Long getId_pontuacao() {
		return id_pontuacao;
	}

	public void setId_pontuacao(Long id_pontuacao) {
		this.id_pontuacao = id_pontuacao;
	}

	public Boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

	public Boolean getAtividade() {
		return atividade;
	}

	public void setAtividade(Boolean atividade) {
		this.atividade = atividade;
	}

	public Boolean getAtraso() {
		return atraso;
	}

	public void setAtraso(Boolean atraso) {
		this.atraso = atraso;
	}

	/*public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}*/

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
