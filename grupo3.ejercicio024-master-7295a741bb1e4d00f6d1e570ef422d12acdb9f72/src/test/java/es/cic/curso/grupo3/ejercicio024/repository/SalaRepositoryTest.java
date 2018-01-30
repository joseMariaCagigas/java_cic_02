package es.cic.curso.grupo3.ejercicio024.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.helper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo3/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class SalaRepositoryTest {
	
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private TestHelper tHelper;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testAdd() {
		Sala sala = new Sala();
		sala.setNumSesion(1);
		assertNull(sala.getId());

		salaRepository.add(sala);

		assertNotNull(sala.getId());
	}
	
	@Test
	public void testRead() {
		Long clavePrimaria = tHelper.generaSalaUnoSesionUno();

		Sala resultado = salaRepository.read(clavePrimaria);

		assertEquals(1, resultado.getNumSesion());
	}
	
	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Sala resultado = salaRepository.read(clavePrimaria);

		assertEquals(3, resultado.getNumSesion());
	}

	@Test
	public void testList() {
		tHelper.generaSalaUnoSesionUno();
		tHelper.generaSalaUnoSesionUno();
		tHelper.generaSalaUnoSesionUno();

		List<Sala> resultado = salaRepository.list();

		assertTrue(resultado.size()>= 3);
	}
	
	@Test
	public void testDelete() {
		Long clavePrimaria = tHelper.generaSalaUnoSesionUno();

		salaRepository.delete(clavePrimaria);
		Sala s;
		try {
			s  = em.find(Sala.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(s);
	}
	
	@Test
	public void testUpdate() {
		Long clavePrimaria = tHelper.generaSalaUnoSesionUno();

		Sala sala2 = new Sala();
		sala2.setId(clavePrimaria);
		sala2.setNumSesion(7);
		sala2.setNumSala(5);

		Sala resultado = salaRepository.update(sala2);

		Sala enBBDD = em.find(Sala.class, clavePrimaria);
		assertEquals(7, enBBDD.getNumSesion());
		assertEquals(7, resultado.getNumSesion());
		assertEquals(5, enBBDD.getNumSala());
		assertEquals(5, resultado.getNumSala());
	}
}