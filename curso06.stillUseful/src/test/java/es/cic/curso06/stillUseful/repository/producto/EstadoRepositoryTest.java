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

import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class EstadoRepositoryTest {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaEstado();
		
	}
	
	@Test
	public void testAdd() {
		Estado estado = new Estado();
		estado.setNombre("Usado");
		assertNull(estado.getId());

		estadoRepository.add(estado);

		assertNotNull(estado.getNombre());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaEstado();

		Estado estado = estadoRepository.read(clavePrimaria);

		assertEquals("Usado", estado.getNombre());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Estado estado = estadoRepository.read(clavePrimaria);

		assertEquals("Usado", estado.getNombre());
	}

	@Test
	public void testList() {
		testHelper.generaEstado();
		testHelper.generaEstado();
		testHelper.generaEstado();
		
		testHelper.generaEstado();
		testHelper.generaEstado();
		testHelper.generaEstado();

		List<Estado> estado = estadoRepository.list();

		assertTrue(estado.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaEstado();

		estadoRepository.delete(clavePrimaria);
		Estado estado;
		try {
			estado  = em.find(Estado.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(estado);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaEstado();

		Estado estado2 = new Estado();
		estado2.setId(clavePrimaria);
		estado2.setNombre("Nuevo");

		Estado estado = estadoRepository.update(estado2);

		Estado enBBDD = em.find(Estado.class, clavePrimaria);
		assertEquals("Nuevo", enBBDD.getNombre());
		assertEquals("Nuevo", estado.getNombre());
	}
	
}