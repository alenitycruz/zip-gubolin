package com.gft.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_evento")
public class Evento implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;
   
	private String nome;
   
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;
  
	@ManyToMany(mappedBy = "eventos")
    private List<Grupo> grupos;
    
	@OneToMany
	@JoinColumn(name = "id_atividade")
    private List<Atividade> atividades;
    
	private boolean status;

	public long totalDiasDoEvento() {

		long days = Math.abs(this.dataFinal.getTime() - this.dataInicio.getTime());
		
		return days;
	}

	public Long getId_evento() {
		return id_evento;
	}

	public void setId_evento(Long id_evento) {
		this.id_evento = id_evento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public void setAtividadeNaLista(Atividade atividade) {
		this.atividades.add(atividade);
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