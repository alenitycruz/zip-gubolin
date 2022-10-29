package com.gft.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_participante")
public class Participante implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_participante;
	
	private String nome;
	
	private String nivel;
	
	private String email;

	private String quatro_letras;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;

	private boolean status;

	private boolean[] marcar_presencas;

	private boolean[] atividades_realizadas;

	private boolean[] atrasos;
	
	public int indiceDoArrayMarcarPresenca() {
		Evento auxEvento = new Evento();
		
		int i = (int) auxEvento.totalDiasDoEvento();

		this.marcar_presencas = new boolean[i];

		return this.marcar_presencas.length;

	}
	
	public void arrayMarcarPresenca() {
		Participante pontuacaoAux = new Participante();
		
		int i = pontuacaoAux.indiceDoArrayMarcarPresenca();
		this.marcar_presencas = new boolean[i];
	}

	@SuppressWarnings("unchecked")
	public int indiceDoArrayAtividadesRealizadas() {
		
		int i = 0;
		
		List<Atividade> atividades = new ArrayList<>();
		@SuppressWarnings("rawtypes")
		List<Evento> eventos = new ArrayList(atividades);
		
		grupo.setEventos(eventos);
		
		
		i = atividades.size();
		
		return i;
		
	}
	
	
	public void arrayAtividadesRealizadas() {
		Participante pontuacaoAux = new Participante();
		int i = pontuacaoAux.indiceDoArrayAtividadesRealizadas();
		this.atividades_realizadas = new boolean[i];
	}

	
	public void arrayAtraso() {
		Participante pontuacaoAux = new Participante();
		
		int i = pontuacaoAux.indiceDoArrayMarcarPresenca();
		this.atrasos = new boolean[i];
	}
	
	public int totalPontuacaoPorParticipante() {

		int totalPontos = 0;

		for (boolean presenca : this.marcar_presencas) {
			if (presenca == true) {
				totalPontos += 10;
			}
		}
		
		for (boolean atividade : this.atividades_realizadas) {
			if(atividade == true) {
				totalPontos += 5;
			}
		}
		
		for (boolean atraso : this.atrasos ) {
			if (atraso == true) {
				totalPontos -= 2;
			}
		}

		return totalPontos;
		
	}
	
	public boolean presencaBonus() {
		boolean result = false;
		
		int i = 0;
		
		for (boolean presenca : this.marcar_presencas) {
			if (presenca == true) {
				i++;
			}
		}
		
		if(i == this.marcar_presencas.length) {
			result = true;
		}
		
		return result;
	}
	
	public boolean atividadeBonus() {
		boolean result = false;
		
		int i = 0;
		
		for (boolean atividade : this.atividades_realizadas) {
			if(atividade == true) {
				i++;
			}
		}
		
		if(i == this.atividades_realizadas.length) {
			result = true;
		}
		
		return result;
	}

	public Long getId_participante() {
		return id_participante;
	}

	public void setId_participante(Long id_participante) {
		this.id_participante = id_participante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuatro_letras() {
		return quatro_letras;
	}

	public void setQuatro_letras(String quatro_letras) {
		this.quatro_letras = quatro_letras;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean[] getMarcar_presencas() {
		return marcar_presencas;
	}

	public void setMarcar_presencas(boolean[] marcar_presencas) {
		this.marcar_presencas = marcar_presencas;
	}

	public boolean[] getAtividades_realizadas() {
		return atividades_realizadas;
	}

	public void setAtividades_realizadas(boolean[] atividades_realizadas) {
		this.atividades_realizadas = atividades_realizadas;
	}

	public boolean[] getAtrasos() {
		return atrasos;
	}

	public void setAtrasos(boolean[] atrasos) {
		this.atrasos = atrasos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
