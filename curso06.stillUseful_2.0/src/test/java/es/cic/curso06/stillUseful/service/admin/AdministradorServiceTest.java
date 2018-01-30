package es.cic.curso06.stillUseful.service.admin;

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

import es.cic.curso06.stillUseful.dominio.admin.Admin;
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
public class AdministradorServiceTest {
	
	
	@Autowired
	private AdministradorService administradorService;
		
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	
	private Long IdAdminitrador;
	private Administrador administrador;

	private Administrador nuevoAdministrador;

	@Before
	public void setUp() throws Exception {
		
		IdAdminitrador = testHelper.generaAdministrador();
		administrador = administradorRepository.read(IdAdminitrador);
	}

	@Test
	public void testCrearAdministrador() {

		Administrador nuevoAdministrador = administradorService.crearAdministrador("Alex", "Gonzalez Piedrahita", "20325878d",
				"Calle Principal 32", "Astillero", "Cantabria", "Cantabria", 39610, "alexgopehita@yahoo.es");

		Administrador agregadoAdministrador = administradorRepository.read(nuevoAdministrador.getId());
		
		assertEquals("Alex", agregadoAdministrador.getNombre());
		assertEquals("Astillero", agregadoAdministrador.getCiudad());
		assertEquals(39610, agregadoAdministrador.getCodigoPostal());
	}

	@Test
	public void testEditarAdministrador() {

		boolean editado = administradorService.editarAdministrador(administrador.getId(), "Manuel", administrador.getApellidos(),
				administrador.getDni(), administrador.getCalle(), administrador.getCiudad(), administrador.getProvincia(),
				administrador.getComunidad(), administrador.getCodigoPostal(), administrador.getEmail());
		
		Administrador nuevoAdministardor = administradorRepository.read(administrador.getId());
		
		assertEquals(true, editado);
		
		assertEquals("Manuel", nuevoAdministardor.getNombre());
	}

	@Test
	public void testBorrarAdministrador() {

		List<Administrador> listaAdministrador = administradorRepository.list();
		assertEquals(1, listaAdministrador.size());
		
		boolean borrado = administradorService.borrarAdministrador(administrador.getId());
		
		listaAdministrador = administradorRepository.list();
		assertNotEquals(1,listaAdministrador.size());

	}

	@Test
	public void testListarAdministrador() {

		
		List<Administrador> listaAdministrador = administradorRepository.list();
		assertEquals(1, listaAdministrador.size());
		
		nuevoAdministrador = administradorService.crearAdministrador("Alex", "Gonzalez Piedrahita", "20325878d",
				"Calle Principal 32", "Astillero", "Cantabria", "Cantabria", 39610, "alexgopehita@yahoo.es");
		
		List<Administrador> listaAdministrador2 = administradorRepository.list();
		assertEquals(2, listaAdministrador2.size());
	}

}
