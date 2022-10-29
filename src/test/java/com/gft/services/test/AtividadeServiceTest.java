package com.gft.services.test;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;

import com.gft.entities.Atividade;
import com.gft.repositories.AtividadeRepository;
import com.gft.services.AtividadeService;

@DisplayName("AtividadeServiceTest")
public class AtividadeServiceTest extends AplicationConfigTest {

	private String nome = "Bruno";
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_inicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_entrega;

	@MockBean
	private AtividadeRepository atividadeRepository;

	@MockBean
	private Atividade atividade;

	@Autowired
	private AtividadeService atividadeService;

	@BeforeEach
	public void setUp() {
		atividade = new Atividade();
		atividade.setNome(nome);
		atividade.setData_inicio(data_inicio);
		atividade.setData_entrega(data_entrega);
	}

	@Test
	@DisplayName("salvando atividade ☺")
	public void testar_se_atividade_foi_salva() {

		Atividade atividade = Mockito.mock(Atividade.class);

		atividadeService.create(atividade);

		Mockito.verify(atividadeRepository, Mockito.times(1)).save(ArgumentMatchers.any(Atividade.class));

	}

	@Test
	@DisplayName("excluindo atividade ☺")
	public void testar_se_atividade_foi_excluida() throws Exception {

		Long atividadeId = 1l;

		Atividade atividade = Mockito.mock(Atividade.class);

		Optional<Atividade> ativ = Optional.of(atividade);

		Mockito.when(atividadeRepository.findById(ArgumentMatchers.eq(atividadeId))).thenReturn(ativ);

		atividadeService.delete(atividadeId);

		Mockito.verify(atividadeRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any(Long.class));

		atividadeService.deleteAtividade(atividade);

		Mockito.verify(atividadeRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Atividade.class));

	}
}
