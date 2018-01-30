package es.cic.curso.curso06.ejercicio014.versionII.servicios;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio014.versionII.repositorios.ProductoRepository;
import es.cic.curso.curso06.ejercicio014.versionII.repositorios.VentaRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso06/ejercicio014/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class VentaServiceImplTest {
	
	private static final double CANTIDAD_VARIABLE = 0.001;

	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private VentaService sut;
	
	@Autowired
	private ProductoRepository salaRepository;

	
	@Autowired
	private VentaRepository ventaRepository;	
	
	@Before
	public void setUp() throws Exception {

		diaNuevo();
		
	}



	private int diaNuevo() {
		
		int ventasDelDia = 0;
		return ventasDelDia;
				
	}



	@Test
	public void testCalcularPrecio() {

	}

	
	}	
