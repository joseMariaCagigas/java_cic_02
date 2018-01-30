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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class AdminServiceTest2 {
	
	@Autowired
	AdminService sut;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdministradorRepository admininistradorRepository;	
	
	private Administrador administradorId;

	@Before
	public void setUp() throws Exception {
		limpiarAdmin();
	}

	private void limpiarAdmin() {
		List<Admin> adminl = sut.obtenerAdmin2();
		for(Admin admin : adminl){
			adminRepository.delete(admin);
		}
		
	}
	@Test
	public void testAniadirAdmin2() {
		
		Long idAdmin = sut.aniadirAdmin2(administradorId.getId(), "Willow", "victim");
		assertNotNull(idAdmin);
	}

	@Test
	public void testObtenerAdmins2() {
		
		List<Admin>adminl = sut.obtenerAdmin2();
		for(Admin admin : adminl){
			assertNotNull(admin.getId());
		}
	}

	@Test
	public void testObtenerAdmin2() {

		Long idAdmin = sut.aniadirAdmin2(administradorId.getId(), "Willow", "victim");
		assertNotNull(idAdmin);
		
		Admin admin = sut.obtenerAdmin2(idAdmin);
		
		assertNotNull(admin.getId());
		assertTrue(admin.getPassword() == "victim");
	}

	@Test
	public void testActualizarAdmin2() {
		
		Long idAdmin = sut.aniadirAdmin2(administradorId.getId(), "Willow", "victim");
		assertNotNull(idAdmin);
		
		Admin admin = sut.obtenerAdmin2(idAdmin);
		admin.setPassword("Tordo");
		Admin adminActualizada = sut.obtenerAdmin2(idAdmin);
		
		assertTrue(adminActualizada.getPassword() == "victim");
		assertNotNull(adminActualizada);
	}
	

	@Test
	public void testBorrarAdmin2() {

		Long idAdmin1 = sut.aniadirAdmin2(administradorId.getId(), "Willow", "victim");

		sut.borrarAdmin2(idAdmin1);
		System.out.println(idAdmin1);
		List<Admin>adminl = sut.obtenerAdmin2();
		System.out.println(idAdmin1);
		assertTrue(adminl.size() == 0);
		
	}

}
