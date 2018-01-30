package es.cic.curso06.stillUseful.service.user;

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

import es.cic.curso06.stillUseful.dominio.user.User;
import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.user.UserRepository;
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
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	private Long IdUser;
	private User user;
	
	private Long IdUsuario;
	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		
		IdUser = testHelper.generaUser();
		user = userRepository.read(IdUser);
		
		IdUsuario = testHelper.generaUsuario();
		usuario = usuarioRepository.read(IdUsuario);
		

	}

	@Test
	public void testCrearUser() {
		
		User nuevoUser = userService.crearUser(usuario, "Duro de pelar", "204gh659gh569hy");

		User agregadoUser = userRepository.read(nuevoUser.getId());
		
		assertEquals("Duro de pelar", agregadoUser.getNick());
		assertEquals("204gh659gh569hy", agregadoUser.getPassword());
	}

	@Test
	public void testEditarUser() {
		
		boolean editado = userService.editarUser(user.getId(), user.getUsuario(), "Veneno",user.getPassword());
		
		User nuevoUser = userRepository.read(user.getId());
		
		assertEquals(true, editado);
		
		assertEquals("Veneno", nuevoUser.getNick());
	}

	@Test
	public void testBorrarUser() {
		
		List<User> listaUser = userRepository.list();
		assertEquals(1, listaUser.size());
		
		boolean borrado = userService.borrarUser(user.getId());
		
		listaUser = userRepository.list();
		assertNotEquals(1,listaUser.size());

	}

	@Test
	public void testListarUser() {
		
		List<User> listaUser = userRepository.list();
		assertEquals(1, listaUser.size());
		
		User nuevoUser = userService.crearUser(usuario, "shooter", "fdasfdadf15646afd456as");
		
		List<User> listaUser2 = userRepository.list();
		assertEquals(2, listaUser2.size());
	}

}
