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

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;
import es.cic.curso06.stillUseful.repository.producto.ProductoRepository;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class ProductoServiceTest {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	private Long IdCategoria;
	private Categoria categoria;
	
	private Long IdProducto;
	private Producto producto;
	
	private Long IdEstado;
	private Estado estado;
	
	private Long IdUsuario;
	private Usuario usuario;

	private boolean borrado;
	
	@Before
	public void setUp() throws Exception {
		
		IdCategoria = testHelper.generaCategoria();
		categoria = categoriaRepository.read(IdCategoria);
		
		IdProducto = testHelper.generaProducto();
		producto = productoRepository.read(IdProducto);
		
		IdEstado = testHelper.generaEstado();
		estado = estadoRepository.read(IdEstado);
		
		IdUsuario = testHelper.generaUsuario();
		usuario = usuarioRepository.read(IdUsuario);
	}

	@Test
	public void testCrearProducto() {
		
		Producto nuevoProducto = productoService.crearProducto("Cazadora Levis 2008", 30.00, 1, false, false, categoria, estado, usuario);

		Producto agregadoProducto = productoRepository.read(nuevoProducto.getId());
		
		assertEquals("Cazadora Levis 2008", agregadoProducto.getNombre());
		assertTrue(30.00 == agregadoProducto.getPrecioInicial());
		assertTrue(1 == agregadoProducto.getCantidad());

	}

	@Test
	public void testEditarProducto() {
		
		boolean editado = productoService.editarProducto(
				producto.getId(),producto.getNombre(), producto.getPrecioInicial(), 2, true, true,
				producto.getCategoriaId(), producto.getEstadoId(), producto.getUsuarioId());
		
		Producto productoEditado = productoRepository.read(producto.getId());
		
		assertEquals(true, editado);
		
		assertEquals(2, productoEditado.getCantidad());
		assertTrue(true);
	}

	@Test
	public void testBorrarProducto() {
		
		List<Producto> listaProductos = productoRepository.list();
		assertEquals(1, listaProductos.size());
		
		boolean borrado = productoService.borrarProducto(producto.getId());
		
		listaProductos = productoRepository.list();
		
		assertEquals(true, borrado);
		assertEquals(0, listaProductos.size());
	}

	@Test
	public void testListarProducto() {

		List<Producto> listaProductos = productoService.listarProducto();
		
		assertEquals(1, listaProductos.size());
		
		borrado = productoService.borrarProducto(producto.getId());
		
		listaProductos = productoRepository.list();
		
		assertEquals(0, listaProductos.size());
	}

}
