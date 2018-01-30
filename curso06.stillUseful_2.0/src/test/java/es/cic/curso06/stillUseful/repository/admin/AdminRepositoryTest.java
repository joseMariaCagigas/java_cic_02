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

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.repository.admin.AdminRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class AdminRepositoryTest {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaAdmin();
		
	}
	
	@Test
	public void testAdd() {
		Admin admin = new Admin();
		admin.setNick("Trinidad");
		assertNull(admin.getId());

		adminRepository.add(admin);

		assertNotNull(admin.getNick());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaAdmin();

		Admin resultado = adminRepository.read(clavePrimaria);

		assertEquals("Trini", resultado.getNick());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Admin admin = adminRepository.read(clavePrimaria);

		assertEquals("12ad123f456afd123a", admin.getPassword());
	}

	@Test
	public void testList() {
		testHelper.generaAdmin();
		testHelper.generaAdmin();
		testHelper.generaAdmin();
		
		testHelper.generaAdmin();
		testHelper.generaAdmin();
		testHelper.generaAdmin();

		List<Admin> resultado = adminRepository.list();

		assertTrue(resultado.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaAdmin();

		adminRepository.delete(clavePrimaria);
		Admin admin;
		try {
			admin  = em.find(Admin.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(admin);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaAdmin();

		Admin Admin2 = new Admin();
		Admin2.setId(clavePrimaria);
		Admin2.setNick("Raúl");

		Admin resultado = adminRepository.update(Admin2);

		Admin enBBDD = em.find(Admin.class, clavePrimaria);
		assertEquals("Raúl", enBBDD.getNick());
		assertEquals("Raúl", resultado.getNick());
	}
	
}