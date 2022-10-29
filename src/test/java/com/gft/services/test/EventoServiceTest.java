package com.gft.services.test;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gft.entities.Evento;
import com.gft.repositories.EventoRepository;
import com.gft.services.EventoService;

@DisplayName("EventoServiceTest")
public class EventoServiceTest extends AplicationConfigTest {

	@MockBean
	private EventoRepository eventoRepository;

	@MockBean
	private Evento evento;

	@Autowired
	private EventoService eventoService;

	@BeforeEach
	public void setUp() {
		evento = new Evento();

	}

	@Test
	@DisplayName("salvando evento ☺")
	public void testar_se_evento_foi_salvo() {

		Evento evento = Mockito.mock(Evento.class);

		eventoService.salvarEvento(evento);

		Mockito.verify(eventoRepository, Mockito.times(1)).save(ArgumentMatchers.any(Evento.class));

	}

	@Test
	@DisplayName("excluindo evento ☺")
	public void testar_se_evento_foi_excluido() throws Exception {

		Long eventoId = 1l;

		Evento evento = Mockito.mock(Evento.class);

		Optional<Evento> even = Optional.of(evento);

		Mockito.when(eventoRepository.findById(ArgumentMatchers.eq(eventoId))).thenReturn(even);

		eventoService.excluirEvento(eventoId);

		Mockito.verify(eventoRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any(Long.class));

		eventoService.excluirEvento(eventoId);
	

		Mockito.verify(eventoRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Evento.class));

	}
}
