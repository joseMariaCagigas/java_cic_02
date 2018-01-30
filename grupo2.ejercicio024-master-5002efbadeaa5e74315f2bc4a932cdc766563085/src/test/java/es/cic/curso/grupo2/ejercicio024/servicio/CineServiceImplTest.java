package es.cic.curso.grupo2.ejercicio024.servicio;

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

import es.cic.curso.grupo2.ejercicio024.dominio.Pelicula;
import es.cic.curso.grupo2.ejercicio024.dominio.Sala;
import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo2.ejercicio024.dominio.Venta;
import es.cic.curso.grupo2.ejercicio024.servicio.CineServiceImpl.NotEnoughException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class CineServiceImplTest {

	private Pelicula peli1;
	private Pelicula peli2;
	private Pelicula peli3;
		
	private Sala sala1;
	private Sala sala2;
	private Sala sala3;
		
	private Sesion sesion1;
	private Sesion sesion2;
	private Sesion sesion3;
	
	private Sesion sesion4;
	private Sesion sesion5;
	private Sesion sesion6;
	
	private Sesion sesion7;
	private Sesion sesion8;
	private Sesion sesion9;
	
	@Autowired
	SalaService salaService;
	
	@Autowired
	SesionService sesionService;
	
	@Autowired
	PeliculaService peliculaService;
	
	@Autowired
	VentaService ventaService;
	
	@Autowired
	CineService cineService;
	
	@Before
	public void setUp() throws Exception {
		peli1 = new Pelicula("Torrente");
		peli2 = new Pelicula("Red");
		peli3 = new Pelicula("Red 2");
		
		peliculaService.nuevaPelicula(peli1);
		peliculaService.nuevaPelicula(peli2);
		peliculaService.nuevaPelicula(peli3);
		
		sala1 = new Sala(1, 100);
		sala2 = new Sala(2, 50);
		sala3 = new Sala(3, 30);
		
		salaService.nuevaSala(sala1);
		salaService.nuevaSala(sala2);
		salaService.nuevaSala(sala3);
		
		sesion1 = new Sesion(peli1, sala1, 17.00);
		sesion2 = new Sesion(peli1, sala1, 19.00);
		sesion3 = new Sesion(peli1, sala1, 21.00);
		
		sesion4 = new Sesion(peli2, sala2, 17.00);
		sesion5 = new Sesion(peli2, sala2, 19.00);
		sesion6 = new Sesion(peli2, sala2, 21.00);
		
		sesion7 = new Sesion(peli3, sala3, 17.00);
		sesion8 = new Sesion(peli3, sala3, 19.00);
		sesion9 = new Sesion(peli3, sala3, 21.00);
		
		sesionService.nuevaSesion(sesion1);
		sesionService.nuevaSesion(sesion2);
		sesionService.nuevaSesion(sesion3);
		sesionService.nuevaSesion(sesion4);
		sesionService.nuevaSesion(sesion5);
		sesionService.nuevaSesion(sesion6);
		sesionService.nuevaSesion(sesion7);
		sesionService.nuevaSesion(sesion8);
		sesionService.nuevaSesion(sesion9);
	}

	@Test
	public void testVenderEntradas_si() throws NotEnoughException {
		Long ventaId = cineService.venderEntradas(sesion1.getId(), 1);
		Venta venta = ventaService.leeVenta(ventaId);
		
		assertTrue(venta.getId() != null);
	}
	
	  @Test(expected = NotEnoughException.class)
	public void testVenderEntradas_no() throws NotEnoughException {
		Long ventaId = cineService.venderEntradas(sesion1.getId(), 101);
		Venta venta = ventaService.leeVenta(ventaId);
		
		assertTrue(venta.getId() != null);
	}

	@Test
	public void testListarVentas() throws NotEnoughException {
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		
		List<Venta>ventas = ventaService.getVentas();
		
		assertTrue(ventas.size() >= 4);
	}

	@Test
	public void testRecaudarSala() throws NotEnoughException {
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		
		double recaudacionSala = cineService.recaudarSala(sala1.getId());
		
		assertTrue(recaudacionSala > 0);

	}

	@Test
	public void testRecaudarCine() throws NotEnoughException {
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		cineService.venderEntradas(sesion1.getId(), 1);
		
		double recaudacion = cineService.recaudarCine();
		
		assertTrue(recaudacion > 0);
	}

	@Test
	public void testDevolver() throws NotEnoughException {
		Long ventaId1 = cineService.venderEntradas(sesion1.getId(), 1);
		
		double importeADevolver = cineService.devolver(ventaId1);
		
		Venta venta = ventaService.leeVenta(ventaId1);
		
		assertNull(venta);
		assertTrue(importeADevolver == 5);
	}

	@Test
	public void testCambio() throws NotEnoughException {
		Long ventaId1 = cineService.venderEntradas(sesion1.getId(), 1);
		
		cineService.cambio(ventaId1, sesion2.getId());
		
		Venta venta = ventaService.leeVenta(ventaId1);
		
		assertTrue(venta.getSesionEntrada().getId() == sesion2.getId());
	}

	
	public void limpiarVentas(){
		
	}
	
	
}
