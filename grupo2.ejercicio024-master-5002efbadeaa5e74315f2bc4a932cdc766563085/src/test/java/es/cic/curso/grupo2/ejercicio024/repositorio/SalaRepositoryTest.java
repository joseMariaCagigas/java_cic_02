package es.cic.curso.grupo2.ejercicio024.repositorio;

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

import es.cic.curso.grupo2.ejercicio024.dominio.Pelicula;
import es.cic.curso.grupo2.ejercicio024.dominio.Sala;
import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	 TransactionalTestExecutionListener.class})
@Transactional
public class SalaRepositoryTest {

	@Autowired
	SalaRepository sut;

	@PersistenceContext
	protected EntityManager em;
	
	Sala sala1;
	
	@Before
	public void setUp()
	{
		sala1 = new Sala(1,100);
		em.persist(sala1);
	}
	
	public SalaRepositoryTest(){
		super();
	}

	@Test
	public void testAdd() {
		sut.add(sala1);
		
		assertNotNull(sala1.getId());
	}

	@Test
	public void testReadK() {
		Sala sala = crearSalaParaTestRetornoSala();
		
		Long clave = sala.getId();
		
		Sala sala1 = sut.read(clave);
		
		assertNotNull(sala1.getId());
	}

	@Test
	public void testReadT() {
		Sala sala = crearSalaParaTestRetornoSala();
		
		Sala sala1 = sut.read(sala);
		
		assertNotNull(sala1.getId());
	}

	@Test
	public void testList() {
		Sala sala = crearSalaParaTestRetornoSala();
		
		List<Sala> listaSalas = sut.list();
		
		assertTrue(listaSalas.size() > 1);
	}

	@Test
	public void testUpdate() {
		Sala sala = crearSalaParaTestRetornoSala();
		
		Long claveInicial = sala1.getId();
		
		sut.add(sala);
		
		assertEquals(sala.getAsientosTotales(), 100);
		
		sala.setAsientosTotales(200);
		Sala sala1 = sut.update(sala);
		em.flush();
		
		assertEquals(sala1.getAsientosTotales(), 200);
		
		Long clave = sala1.getId();
		
		Sala salaEnBBDD = em.find(Sala.class, clave);
		
		assertEquals(clave, claveInicial);
		
		assertEquals(salaEnBBDD.getAsientosTotales(), 200);
		assertNotEquals(salaEnBBDD.getAsientosTotales(), 100);
	}

	@Test
	public void testDeleteK() {
		Sala sala = crearSalaParaTestRetornoSala();
		
		Long clave = sala.getId();
		
		sut.delete(clave);
		
		try{
			//Intenta Encontrar la Sesion Borrada
			Sala s = em.find(Sala.class, clave);
		}catch (PersistenceException pe){
			return;
		}
	}

	@Test
	public void testDeleteT() {
		Sala sala = crearSalaParaTestRetornoSala();
		
		Long clave = sala.getId();
		
		sut.delete(sala);
		
		try{
			//Intenta Encontrar la Sesion Borrada
			Sala s = em.find(Sala.class, clave);
		}catch (PersistenceException pe){
			return;
		}
	}
	
	private Sala crearSalaParaTestRetornoSala(){
		sala1 = new Sala(1,100);
		em.persist(sala1);
		
		return sala1;
	}

}
