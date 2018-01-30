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

import es.cic.curso06.stillUseful.dominio.producto.Producto;
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
public class ProductoRepositoryTest {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaProducto();
		
	}
	
	@Test
	public void testAdd() {
		Producto producto = new Producto();
		producto.setNombre("Nike Jordan V 2001");
		assertNull(producto.getId());

		productoRepository.add(producto);

		assertNotNull(producto.getNombre());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaProducto();

		Producto producto = productoRepository.read(clavePrimaria);

		assertEquals("Nike Jordan V 2001", producto.getNombre());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Producto producto = productoRepository.read(clavePrimaria);

		assertEquals("Nike Jordan V 2001", producto.getNombre());
	}

	@Test
	public void testList() {
		testHelper.generaProducto();
		testHelper.generaProducto();
		testHelper.generaProducto();
		
		testHelper.generaProducto();
		testHelper.generaProducto();
		testHelper.generaProducto();

		List<Producto> producto = productoRepository.list();

		assertTrue(producto.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaProducto();

		productoRepository.delete(clavePrimaria);
		Producto producto;
		try {
			producto  = em.find(Producto.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(producto);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaProducto();

		Producto producto2 = new Producto();
		producto2.setId(clavePrimaria);
		producto2.setNombre("Camisa Zara 2016");

		Producto producto = productoRepository.update(producto2);

		Producto enBBDD = em.find(Producto.class, clavePrimaria);
		assertEquals("Camisa Zara 2016", enBBDD.getNombre());
		assertEquals("Camisa Zara 2016", producto.getNombre());
	}
	
}