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

import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;
import es.cic.curso06.stillUseful.testHelper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		
		testHelper.generaUsuario();
		
	}
	
	@Test
	public void testAdd() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Manuel Trinidad");
		assertNull(usuario.getId());

		usuarioRepository.add(usuario);

		assertNotNull(usuario.getNombre());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = testHelper.generaUsuario();

		Usuario resultado = usuarioRepository.read(clavePrimaria);

		assertEquals("Manuel Trinidad", resultado.getNombre());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Usuario usuario = usuarioRepository.read(clavePrimaria);

		assertEquals("allendelasguas@gmail.com", usuario.getEmail());
	}

	@Test
	public void testList() {
		testHelper.generaUsuario();
		testHelper.generaUsuario();
		testHelper.generaUsuario();
		
		testHelper.generaUsuario();
		testHelper.generaUsuario();
		testHelper.generaUsuario();

		List<Usuario> resultado = usuarioRepository.list();

		assertTrue(resultado.size()>= 6);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = testHelper.generaUsuario();

		usuarioRepository.delete(clavePrimaria);
		Usuario usuario;
		try {
			usuario  = em.find(Usuario.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(usuario);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = testHelper.generaUsuario();

		Usuario Admin2 = new Usuario();
		Admin2.setId(clavePrimaria);
		Admin2.setNombre("Raúl");

		Usuario resultado = usuarioRepository.update(Admin2);

		Usuario enBBDD = em.find(Usuario.class, clavePrimaria);
		assertEquals("Raúl", enBBDD.getNombre());
		assertEquals("Raúl", resultado.getNombre());
	}
	
}