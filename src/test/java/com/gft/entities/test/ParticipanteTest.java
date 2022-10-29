package com.gft.entities.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gft.entities.Participante;

//@SpringBootTest
public class ParticipanteTest {

	private Participante participante;

	@BeforeEach
	public void setUp() {
		participante = new Participante();
	}

	@Test
	public void testar_nome_participante() throws Exception {
		participante.setNome("Amanda");
		assertEquals("Amanda", participante.getNome());
	}

	@Test
	public void testar_nivel_participante() throws Exception {
		participante.setNivel(" ");
		assertTrue(participante.getNivel() != null);
	}

	@Test
	public void testar_email_participante() throws Exception {
		participante.setEmail(" ");
		assertTrue(participante.getEmail() != null);
	}

	@Test
	public void testar_4letras_participante() throws Exception {
		participante.setQuatro_letras("eaoa");
		assertEquals(4, participante.getQuatro_letras().length());
	}

}
