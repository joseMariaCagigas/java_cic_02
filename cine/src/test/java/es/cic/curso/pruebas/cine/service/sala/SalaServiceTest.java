package es.cic.curso.pruebas.cine.service.sala;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sala.SalaRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/pruebas/cine/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class SalaServiceTest {
	
	@Autowired
	SalaService sut;
	
	@Autowired
	SalaRepository salaRepository;

	@Before
	public void setUp() throws Exception {
		limpiarSalas();
	}

	private void limpiarSalas() {
		List<Sala> salas = sut.obtenerSalas();
		for(Sala sala : salas){
			salaRepository.delete(sala);
		}
		
	}

	@Test
	public void testAniadirSala() {
		
		Long idSala = sut.aniadirSala(90);
		assertNotNull(idSala);
	}

	@Test
	public void testObtenerSalas() {
		List<Sala>salas = sut.obtenerSalas();
		for(Sala sala : salas){
			assertNotNull(sala.getId());
		}
	}

	@Test
	public void testObtenerSala() {
		Long idSala = sut.aniadirSala(100);
		assertNotNull(idSala);
		
		Sala sala = sut.obtenerSala(idSala);
		
		assertNotNull(sala.getId());
		assertTrue(sala.getAforo() == 100);
	}

	@Test
	public void testActualizarSala() {
		Long idSala = sut.aniadirSala(90);
		assertNotNull(idSala);
		
		Sala sala = sut.obtenerSala(idSala);
		sala.setAforo(50);
		Sala salaActualizada = sut.obtenerSala(idSala);
		
		assertTrue(salaActualizada.getAforo() == 50);
		assertNotNull(salaActualizada);
	}

	@Test
	public void testBorrarSala() {
		Long idSala = sut.aniadirSala(90);
		assertNotNull(idSala);
		
		sut.borrarSala(idSala);
		
		List<Sala>salas = sut.obtenerSalas();
		
		assertTrue(salas.size() == 0);
		
	}

}
