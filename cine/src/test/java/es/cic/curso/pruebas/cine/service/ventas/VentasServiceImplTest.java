package es.cic.curso.pruebas.cine.service.ventas;

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
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;
import es.cic.curso.pruebas.cine.repository.sesion.SesionRepository;
import es.cic.curso.pruebas.cine.repository.venta.Venta;
import es.cic.curso.pruebas.cine.repository.venta.VentaRepository;
import es.cic.curso.pruebas.cine.service.sala.SalaService;
import es.cic.curso.pruebas.cine.service.sesion.SesionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/pruebas/cine/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class VentasServiceImplTest {
	
	@Autowired
	VentasServiceImpl sut;
	
	@Autowired
	SesionService sesionService;
	
	@Autowired
	SalaService salaService;
	
	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	SesionRepository sesionRepository;
	
	@Autowired
	VentaRepository ventaRepository;
	
	private Sala sala;
	private Sesion sesion;

	@Before
	public void setUp() throws Exception {
		
		limpiarSesiones();
		limpiarSalas();
		limpiarVentas();
		
		sala = new Sala(50);
		salaRepository.add(sala);
		
		sesion = new Sesion(0, false, sala);
		sesionRepository.add(sesion);
		
	}

	@Test
	public void testAniadirVenta() {
		Long idVenta = sut.aniadirVenta(sala.getId(), sesion.getId(), 2, 10.0);
		
		assertNotNull(idVenta);
	}

	@Test
	public void testObtenerVenta() {
		Long idVenta = sut.aniadirVenta(sala.getId(), sesion.getId(), 2, 10.0);
		Venta venta = sut.obtenerVenta(idVenta);
		
		assertNotNull(venta.getId());
		assertTrue(venta.getNumEntradas() == 2);
		
	}
	@Test
	public void testObtenerVentas() {
		List<Venta>ventas = sut.obtenerVentas();
		for(Venta venta : ventas){
			assertNotNull(venta.getId());
		}
	}
	
	@Test
	public void testActualizarVenta() {
		Long idVenta = sut.aniadirVenta(sala.getId(), sesion.getId(), 2, 10.0);
		Venta venta = sut.obtenerVenta(idVenta);
		venta.setNumEntradas(4);
		
		Venta ventaMod = sut.obtenerVenta(idVenta);
		
		assertTrue(ventaMod.getNumEntradas() == 4);
		
	}
	
	@Test
	public void testBorrarNota() {
		Long idVenta = sut.aniadirVenta(sala.getId(), sesion.getId(), 2, 10.0);
		
		sut.borrarVenta(idVenta);
		
		List<Venta>ventas = sut.obtenerVentas();
		
		assertTrue(ventas.size() == 0);
	}
	
	private void limpiarSesiones(){
		List<Sesion>sesiones = sesionService.obtenerSesiones();
		for(Sesion sesion : sesiones){
			sesionRepository.delete(sesion);
		}
	}
	
	private void limpiarSalas(){
		List<Sala>salas = salaService.obtenerSalas();
		for(Sala sala : salas){
			salaRepository.delete(sala);
		}
	}
	
	private void limpiarVentas(){
		List<Venta>ventas = sut.obtenerVentas();
		for(Venta venta : ventas){
			ventaRepository.delete(venta);
		}
	}

}
