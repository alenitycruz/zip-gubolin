package com.gft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tb_atividade")
public class Atividade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_atividade;

	private String nome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_inicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_entrega;

	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId_atividade() {
		return id_atividade;
	}

	public void setId_atividade(Long id_atividade) {
		this.id_atividade = id_atividade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}