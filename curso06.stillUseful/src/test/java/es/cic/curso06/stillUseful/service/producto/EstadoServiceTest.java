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
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;
import es.cic.curso06.stillUseful.repository.producto.ProductoRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class EstadoServiceTest {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	private Long IdEstado;
	private Estado estado;
	
	private Long IdProducto;
	private Producto producto;

	@Before
	public void setUp() throws Exception {
		
		IdEstado = testHelper.generaEstado();
		estado = estadoRepository.read(IdEstado);
		
		IdProducto = testHelper.generaProducto();
		producto = productoRepository.read(IdProducto);
		

	}

	@Test
	public void testCrearEstado() {
		
		Estado nuevoEstado = estadoService.crearEstado(producto, "Nuevo con caja", "Comprado pero sin llegar a usar.");

		Estado agregadoEstado = estadoRepository.read(nuevoEstado.getId());
		
		assertEquals("Nuevo con caja", agregadoEstado.getNombre());
		assertEquals("Comprado pero sin llegar a usar.", agregadoEstado.getDescripcion());
	}

	@Test
	public void testEditarEstado() {
		
		boolean editado = estadoService.editarEstado(estado.getId(), producto,
				"Nuevo con caja", estado.getDescripcion());
		
		Estado nuevoEstado = estadoRepository.read(estado.getId());
		
		assertEquals(true, editado);
		
		assertEquals("Nuevo con caja", nuevoEstado.getNombre());
	}

	@Test
	public void testBorrarEstado() {
		
		List<Estado> listaEstado = estadoRepository.list();
		assertEquals(2, listaEstado.size());
		
		boolean borrado = estadoService.borrarEstado(estado.getId());
		
		listaEstado = estadoRepository.list();
		assertEquals(1,listaEstado.size());

	}

	@Test
	public void testListarEstado() {
		
		List<Estado> listaEstado = estadoRepository.list();
		assertEquals(2, listaEstado.size());
		
		Estado nuevoEstado = estadoService.crearEstado(producto, "shooter", "fdasfdadf15646afd456as");
		
		List<Estado> listaEstado2 = estadoRepository.list();
		assertEquals(3, listaEstado2.size());
	}

}
