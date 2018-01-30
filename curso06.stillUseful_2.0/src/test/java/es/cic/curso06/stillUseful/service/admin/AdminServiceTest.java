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
import es.cic.curso06.stillUseful.repository.admin.AdminRepository;
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
public class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private TestHelper testHelper;
	
	private Long IdAdmin;
	private Admin admin;
	
	private Long IdAdminitrador;
	private Administrador administrador;

	@Before
	public void setUp() throws Exception {
		
		IdAdmin = testHelper.generaAdmin();
		admin = adminRepository.read(IdAdmin);
		
		IdAdminitrador = testHelper.generaAdministrador();
		administrador = administradorRepository.read(IdAdminitrador);
		

	}

	@Test
	public void testCrearAdmin() {
		
		Admin nuevoAdmin = adminService.crearAdmin(administrador, "Duro de pelar", "204gh659gh569hy");

		Admin agregadoAdmin = adminRepository.read(nuevoAdmin.getId());
		
		assertEquals("Duro de pelar", agregadoAdmin.getNick());
		assertEquals("204gh659gh569hy", agregadoAdmin.getPassword());
	}

	@Test
	public void testEditarAdmin() {
		
		boolean editado = adminService.editarAdmin(admin.getId(), admin.getAdministrador(), "Veneno",admin.getPassword());
		
		Admin nuevoAdmin = adminRepository.read(admin.getId());
		
		assertEquals(true, editado);
		
		assertEquals("Veneno", nuevoAdmin.getNick());
	}

	@Test
	public void testBorrarAdmin() {
		
		List<Admin> listaAdmin = adminRepository.list();
		assertEquals(1, listaAdmin.size());
		
		boolean borrado = adminService.borrarAdmin(admin.getId());
		
		listaAdmin = adminRepository.list();
		assertNotEquals(1,listaAdmin.size());

	}

	@Test
	public void testListarAdmin() {
		
		List<Admin> listaAdmin = adminRepository.list();
		assertEquals(1, listaAdmin.size());
		
		Admin nuevoAdmin = adminService.crearAdmin(administrador, "shooter", "fdasfdadf15646afd456as");
		
		List<Admin> listaAdmin2 = adminRepository.list();
		assertEquals(2, listaAdmin2.size());
	}

}
