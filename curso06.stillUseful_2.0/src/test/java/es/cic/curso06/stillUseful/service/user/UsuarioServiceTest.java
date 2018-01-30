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
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;
		
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	
	private Long IdUsuario;
	private Usuario usuario;

	private Usuario nuevoUsuario;

	@Before
	public void setUp() throws Exception {
		
		IdUsuario = testHelper.generaUsuario();
		usuario = usuarioRepository.read(IdUsuario);
	}

	@Test
	public void testCrearUsuario() {

		Usuario nuevoUsuario = usuarioService.crearUsuario("Alex", "Gonzalez Piedrahita", "20325878d",
				"Calle Principal 32", "Astillero", "Cantabria", "Cantabria", 39610, "alexgopehita@yahoo.es");

		Usuario agregadoUsuario = usuarioRepository.read(nuevoUsuario.getId());
		
		assertEquals("Alex", agregadoUsuario.getNombre());
		assertEquals("Astillero", agregadoUsuario.getCiudad());
		assertEquals(39610, agregadoUsuario.getCodigoPostal());
	}

	@Test
	public void testEditarUsuario() {

		boolean editado = usuarioService.editarUsuario(usuario.getId(), "Manuel", usuario.getApellidos(),
				usuario.getDni(), usuario.getCalle(), usuario.getCiudad(), usuario.getProvincia(),
				usuario.getComunidad(), usuario.getCodigoPostal(), usuario.getEmail());
		
		Usuario nuevoUsuario = usuarioRepository.read(usuario.getId());
		
		assertEquals(true, editado);
		
		assertEquals("Manuel", nuevoUsuario.getNombre());
	}

	@Test
	public void testBorrarUsuario() {

		List<Usuario> listaUsuario = usuarioRepository.list();
		assertEquals(1, listaUsuario.size());
		
		boolean borrado = usuarioService.borrarUsuario(usuario.getId());
		
		listaUsuario = usuarioRepository.list();
		assertNotEquals(1,listaUsuario.size());

	}

	@Test
	public void testListarUsuario() {

		
		List<Usuario> listaUsuario = usuarioRepository.list();
		assertEquals(1, listaUsuario.size());
		
		nuevoUsuario = usuarioService.crearUsuario("Alex", "Gonzalez Piedrahita", "20325878d",
				"Calle Principal 32", "Astillero", "Cantabria", "Cantabria", 39610, "alexgopehita@yahoo.es");
		
		List<Usuario> listaUsuario2 = usuarioRepository.list();
		assertEquals(2, listaUsuario2.size());
	}

}
