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
import es.cic.curso06.stillUseful.service.user.UsuarioService2;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class ProductoService2Test {

	@Autowired
	ProductoService2 productoService;
	
	@Autowired
	CategoriaService2 categoriaService;
	
	@Autowired
	EstadoService2 estadoService;
	
	@Autowired
	UsuarioService2 usuarioService;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	private Categoria categoria;
	private Estado estado;
	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		
		limpiarUsuarios();
		limpiarCategorias();
		limpiarEstados();
		limpiarProductos();
		
		categoria = new Categoria("Pantalón Jean", "Pantalones Vaqueros");
		categoriaRepository.add(categoria);
		
		estado = new Estado("Pantalón Jean", "Pantalones Vaqueros");
		estadoRepository.add(estado);
		
		usuario = new Usuario("Manuel Trinidad", "Hacha Ventilador", "20356358F", "Villa Porrosa s/n", "Santander", 
				"Cantabria", "Cantabria" ,39012, "allendelasguas@gmail.com");
		usuarioRepository.add(usuario);

	}
	
	private void limpiarProductos() {

		List<Producto>productos = productoService.obtenerProductos();
		for(Producto producto : productos){
			productoRepository.delete(producto);
		}
	}

	private void limpiarEstados() {

		List<Estado>estados = estadoService.obtenerEstados();
		for(Estado estado : estados){
			estadoRepository.delete(estado);
		}
	}

	private void limpiarCategorias() {

		List<Categoria>categorias = categoriaService.obtenerCategorias();
		for(Categoria categoria : categorias){
			categoriaRepository.delete(categoria);
		}
	}

	private void limpiarUsuarios() {
		
		List<Usuario>usuarios = usuarioService.obtenerUsuarios();
		for(Usuario usuario : usuarios){
			usuarioRepository.delete(usuario);
		}
		
	}

	@Test
	public void testAniadirProducto() {

		Long idProducto = productoService.aniadirProducto("Levi's 501", 50, 1, false, false, categoria.getId(),
				estado.getId(), usuario.getId());
		
		assertNotNull(idProducto);
	}

	@Test
	public void testObtenerProducto() {
		
		Long idProducto = productoService.aniadirProducto("Levi's 501", 50, 1, false, false, categoria.getId(),
				estado.getId(), usuario.getId());
		
		Producto producto = productoService.obtenerProducto(idProducto);
		
		assertNotNull(producto.getId());
		assertTrue(producto.getPrecioInicial() == 50);
	}

	@Test
	public void testObtenerProductos() {
		
		List<Producto> productos = productoService.obtenerProductos();
		for(Producto producto : productos){
			assertNotNull(producto.getId());
		}
	}

	@Test
	public void testActualizarProducto() {
		
		Long idProducto = productoService.aniadirProducto("Levi's 501", 50, 1, false, false, categoria.getId(),
				estado.getId(), usuario.getId());
		
		Producto producto = productoService.obtenerProducto(idProducto);
		
		producto.setCantidad(2);
		
		Producto productoMod = productoService.obtenerProducto(idProducto);
		
		assertTrue(productoMod.getCantidad() == 2);
	}

	@Test
	public void testBorrarVenta() {
		
		Long idProducto = productoService.aniadirProducto("Levi's 501", 50, 1, false, false, categoria.getId(),
				estado.getId(), usuario.getId());
		
		productoService.borrarProducto(idProducto);
		
		List<Producto> productos = productoService.obtenerProductos();
		assertTrue(productos.size() == 0);
	}
	
	

}
