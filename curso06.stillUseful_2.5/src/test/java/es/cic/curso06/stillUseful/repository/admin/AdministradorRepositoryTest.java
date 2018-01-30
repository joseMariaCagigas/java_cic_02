package es.cic.curso06.stillUseful.repository.admin;

import static org.junit.Assert.*;

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

import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.repository.admin.AdministradorRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class AdministradorRepositoryTest {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaAdministrador();
		
	}
	
	@Test
	public void testAdd() {
		Administrador administrador = new Administrador();
		administrador.setNombre("Manuel Trinidad");
		assertNull(administrador.getId());

		administradorRepository.add(administrador);

		assertNotNull(administrador.getNombre());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaAdministrador();

		Administrador resultado = administradorRepository.read(clavePrimaria);

		assertEquals("Manuel Trinidad", resultado.getNombre());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Administrador administrador = administradorRepository.read(clavePrimaria);

		assertEquals("allendelasguas@gmail.com", administrador.getEmail());
	}

	@Test
	public void testList() {
		testHelper.generaAdministrador();
		testHelper.generaAdministrador();
		testHelper.generaAdministrador();
		
		testHelper.generaAdministrador();
		testHelper.generaAdministrador();
		testHelper.generaAdministrador();

		List<Administrador> resultado = administradorRepository.list();

		assertTrue(resultado.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaAdministrador();

		administradorRepository.delete(clavePrimaria);
		Administrador administrador;
		try {
			administrador  = em.find(Administrador.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(administrador);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaAdministrador();

		Administrador Admin2 = new Administrador();
		Admin2.setId(clavePrimaria);
		Admin2.setNombre("Raúl");

		Administrador resultado = administradorRepository.update(Admin2);

		Administrador enBBDD = em.find(Administrador.class, clavePrimaria);
		assertEquals("Raúl", enBBDD.getNombre());
		assertEquals("Raúl", resultado.getNombre());
	}
	
}