package com.gft.entities.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gft.entities.Grupo;
import com.gft.entities.Participante;

public class GrupoTest {
	private Grupo grupo;

	private List<Participante> participantes;

	private Participante p1, p2, p3, p4;

	@BeforeEach
	public void setUp() {
		grupo = new Grupo();

		p1 = new Participante();

		p2 = new Participante();

		p3 = new Participante();

		p4 = new Participante();

		participantes = new ArrayList<>();
		participantes.add(p1);
		participantes.add(p2);
		participantes.add(p3);
		participantes.add(p4);
		
		grupo.setParticipantes(participantes);
		
	}

	@Test
	public void testar_nome() throws Exception {
		grupo.setNome("Starter Woman");
		assertEquals("Starter Woman", grupo.getNome());
	}

	@Test
	public void quantidade_partici_tenho_no_grupo() throws Exception {
		assertEquals(4, grupo.quantidadeParticipante());
	}
}
