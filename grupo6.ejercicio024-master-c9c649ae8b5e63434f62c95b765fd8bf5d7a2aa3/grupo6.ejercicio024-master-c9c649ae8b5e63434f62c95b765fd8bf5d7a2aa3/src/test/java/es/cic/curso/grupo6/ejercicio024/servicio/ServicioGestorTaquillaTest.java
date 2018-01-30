package es.cic.curso.grupo6.ejercicio024.servicio;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

import es.cic.curso.grupo6.ejercicio024.RecaudacionDTO;
import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/grupo6.ejercicio024/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ServicioGestorTaquillaTest {
	
	public static final int CAPACIDAD_SALA = 100;
	public static final int ENTRADAS = 10;

	@Autowired
	private ServicioGestorTaquilla sut;

	@PersistenceContext
	private EntityManager em;
	
	
	private Sesion sesion;
	private Sala sala;
	@Before
	public void setUp() {
		sala = new Sala();
		sala.setCapacidad(CAPACIDAD_SALA);
		sala.setAbierto(true);
		em.persist(sala);
		em.flush();
		sesion = new Sesion();
		sesion.setSala(sala);
		em.persist(sesion);
		em.flush();	
	}

	@Test
	public void testRecaudacion() throws Exception{
		sut.vendeEntradas(sesion.getId(), 4);
		int s = sesion.getAsientosOcupados();
		float f = sut.calculaRecaudacion(sala.getId());
		RecaudacionDTO dto = new RecaudacionDTO(sala.getId(),f);
		assertEquals(s,4);
		assertEquals(f,20.0,0.000001);
		assertEquals(dto.getRecaudacion(),20.0,0.000001);
	}

	@Test
	public void testVendeEntradas() throws Exception {
		sut.vendeEntradas(sesion.getId(), ENTRADAS);
		assertEquals(ServicioGestorTaquillaImpl.PRECIO_ENTRADA * ENTRADAS, sut.calculaRecaudacion(),  0.0001);
		assertEquals(CAPACIDAD_SALA - ENTRADAS, sut.localidadesDisponibles(sesion.getId()));		
	}
}

