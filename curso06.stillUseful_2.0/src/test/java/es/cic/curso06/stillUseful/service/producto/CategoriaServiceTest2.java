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
public class CategoriaServiceTest2 {
	
	@Autowired
	CategoriaService sut;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Before
	public void setUp() throws Exception {
		limpiarCategoriaes();
	}

	private void limpiarCategoriaes() {
		List<Categoria> categoriaes = sut.obtenerCategoriaes2();
		for(Categoria categoria : categoriaes){
			categoriaRepository.delete(categoria);
		}
		
	}
	@Test
	public void testAniadirCategoria2() {
		
		Long idCategoria = sut.aniadirCategoria2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idCategoria);
	}

	@Test
	public void testObtenerCategoriaes2() {
		
		List<Categoria>categoriaes = sut.obtenerCategoriaes2();
		for(Categoria categoria : categoriaes){
			assertNotNull(categoria.getId());
		}
	}

	@Test
	public void testObtenerCategoria2() {

		Long idCategoria = sut.aniadirCategoria2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idCategoria);
		
		Categoria categoria = sut.obtenerCategoria2(idCategoria);
		
		assertNotNull(categoria.getId());
		assertTrue(categoria.getCodigoPostal() == 39012);
	}

	@Test
	public void testActualizarCategoria2() {
		
		Long idCategoria = sut.aniadirCategoria2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idCategoria);
		
		Categoria categoria = sut.obtenerCategoria2(idCategoria);
		categoria.setCodigoPostal(39011);
		Categoria categoriaActualizada = sut.obtenerCategoria2(idCategoria);
		
		assertTrue(categoriaActualizada.getCodigoPostal() == 39011);
		assertNotNull(categoriaActualizada);
	}
	

	@Test
	public void testBorrarCategoria2() {

		Long idCategoria = sut.aniadirCategoria2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idCategoria);
		
		sut.borrarCategoria2(idCategoria);
		
		List<Categoria>categoriaes = sut.obtenerCategoriaes2();;
		
		assertTrue(categoriaes.size() == 0);
		
	}

}
