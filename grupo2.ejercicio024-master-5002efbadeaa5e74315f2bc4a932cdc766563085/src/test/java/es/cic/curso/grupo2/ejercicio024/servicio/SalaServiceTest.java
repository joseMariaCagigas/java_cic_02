package es.cic.curso.grupo2.ejercicio024.servicio;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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

import es.cic.curso.grupo2.ejercicio024.dominio.Sala;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	 TransactionalTestExecutionListener.class})
@Transactional
public class SalaServiceTest {

	@Autowired
	SalaService sut;

	@PersistenceContext
	protected EntityManager em;
	
	Sala sala1;
	
	@Before
	public void setUp() throws Exception {
		sala1 = new Sala(1,100);
		em.persist(sala1);
	}

	public SalaServiceTest(){
		super();
	}
	
	@Test
	public void testNuevaSala() {
		Sala sala = generarNuevaSala();
		
		assertNull(sala.getId());
		
		sut.nuevaSala(sala);
		
		assertNotNull(sala.getId());
	}

	@Test
	public void testLeerSala() {
		testNuevaSala();
		
		List<Sala> listaSalas = sut.leerSala();
		
		assertTrue(listaSalas.size() > 1);
	}

	@Test
	public void testEditaSala() {
		Sala sala = generarNuevaSala();
		
		sut.nuevaSala(sala);
		
		Long claveInicial = sala.getId();
		
		assertEquals(sala.getAsientosTotales(), 100);
		
		sala.setAsientosTotales(200);
		sut.editaSala(claveInicial, sala);
		em.flush();
		
		assertEquals(sala.getAsientosTotales(), 200);
		
		Long clave = sala.getId();
		
		Sala salaEnBBDD = em.find(Sala.class, clave);
		
		assertEquals(clave, claveInicial);
		
		assertEquals(salaEnBBDD.getAsientosTotales(), 200);
		assertNotEquals(salaEnBBDD.getAsientosTotales(), 100);
	}

	@Test
	public void testBorraSala() {
		Sala sala = generarNuevaSala();
		
		sut.nuevaSala(sala);
		
		Long clave = sala.getId();
		
		sut.borraSala(clave);
		
		try{
			//Intenta Encontrar la Sesion Borrada
			Sala s = em.find(Sala.class, clave);
		}catch (PersistenceException pe){
			return;
		}
	}
	
	private Sala generarNuevaSala(){
		sala1 = new Sala(1,100);
		
		return sala1;
	}

}
