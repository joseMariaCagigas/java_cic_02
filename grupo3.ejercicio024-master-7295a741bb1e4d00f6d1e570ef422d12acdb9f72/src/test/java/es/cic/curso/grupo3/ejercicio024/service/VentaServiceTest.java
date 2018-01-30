package es.cic.curso.grupo3.ejercicio024.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

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

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;
import es.cic.curso.grupo3.ejercicio024.helper.TestHelper;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;
import es.cic.curso.grupo3.ejercicio024.repository.VentaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo3/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class VentaServiceTest {
	
	private static final double DELTA = 0.001;
	@Autowired
	private VentaService ventaService;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private TestHelper tHelper;
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private SalaService salaService;

	Long claveVenta;
	Long claveSala;
	Sala sala;
	Venta venta;
	
	@Before
	public void setUp() throws Exception {
		claveSala = tHelper.generaSalaUnoSesionUno();
		sala = salaRepository.read(claveSala);
		claveVenta = tHelper.generaVentaSesionUno(sala);
		venta = ventaRepository.read(claveVenta);
	}
	
	@Test
	public void testObtenerRecaudacionSala(){
		claveSala = tHelper.generaSalaDosSesionDos();
		sala = salaRepository.read(claveSala);
		claveVenta = tHelper.generaVentaSesionDos(sala);
		
		tHelper.generaVentaSesionUno(sala);
		tHelper.generaVentaSesionUno(sala);
		
		double recaudacion;
		
		recaudacion = ventaService.obtenerRecaudacionSala(1);
		assertEquals(50.0, recaudacion, 0.1);
	}	
	
	@Test
	public void testObtenerRecaudacionCine(){
		claveSala = tHelper.generaSalaDosSesionDos();
		sala = salaRepository.read(claveSala);
		claveVenta = tHelper.generaVentaSesionDos(sala);
		
		tHelper.generaVentaSesionUno(sala);
		tHelper.generaVentaSesionUno(sala);
		
		double recaudacion;
		
		recaudacion = ventaService.obtenerRecaudacionCine();
		assertEquals(200.0, recaudacion, 0.1);
	}	
	
	@Test
	public void testVenderEntrada_ConDescuento(){
		ventaService.venderEntrada(sala, 5);
		
		int asientosOcupados = sala.getAsientosLibres();
		assertEquals(95, asientosOcupados);
	}
	
	@Test
	public void testVenderEntrada_SinDescuento_SesionAbierta(){
		ventaService.venderEntrada(sala, 4);
		
		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(96, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_SesionCerrada(){
		salaService.cerrarSesion(1, 1);
		
		ventaService.venderEntrada(sala, 4);

		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(100, asientosOcupados);
		assertEquals(true, resultado);
	}
	
	@Test
	public void testVenderEntrada_SesionCerrada_SesionAbierta(){
		salaService.cerrarSesion(1, 1);

		ventaService.venderEntrada(sala, 4);

		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(100, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirSesion(1, 1);
		
		ventaService.venderEntrada(sala, 4);

		asientosOcupados = sala.getAsientosLibres();
		resultado = salaService.esCerradoSesion(sala);

		assertEquals(96, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_CineCerrado(){
		salaService.cerrarCine();

		ventaService.venderEntrada(sala, 4);

		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(100, asientosOcupados);
		assertEquals(true, resultado);
	}
	
	@Test
	public void testVenderEntrada_CineCerrado_CineAbierto(){
		salaService.cerrarCine();

		ventaService.venderEntrada(sala, 4);

		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(100, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirCine();
		
		ventaService.venderEntrada(sala, 4);

		asientosOcupados = sala.getAsientosLibres();
		resultado = salaService.esCerradoSesion(sala);

		assertEquals(96, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_SesionCerrada_CineAbierto(){
		salaService.cerrarSesion(1, 1);

		ventaService.venderEntrada(sala, 4);

		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(100, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirCine();
		
		ventaService.venderEntrada(sala, 4);

		asientosOcupados = sala.getAsientosLibres();
		resultado = salaService.esCerradoSesion(sala);

		assertEquals(96, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_CineCerrado_SesionAbierta(){
		salaService.cerrarCine();

		ventaService.venderEntrada(sala, 4);

		int asientosOcupados = sala.getAsientosLibres();
		boolean resultado = salaService.esCerradoSesion(sala);

		assertEquals(100, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirSesion(1, 1);
		
		ventaService.venderEntrada(sala, 4);

		asientosOcupados = sala.getAsientosLibres();
		resultado = salaService.esCerradoSesion(sala);

		assertEquals(96, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testBorrarVenta(){
		Collection<Venta> listaVentas = ventaService.getVentas();
		
		ventaService.borrarVenta(venta);
		
		listaVentas = ventaService.getVentas();
		
		assertEquals(0, listaVentas.size());
		assertEquals(110, sala.getAsientosLibres());
	}
	
	@Test
	public void testBorrarVenta_Dos(){
		Collection<Venta> listaVentas = ventaService.getVentas();
		
		ventaService.borrarVenta(venta.getId());
		
		listaVentas = ventaService.getVentas();
		
		assertEquals(0, listaVentas.size());
		assertEquals(110, sala.getAsientosLibres());
	}
	
	@Test
	public void testVaciarVentas(){
		tHelper.generaVentaSesionDos(sala);
		tHelper.generaVentaSesionDos(sala);
		tHelper.generaVentaSesionDos(sala);

		Collection<Venta> listaVentas = ventaService.getVentas(); 
		
		assertEquals(4, listaVentas.size());
		
		ventaService.vaciarVentas();
		
		listaVentas = ventaService.getVentas(); 
		assertEquals(0, listaVentas.size());
	}
	
	@Test
	public void testGetPrecio(){
		double precio = venta.getPrecio();
		
		assertEquals(5.0, precio, DELTA);
	}
}