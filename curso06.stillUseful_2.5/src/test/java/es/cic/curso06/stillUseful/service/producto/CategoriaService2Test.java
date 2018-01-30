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
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class CategoriaService2Test {
	
	@Autowired
	CategoriaService2 categoriaService;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Before
	public void setUp() throws Exception {
		limpiarCategorias();
	}

	private void limpiarCategorias() {
		List<Categoria> categorias = categoriaService.obtenerCategorias();
		for(Categoria categoria : categorias){
			categoriaRepository.delete(categoria);
		}
		
	}

	@Test
	public void testAniadirCategoria() {
		
		Long idCategoria = categoriaService.aniadirCategoria2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idCategoria);
	}

	@Test
	public void testObtenerCategorias() {
		List<Categoria>categorias = categoriaService.obtenerCategorias();
		for(Categoria categoria : categorias){
			assertNotNull(categoria.getId());
		}
	}

	@Test
	public void testObtenerCategoria() {
		Long idCategoria = categoriaService.aniadirCategoria2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idCategoria);
		
		Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
		
		assertNotNull(categoria.getId());
		assertTrue(categoria.getNombre() == "Zapatos Tacón");
	}

	@Test
	public void testActualizarCategoria() {
		Long idCategoria = categoriaService.aniadirCategoria2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idCategoria);
		
		Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
		categoria.setDescripcion("Azúl 2016");
		Categoria categoriaActualizada = categoriaService.obtenerCategoria(idCategoria);
		
		assertTrue(categoriaActualizada.getDescripcion() == "Azúl 2016");
		assertNotNull(categoriaActualizada);
	}

	@Test
	public void testBorrarCategoria() {
		Long idCategoria = categoriaService.aniadirCategoria2("Zapatos Tacón", "Azyl 2016");
		assertNotNull(idCategoria);
		
		categoriaService.borrarcategoriaRepository(idCategoria);
		
		List<Categoria>categorias = categoriaService.obtenerCategorias();
		
		assertTrue(categorias.size() == 0);
		
	}
	
}