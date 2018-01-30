package es.cic.curso.pruebas.cine.service.sesion;

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

import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sala.SalaRepository;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;
import es.cic.curso.pruebas.cine.repository.sesion.SesionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/pruebas/cine/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class SesionServiceImplTest {

	@Autowired
	SesionService sut;
	
	@Autowired
	SesionRepository sesionRepository;
	
	@Autowired
	SalaRepository salaRepository;
	
	private Sala sala;
	
	
	@Before
	public void setUp() throws Exception {
		
		limpiarSesiones();
		
		sala = new Sala(100);
		salaRepository.add(sala);
	}


	@Test
	public void testAniadirSesion() {
		Long idSesion1 = sut.aniadirSesion(0, false, sala.getId());
		
		assertNotNull(idSesion1);
	}

	@Test
	public void testObtenerSesion() {
		Long idSesion1 = sut.aniadirSesion(0, false, sala.getId());
		Sesion sesion = sut.obtenerSesion(idSesion1);
		
		assertNotNull(idSesion1);
		assertTrue(sesion.getAsientosOcupados() == 0);
		
		
	}

	@Test
	public void testObtenerSesiones() {
		List<Sesion>sesiones = sut.obtenerSesiones();
		
		for(Sesion sesion : sesiones){
			assertNotNull(sesion.getId());
		}
	}

	@Test
	public void testActualizarSesion() {
		Long idSesion1 = sut.aniadirSesion(0, false, sala.getId());
		Sesion sesion = sut.obtenerSesion(idSesion1);
		sesion.setAsientosOcupados(10);
		
		Sesion sesionMod = sut.obtenerSesion(idSesion1);
		
		assertTrue(sesionMod.getAsientosOcupados() == 10);
		
	}

	@Test
	public void testBorrarSesion() {
		Long idSesion1 = sut.aniadirSesion(0, false, sala.getId());
		
		sut.borrarSesion(idSesion1);
		
		List<Sesion>sesiones = sut.obtenerSesiones();
		
		assertTrue(sesiones.size() == 0);
	}
	
	private void limpiarSesiones(){
		List<Sesion>sesiones = sut.obtenerSesiones();
		for(Sesion sesion : sesiones){
			sesionRepository.delete(sesion);
		}
	}

}