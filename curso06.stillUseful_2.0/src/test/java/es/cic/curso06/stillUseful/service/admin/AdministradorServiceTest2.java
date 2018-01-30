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

import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.repository.admin.AdministradorRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/stillUseful/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class AdministradorServiceTest2 {
	
	@Autowired
	AdministradorService sut;
	
	@Autowired
	AdministradorRepository administradorRepository;

	@Before
	public void setUp() throws Exception {
		limpiarAdministradores();
	}

	private void limpiarAdministradores() {
		List<Administrador> administradores = sut.obtenerAdministradores2();
		for(Administrador administrador : administradores){
			administradorRepository.delete(administrador);
		}
		
	}
	@Test
	public void testAniadirAdministrador2() {
		
		Long idAdministrador = sut.aniadirAdministrador2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idAdministrador);
	}

	@Test
	public void testObtenerAdministradores2() {
		
		List<Administrador>administradores = sut.obtenerAdministradores2();
		for(Administrador administrador : administradores){
			assertNotNull(administrador.getId());
		}
	}

	@Test
	public void testObtenerAdministrador2() {

		Long idAdministrador = sut.aniadirAdministrador2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idAdministrador);
		
		Administrador administrador = sut.obtenerAdministrador2(idAdministrador);
		
		assertNotNull(administrador.getId());
		assertTrue(administrador.getCodigoPostal() == 39012);
	}

	@Test
	public void testActualizarAdministrador2() {
		
		Long idAdministrador = sut.aniadirAdministrador2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idAdministrador);
		
		Administrador administrador = sut.obtenerAdministrador2(idAdministrador);
		administrador.setCodigoPostal(39011);
		Administrador administradorActualizada = sut.obtenerAdministrador2(idAdministrador);
		
		assertTrue(administradorActualizada.getCodigoPostal() == 39011);
		assertNotNull(administradorActualizada);
	}
	

	@Test
	public void testBorrarAdministrador2() {

		Long idAdministrador = sut.aniadirAdministrador2("Manuel", "Lastas Pocas", "72569321D", "Verano Azul 23", "Santander",
				"Cantabria", "Cantabria", 39012, "capsici@gmail.com");
		assertNotNull(idAdministrador);
		
		sut.borrarAdministrador2(idAdministrador);
		
		List<Administrador>administradores = sut.obtenerAdministradores2();;
		
		assertTrue(administradores.size() == 0);
		
	}

}
