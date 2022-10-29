package com.gft.entities.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gft.entities.Evento;

public class EventoTest {
	private Evento evento;

	@BeforeEach
	public void setUp() {
		evento = new Evento();
	}

	@Test
	public void testarInjecaoDeValorNoAtributoNome() throws Exception {
		evento.setNome("Circo");
		assertEquals("Circo", evento.getNome());
	}

	@Test
	public void testarInjecaoDeValorNoAtributoDataInicio() throws Exception {
		evento.setDataInicio(null);
		assertTrue(evento.getDataInicio() == null);
	}

	@Test
	public void testarInjecaoDeValorNoAtributoDataEntrega() throws Exception {

		evento.setDataFinal(new Date());
		assertEquals(new Date(), evento.getDataFinal());
	}
}
