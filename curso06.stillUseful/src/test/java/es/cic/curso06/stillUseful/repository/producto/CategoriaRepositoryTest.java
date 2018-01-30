package es.cic.curso06.stillUseful.repository.producto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class CategoriaRepositoryTest {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaCategoria();
		
	}
	
	@Test
	public void testAdd() {
		Categoria categoria = new Categoria();
		categoria.setNombre("Calzado");
		assertNull(categoria.getId());

		categoriaRepository.add(categoria);

		assertNotNull(categoria.getNombre());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaCategoria();

		Categoria categoria = categoriaRepository.read(clavePrimaria);

		assertEquals("Calzado", categoria.getNombre());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Categoria categoria = categoriaRepository.read(clavePrimaria);

		assertEquals("Calzado", categoria.getNombre());
	}

	@Test
	public void testList() {
		testHelper.generaCategoria();
		testHelper.generaCategoria();
		testHelper.generaCategoria();
		
		testHelper.generaCategoria();
		testHelper.generaCategoria();
		testHelper.generaCategoria();

		List<Categoria> categoria = categoriaRepository.list();

		assertTrue(categoria.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaCategoria();

		categoriaRepository.delete(clavePrimaria);
		Categoria categoria;
		try {
			categoria  = em.find(Categoria.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(categoria);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaCategoria();

		Categoria categoria2 = new Categoria();
		categoria2.setId(clavePrimaria);
		categoria2.setNombre("Pantalones");

		Categoria categoria = categoriaRepository.update(categoria2);

		Categoria enBBDD = em.find(Categoria.class, clavePrimaria);
		assertEquals("Pantalones", enBBDD.getNombre());
		assertEquals("Pantalones", categoria.getNombre());
	}
	
}