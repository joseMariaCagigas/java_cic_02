package es.cic.curso.curso10.curso10.ejercicio023.repositorio;

import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso10/curso10/ejercicio023/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	 TransactionalTestExecutionListener.class})
@Transactional
public class PeliculaRepositoryTest
{
	//Repositorio Creado Mediante SPRING
	@Autowired
	PeliculaRepository sut;

	@PersistenceContext
	protected EntityManager em;

	//Clases
	Pelicula pelicula1;
	
	@Before
	public void setUp()
	{
		//Inicializa Peliculas
		pelicula1 = new Pelicula("Hachiko","Director",10);
		em.persist(pelicula1);
	}
	
	//Constructor Vacio
	public PeliculaRepositoryTest(){
		super();
	}
	
	@Test
	public void test()
	{
		generaPeliculaLectura();
		assertTrue(true);
	}

	private Long generaPeliculaLectura()
	{	
		//Pelicula
		pelicula1 = new Pelicula("TituloLectura","DirectorLectura",30);
		em.persist(pelicula1);
		
		//Devuelve la Clave Primaria
		return pelicula1.getId();
	}
}
