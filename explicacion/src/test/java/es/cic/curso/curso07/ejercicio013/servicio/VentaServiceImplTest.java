package es.cic.curso.curso07.ejercicio013.servicio;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso07.ejercicio013.dominio.Sala;
import es.cic.curso.curso07.ejercicio013.dominio.SalaRepository;
import es.cic.curso.curso07.ejercicio013.dominio.Venta;
import es.cic.curso.curso07.ejercicio013.dominio.VentaRepository;
import es.cic.curso.curso07.ejercicio013.servicio.VentaException;
import es.cic.curso.curso07.ejercicio013.servicio.VentaService;
import es.cic.curso.curso07.ejercicio013.servicio.VentaServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso07/ejercicio013/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class VentaServiceImplTest {
	private static final double DELTA_CANTIDAD = 0.001;

	private Sala sala1;
	private Sala sala2;
	private Sala sala3;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private VentaService sut;
	
	@Autowired
	private SalaRepository salaRepository;

	
	@Autowired
	private VentaRepository ventaRepository;	
	
	@Before
	public void setUp() throws Exception {

		limpiarSalas();
		
		inicializaBaseDeDatos();
	}



	@Test
	public void testCalcularPrecio() {
		int numeroEntradas = 4;
		
		double resultado = sut.calcularPrecio(numeroEntradas);
		
		assertEquals(20.0, resultado, 0.001);
	}
	
	@Test
	public void testVentaCompleta() {
		long sala2Id = sala2.getId();
		
		
		assertEquals(false, sut.hayButacasLibres(sala2Id, 3));
		assertEquals(true, sut.hayButacasLibres(sala2Id, 2));
		sut.comprarEntradas(sala2Id, 2, (short)1);
		
		assertEquals(2,getOcupadosSesion2(sala2Id));
	
		double recaudacion = sut.calcularRecaudacion();
		assertEquals(510.0, recaudacion, DELTA_CANTIDAD);
	}

	

	@Test
	public void RecaudacionSalastest() throws FileNotFoundException, IOException {
		
		assertEquals(75.0, sut.calcularRecaudacionSala(sala1.getId()), DELTA_CANTIDAD);

		assertEquals(255.0, sut.calcularRecaudacionSala(sala2.getId()), DELTA_CANTIDAD);
		
		assertEquals(175.0, sut.calcularRecaudacionSala(sala3.getId()), DELTA_CANTIDAD);
	}
	

	@Test
	public void testHayUnaButacaLibre() throws FileNotFoundException, IOException {
		long sala2Id = sala2.getId();
		assertEquals(false, sut.hayButacasLibres(sala2Id, 3));
		assertEquals(true, sut.hayButacasLibres(sala2Id, 1));
	}
	
	@Test
	public void testHayButacasLibre() throws FileNotFoundException, IOException {
		long sala2Id = sala2.getId();
		assertEquals(false, sut.hayButacasLibres(50 , sala2Id, 2));
		assertEquals(true, sut.hayButacasLibres(49, sala2Id, 2));
	}

	
	@Test
	public void testComprarEntradas() throws FileNotFoundException, IOException {
		long sala2Id = sala2.getId();
		sut.comprarEntradas(sala2Id, 1, (short)1);	

		assertEquals(1, getOcupadosSesion1(sala2Id)); 
		sut.comprarEntradas(sala2Id, 1, (short)3);
		assertEquals(4, getOcupadosSesion1(sala2Id));
		sut.comprarEntradas(sala2Id, 1, (short)2);
		assertEquals(6, getOcupadosSesion1(sala2Id));
		em.flush();	
		
		List<Venta> listaVenta = ventaRepository.list();
		assertEquals(3, listaVenta.size());
		assertEquals(6 ,
				listaVenta.stream()
				.mapToInt(v -> v.getCuantasEntradas())
				.sum());
		assertEquals(30.0 ,
							listaVenta.stream()
							.mapToDouble(v -> v.getPrecio())
							.sum()
				, DELTA_CANTIDAD);
	}

	
	@Test(expected=VentaException.class)
	public void testComprarEntradas_NoDisponibles() throws FileNotFoundException, IOException {
		long sala2Id = sala2.getId();
		sut.comprarEntradas(sala2Id, 1, (short)51);
	}
	
	@Test
	public void testComprarEntradas_NoDisponiblesNoAlteraDisponibles() throws FileNotFoundException, IOException {
		long sala2Id = sala2.getId();
		try {
			sut.comprarEntradas(sala2Id, 2, (short)50);
		} catch (VentaException ve) {
			em.flush();
			assertEquals(1, getOcupadosSesion2(sala2Id));
		}	
	}	
		
	
	private void inicializaBaseDeDatos() {
		sala1 = new Sala(1, 100, 0, 10, 5);
		
		sala2 = new Sala();
		sala2.setNumeroDeSala(2);
		sala2.setCapacidad(50);
		sala2.setOcupadasSesion1(0);
		sala2.setOcupadasSesion2(1);
		sala2.setOcupadasSesion3(50);

		sala3 = new Sala();
		sala3.setNumeroDeSala(3);
		sala3.setCapacidad(30);
		sala3.setOcupadasSesion1(3);
		sala3.setOcupadasSesion2(2);
		sala3.setOcupadasSesion3(30);		
		
		
		em.persist(sala1);
		em.persist(sala2);
		em.persist(sala3);
	}

	private void limpiarSalas() {
		List<Sala> listaSala = salaRepository.list();
		for (Sala sala: listaSala) {
			salaRepository.delete(sala);
		}
	}
	
	private void limpiarVentas() {
		List<Venta> listaVenta = ventaRepository.list();
		for (Venta venta: listaVenta) {
			ventaRepository.delete(venta);
		}
	}
	
	
	private int getOcupadosSesion1(long sala2Id) {
		return em.find(Sala.class, sala2Id).getOcupadasSesion1();
	}
	private int getOcupadosSesion2(long sala2Id) {
		return em.find(Sala.class, sala2Id).getOcupadasSesion2();
	}	
}
