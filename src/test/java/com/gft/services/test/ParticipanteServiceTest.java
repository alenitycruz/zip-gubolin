package com.gft.services.test;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gft.entities.Participante;
import com.gft.repositories.ParticipanteRepository;
import com.gft.services.ParticipanteService;

@DisplayName("ParticipanteServiceTest")
public class ParticipanteServiceTest extends AplicationConfigTest {

	@MockBean
	private ParticipanteRepository participanteRepository;

	@MockBean
	private Participante participante;

	@Autowired
	private ParticipanteService participanteService;

	@BeforeEach
	public void setUp() {
		participante = new Participante();

	}

	@Test
	@DisplayName("salvando participante ☺")
	public void testar_se_participante_foi_salva() {

		Participante participante = Mockito.mock(Participante.class);

		participanteService.create(participante);

		Mockito.verify(participanteRepository, Mockito.times(1)).save(ArgumentMatchers.any(Participante.class));

	}

	@Test
	@DisplayName("excluindo participante ☺")
	public void testar_se_participante_foi_excluida() throws Exception {

		Long participanteId = 1l;

		Participante participante = Mockito.mock(Participante.class);

		Optional<Participante> ativ = Optional.of(participante);

		Mockito.when(participanteRepository.findById(ArgumentMatchers.eq(participanteId))).thenReturn(ativ);

		participanteService.delete(participanteId);

		Mockito.verify(participanteRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any(Long.class));

		participanteService.delete(participanteId);

		Mockito.verify(participanteRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Participante.class));

	}
}
