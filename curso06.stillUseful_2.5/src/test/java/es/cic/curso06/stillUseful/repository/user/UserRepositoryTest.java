package es.cic.curso06.stillUseful.repository.user;

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

import es.cic.curso06.stillUseful.dominio.user.User;
import es.cic.curso06.stillUseful.repository.user.UserRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaUser();
		
	}
	
	@Test
	public void testAdd() {
		User user = new User();
		user.setNick("Trinidad");
		assertNull(user.getId());

		userRepository.add(user);

		assertNotNull(user.getNick());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaUser();

		User resultado = userRepository.read(clavePrimaria);

		assertEquals("Trini", resultado.getNick());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		User user = userRepository.read(clavePrimaria);

		assertEquals("12ad123f456afd123a", user.getPassword());
	}

	@Test
	public void testList() {
		testHelper.generaUser();
		testHelper.generaUser();
		testHelper.generaUser();
		
		testHelper.generaUser();
		testHelper.generaUser();
		testHelper.generaUser();

		List<User> resultado = userRepository.list();

		assertTrue(resultado.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaUser();

		userRepository.delete(clavePrimaria);
		User user;
		try {
			user  = em.find(User.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(user);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaUser();

		User user2 = new User();
		user2.setId(clavePrimaria);
		user2.setNick("Raúl");

		User resultado = userRepository.update(user2);

		User enBBDD = em.find(User.class, clavePrimaria);
		assertEquals("Raúl", enBBDD.getNick());
		assertEquals("Raúl", resultado.getNick());
	}
	
}