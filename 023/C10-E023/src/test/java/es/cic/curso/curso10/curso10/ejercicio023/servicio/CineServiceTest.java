package es.cic.curso.curso10.curso10.ejercicio023.servicio;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso10/curso10/ejercicio023/applicationContext.xml"
				})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CineServiceTest
{
	//Clase de Prueba
	@Autowired
	CineController cut;
	
	@Autowired
	CineService cineService;
	
	@Before
	public void setUp() throws Exception
	{		
		//Inicializa Peliculas
		Pelicula pelicula1 = new Pelicula("Hachiko","D",500);
		Pelicula pelicula2 = new Pelicula("Mi Vida sin Mi","D",500);
		Pelicula pelicula3 = new Pelicula("Swing Kids","D",500);
		cineService.getPeliculas().add(pelicula1);
		cineService.getPeliculas().add(pelicula2);
		cineService.getPeliculas().add(pelicula3);
	}

	@Test
	public void test()
	{
		assertTrue(true);
	}
}
