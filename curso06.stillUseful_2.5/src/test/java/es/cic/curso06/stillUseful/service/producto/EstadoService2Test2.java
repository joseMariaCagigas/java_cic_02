package es.cic.curso06.stillUseful.service.producto;
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

import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class EstadoService2Test2 {
	
	@Autowired
	EstadoService2 estadoService;
	
	@Autowired
	EstadoRepository estadoRepository;

	@Before
	public void setUp() throws Exception {
		limpiarEstados();
	}

	private void limpiarEstados() {
		List<Estado> estados = estadoService.obtenerEstados();
		for(Estado estado : estados){
			estadoRepository.delete(estado);
		}
		
	}

	@Test
	public void testAniadirEstado() {
		
		Long idEstado = estadoService.aniadirEstado2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idEstado);
	}

	@Test
	public void testObtenerEstados() {
		List<Estado>estados = estadoService.obtenerEstados();
		for(Estado estado : estados){
			assertNotNull(estado.getId());
		}
	}

	@Test
	public void testObtenerEstado() {
		Long idEstado = estadoService.aniadirEstado2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idEstado);
		
		Estado estado = estadoService.obtenerEstado(idEstado);
		
		assertNotNull(estado.getId());
		assertTrue(estado.getNombre() == "Zapatos Tacón");
	}

	@Test
	public void testActualizarEstado() {
		Long idEstado = estadoService.aniadirEstado2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idEstado);
		
		Estado estado = estadoService.obtenerEstado(idEstado);
		estado.setDescripcion("Azúl 2016");
		Estado estadoActualizada = estadoService.obtenerEstado(idEstado);
		
		assertTrue(estadoActualizada.getDescripcion() == "Azúl 2016");
		assertNotNull(estadoActualizada);
	}

	@Test
	public void testBorrarEstado() {
		Long idEstado = estadoService.aniadirEstado2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idEstado);
		
		estadoService.borrarestadoRepository(idEstado);
		
		List<Estado>estados = estadoService.obtenerEstados();
		
		assertTrue(estados.size() == 0);
		
	}
	
}