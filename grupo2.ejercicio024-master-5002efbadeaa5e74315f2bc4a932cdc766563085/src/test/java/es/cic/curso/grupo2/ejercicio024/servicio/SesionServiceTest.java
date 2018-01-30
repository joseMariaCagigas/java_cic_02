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
public class SesionServiceTest {

	@Autowired
	SesionService sut;

	@PersistenceContext
	protected EntityManager em;
	
	Sesion sesion1;
	
	@Before
	public void setUp() throws Exception {
		
		double hora = 17.30;
		
		Pelicula pelicula = new Pelicula();
		em.persist(pelicula);
		
		Sala sala = new Sala(1,100);
		em.persist(sala);
		
		sesion1 = new Sesion(pelicula, sala, hora);
		em.persist(sesion1);
	}
	
	public SesionServiceTest(){
		super();
	}

	@Test
	public void testNuevaSesion() {
		Sesion sesion = crearSesion();
		
		assertNull(sesion.getId());
		
		sut.nuevaSesion(sesion);
		
		assertNotNull(sesion.getId());
	}

	@Test
	public void testLeerSesiones() {
		testNuevaSesion();
		
		List<Sesion> listaSesiones = sut.leerSesiones();
		
		assertTrue(listaSesiones.size() > 1);
	}

	@Test
	public void testEditaSesion() {
		Sesion sesion = crearSesion();
		
		sut.nuevaSesion(sesion);
		
		Long claveInicial = sesion.getId();
		
		assertEquals(sesion.getHora(), 17.30, 0.001);
		
		sesion.setHora(21.00);
		sut.editaSesion(claveInicial, sesion);
		em.flush();
		
		assertEquals(sesion.getHora(), 21.00, 0.001);
		
		Long clave = sesion.getId();
		
		Sesion sesionEnBBDD = em.find(Sesion.class, clave);
		
		assertEquals(clave, claveInicial);
		
		assertEquals(sesionEnBBDD.getHora(), 21.00, 0.001);
		assertNotEquals(sesionEnBBDD.getHora(), 17.30, 0.001);
	}

	@Test
	public void testBorraSesion() {
		Sesion sesion = crearSesion();
		
		sut.nuevaSesion(sesion);
		
		Long clave = sesion.getId();
		
		sut.borraSesion(clave);
		
		try{
			//Intenta Encontrar la Sesion Borrada
			Sesion s = em.find(Sesion.class, clave);
		}catch (PersistenceException pe){
			return;
		}
	}

	@Test
	public void testAbrirSesion() {
		Sesion sesion = crearSesion();
		
		sut.nuevaSesion(sesion);
		
		Long claveInicial = sesion.getId();
		
		assertTrue(sesion.isAbierta());
		
		sesion.setAbierta(false);
		
		sut.editaSesion(claveInicial, sesion);
		em.flush();
		
		Long clave = sesion.getId();
		
		Sesion sesionEnBBDD = em.find(Sesion.class, clave);
		
		assertEquals(clave, claveInicial);
		
		assertFalse(sesionEnBBDD.isAbierta());
		
		//Aqui empieza la operacion de abrir la sala.Primero se cierra porque por defecto se abre.
		
		sesion.setAbierta(true);
		
		sut.editaSesion(clave, sesion);
		
		clave = sesion.getId();
		
		sesionEnBBDD = em.find(Sesion.class, clave);
		
		assertTrue(sesionEnBBDD.isAbierta());
	}

	@Test
	public void testCerrarSesion() {
		Sesion sesion = crearSesion();
		
		sut.nuevaSesion(sesion);
		
		Long claveInicial = sesion.getId();
		
		assertTrue(sesion.isAbierta());
		
		sesion.setAbierta(false);
		
		sut.editaSesion(claveInicial, sesion);
		em.flush();
		
		Long clave = sesion.getId();
		
		Sesion sesionEnBBDD = em.find(Sesion.class, clave);
		
		assertEquals(clave, claveInicial);
		
		assertFalse(sesionEnBBDD.isAbierta());
	}
	
	private Sesion crearSesion(){
		double hora = 17.30;
		
		Pelicula pelicula = new Pelicula();
		em.persist(pelicula);
		
		Sala sala = new Sala(1,100);
		em.persist(sala);
		
		sesion1 = new Sesion(pelicula, sala, hora);
		
		return sesion1;
	}

}
