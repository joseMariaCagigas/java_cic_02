package es.cic.curso.grupo3.ejercicio024.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

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

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.helper.TestHelper;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo3/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class SalaServiceTest {

	@Autowired
	private SalaService salaService;
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private TestHelper tHelper;

	Long claveSala;
	Sala sala;
	
	@Before
	public void setUp() throws Exception {
		claveSala = tHelper.generaSalaTresSesionTres();
		sala = salaRepository.read(claveSala);
	}

	@Test
	public void testEsCerradoSesion(){
		boolean resultado;
		
		resultado = salaService.esCerradoSesion(sala);
		
		assertEquals(false, resultado);
	}
	
	@Test
	public void testCerrarSesion(){
		salaService.cerrarSesion(1, 1);
		
		Collection<Sala> listaSalas = salaService.getSalas(); 
		
		assertEquals(1, listaSalas.size());
		assertEquals(30, sala.getAsientosLibres());
	}
	
	@Test
	public void testHayAsientosLibres(){
		boolean resultado;
		
		resultado = salaService.hayAsientosLibres(sala, 10);
		
		assertEquals(true, resultado);
	}
	
	@Test
	public void testGetSalas(){
		Collection<Sala> listaSalas = salaService.getSalas(); 
		
		assertEquals(1, listaSalas.size());
	}
	
	@Test
	public void testVaciarSalas(){
		tHelper.generaSalaTresSesionTres();
		tHelper.generaSalaTresSesionTres();
		tHelper.generaSalaTresSesionTres();
		
		Collection<Sala> listaSalas = salaService.getSalas(); 
		
		assertEquals(4, listaSalas.size());
		
		salaService.vaciarSalas();
		
		listaSalas = salaService.getSalas(); 
		assertEquals(0, listaSalas.size());
	}
	
	@Test
	public void testNuevaSala_Integracion(){
		tHelper.generaSalaTresSesionTres();
		tHelper.generaSalaTresSesionTres();
		tHelper.generaSalaTresSesionTres();
		
		Collection<Sala> listaSalas = salaService.getSalas(); 
		
		assertEquals(4, listaSalas.size());
		
		salaService.vaciarSalas();
		
		listaSalas = salaService.getSalas(); 
		assertEquals(0, listaSalas.size());
		
		claveSala = tHelper.generaSalaTresSesionTres();
		sala = salaRepository.read(claveSala);
		
		salaService.nuevaSala(sala);
		
		listaSalas = salaService.getSalas(); 
		assertEquals(1, listaSalas.size());
	}
}