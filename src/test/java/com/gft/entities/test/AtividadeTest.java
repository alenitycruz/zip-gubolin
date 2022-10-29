package com.gft.entities.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gft.entities.Atividade;

public class AtividadeTest {

	private Atividade atividade;

	@BeforeEach
	public void setUp() {
		atividade = new Atividade();
	}

	@Test
	public void testarInjecaoDeValorNoAtributoNome() throws Exception {
		atividade.setNome("Opera Classica");
		assertEquals("Opera Classica", atividade.getNome());
	}

	@Test
	public void testarInjecaoDeValorNoAtributoDataInicio() throws Exception {
		atividade.setData_inicio(null);
		assertTrue(atividade.getData_inicio() == null);
	}

	@Test
	public void testarInjecaoDeValorNoAtributoDataEntrega() throws Exception {

		atividade.setData_entrega(new Date());
		assertEquals(new Date(), atividade.getData_entrega());
	}
}
