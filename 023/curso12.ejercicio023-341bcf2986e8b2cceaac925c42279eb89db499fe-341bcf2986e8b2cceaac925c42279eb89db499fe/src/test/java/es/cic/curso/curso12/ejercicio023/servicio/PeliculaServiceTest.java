package es.cic.curso.curso12.ejercicio023.servicio;

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

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;
import es.cic.curso.curso12.ejercicio023.dominio.PeliculaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso12/ejercicio023/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class PeliculaServiceTest {

	@Autowired
	private PeliculaService sut;
	
	@Autowired
	private PeliculaRepository peliculaRepo;
	
	private String titulo;
	private String director;
	private String productora;
	private String interprete;
	private String genero;
	private int year;
	private int duracion;
	
	@Before
	public void setUp() throws Exception {
		limpiarPeliculas();
		
		titulo = "Pain & Gain";
		director = "Michael Bay";
		productora = "universal";
		interprete = "La Roca";
		year = 2013;
		duracion = 129;
		genero = "Crimen";
	}

	@Test
	public void testAniadirPelicula() {
		 Long peliId = sut.aniadirPelicula(titulo, director, productora, interprete, year, duracion, genero);
		 
		 List<Pelicula>p = sut.obtenerPeliculas();
		 assertNotNull(p);
		 assertNotNull(peliId);
	}
	
	@Test
	public void testObtenerPelicula(){
		Long peliId = sut.aniadirPelicula("x", "d", "f", "r", 2034, 324, "wer");
		
		Pelicula peli = sut.obtenerPelicula(peliId);
		
		assertNotNull(peli.getId());
		assertTrue(peli.getDuracion()==324);
	}
	
	@Test
	public void testObtenerPeliculas(){
		int n= 4;
		
		for (int i = 0; i<n;i++){
			Pelicula p = new Pelicula();
			p.setYear(2000);
			peliculaRepo.add(p);
		}
		List<Pelicula> peliculas = sut.obtenerPeliculas();
		assertEquals(n,peliculas.size());
	}
	@Test
	public void testActualizarPeliculas(){
		Long peliId = sut.aniadirPelicula(titulo, director, productora, interprete, year, duracion, genero);

		Pelicula pMod = sut.obtenerPelicula(peliId);		
		pMod.setInterprete("Mark Whalber");
		
		Pelicula p = sut.obtenerPelicula(peliId);
		
		assertFalse(p.getInterprete() != pMod.getInterprete());
		
	}
	
	@Test
	public void testBorrarPelicula(){
		Long peliId = sut.aniadirPelicula(titulo, director, productora, interprete, year, duracion, genero);
		
		sut.borrarPelicula(peliId);
		
		List<Pelicula> pelis = sut.obtenerPeliculas();
		assertTrue(pelis.size() ==0);
	}

	
	private void limpiarPeliculas(){
		List<Pelicula> peliculas = sut.obtenerPeliculas();
		for (Pelicula p:peliculas){
			peliculaRepo.delete(p);
		}
	}
}
