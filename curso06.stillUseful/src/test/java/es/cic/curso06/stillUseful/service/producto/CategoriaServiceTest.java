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
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;
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
public class CategoriaServiceTest {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	private Long IdCategoria;
	private Categoria categoria;
	
	private Long IdProducto;
	private Producto producto;

	private boolean borrado;

	private Categoria nuevoCategoria;

	public Categoria getNuevoCategoria() {
		return nuevoCategoria;
	}

	public void setNuevoCategoria(Categoria nuevoCategoria) {
		this.nuevoCategoria = nuevoCategoria;
	}

	@Before
	public void setUp() throws Exception {
		
		IdCategoria = testHelper.generaCategoria();
		categoria = categoriaRepository.read(IdCategoria);
		
		IdProducto = testHelper.generaProducto();
		producto = productoRepository.read(IdProducto);
		

	}

	@Test
	public void testCrearCategoria() {
		
		Categoria nuevoCategoria = categoriaService.crearCategoria(producto, "Pantalones - Jeans", 
				"Vaqueros.");

		Categoria agregadoCategoria = categoriaRepository.read(nuevoCategoria.getId());
		
		assertEquals("Pantalones - Jeans", agregadoCategoria.getNombre());
		assertEquals("Vaqueros.", agregadoCategoria.getDescripcion());
	}

	@Test
	public void testEditarCategoria() {
		
		boolean editado = categoriaService.editarCategoria(categoria.getId(), producto,
				"Chaquetas", categoria.getDescripcion());
		
		Categoria nuevoCategoria = categoriaRepository.read(categoria.getId());
		
		assertEquals(true, editado);
		
		assertEquals("Chaquetas", nuevoCategoria.getNombre());
	}

	@Test
	public void testBorrarCategoria() {
		
		List<Categoria> listaCategoria = categoriaRepository.list();
		assertEquals(2, listaCategoria.size());
		
		setBorrado(categoriaService.borrarCategoria(categoria.getId()));
		
		listaCategoria = categoriaRepository.list();
		assertEquals(1,listaCategoria.size());

	}

	@Test
	public void testListarCategoria() {
		
		List<Categoria> listaCategoria = categoriaRepository.list();
		assertEquals(2, listaCategoria.size());
		
		setNuevoCategoria(categoriaService.crearCategoria(producto, "shooter", "fdasfdadf15646afd456as"));
		
		List<Categoria> listaCategoria2 = categoriaRepository.list();
		assertEquals(3, listaCategoria2.size());
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

}
