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

import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;
import es.cic.curso06.stillUseful.service.user.UsuarioService2;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class UsuarioService2Test {
	
	@Autowired
	UsuarioService2 usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Before
	public void setUp() throws Exception {
		limpiarUsuarios();
	}

	private void limpiarUsuarios() {
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		for(Usuario usuario : usuarios){
			usuarioRepository.delete(usuario);
		}
		
	}

	@Test
	public void testAniadirUsuario() {
		
		Long idUsuario = usuarioService.aniadirUsuario("Manuel", "Zapato Veloz", "32659874f", "Calle Armonía 43", "Santander",
				"Cantabria", "Cantabria", 39012, "allialfondo@gmail.com");
		assertNotNull(idUsuario);
	}

	@Test
	public void testObtenerUsuarios() {
		List<Usuario>usuarios = usuarioService.obtenerUsuarios();
		for(Usuario usuario : usuarios){
			assertNotNull(usuario.getId());
		}
	}

	@Test
	public void testObtenerUsuario() {
		Long idUsuario = usuarioService.aniadirUsuario("Manuel", "Zapato Veloz", "32659874f", "Calle Armonía 43", "Santander",
				"Cantabria", "Cantabria", 39012, "allialfondo@gmail.com");
		assertNotNull(idUsuario);
		
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		
		assertNotNull(usuario.getId());
		assertTrue(usuario.getNombre() == "Manuel");
	}

	@Test
	public void testActualizarUsuario() {
		Long idUsuario = usuarioService.aniadirUsuario("Manuel", "Zapato Veloz", "32659874f", "Calle Armonía 43", "Santander",
				"Cantabria", "Cantabria", 39012, "allialfondo@gmail.com");
		assertNotNull(idUsuario);
		
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		usuario.setNombre("Pepe");
		Usuario usuarioActualizada = usuarioService.obtenerUsuario(idUsuario);
		
		assertTrue(usuarioActualizada.getNombre() == "Pepe");
		assertNotNull(usuarioActualizada);
	}

	@Test
	public void testBorrarUsuario() {
		Long idUsuario = usuarioService.aniadirUsuario("Manuel", "Zapato Veloz", "32659874f", "Calle Armonía 43", "Santander",
				"Cantabria", "Cantabria", 39012, "allialfondo@gmail.com");
		assertNotNull(idUsuario);
		
		usuarioService.borrarUsuario(idUsuario);
		
		List<Usuario>usuarios = usuarioService.obtenerUsuarios();
		
		assertTrue(usuarios.size() == 0);
		
	}
}
