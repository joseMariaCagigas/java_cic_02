package es.cic.curso.grupo2.ejercicio024.servicio;

import static org.junit.Assert.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import es.cic.curso.grupo2.ejercicio024.dominio.*;
import es.cic.curso.grupo2.ejercicio024.repositorio.PeliculaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class PeliculaServiceTest {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	@Autowired
	PeliculaService sut;
	
	@Before
	public void setUp() throws Exception
	{
		limpiar();		
		inicializa();
	}
	
	@Test
	public void testGetPeliculas(){
		List<Pelicula> lista = sut.getPeliculas();
		assertEquals(lista.size(), 3);
	}
	
	@Test
	public void leePeliculaTest(){
 		List<Pelicula> lista = sut.getPeliculas();
 		assertNotNull(sut.leePelicula(lista.get(0).getId()));
	}
	
	@Test
	public void nuevaPeliculaTest(){
		Pelicula pelicula1 = new Pelicula("Hachiko");
		sut.nuevaPelicula(pelicula1);
		Pelicula pelicula2 = peliculaRepository.read(pelicula1);
		assertEquals(pelicula1, pelicula2);
	}
	@Test
	public void editaPeliculaTest()
	{
		Pelicula pelicula1 = new Pelicula("Hachiko");
		sut.nuevaPelicula(pelicula1);
 		Long claveInicial = pelicula1.getId();
 		
 		assertEquals("Hachiko",pelicula1.getTitulo());
 		
 		pelicula1.setTitulo("Ghost in the Shell");
 		sut.editaPelicula(claveInicial,pelicula1);
 		em.flush();
 		
 		assertEquals("Ghost in the Shell",pelicula1.getTitulo());
 		
 		Long claveDDBB = pelicula1.getId(); 		
 		Pelicula peliculaDDBB = em.find(Pelicula.class,claveDDBB);
 		
 		assertEquals(claveDDBB,claveInicial);
		assertEquals("Ghost in the Shell",peliculaDDBB.getTitulo());
	}
	
	@Test
	public void borrarPeliculaTest(){
		Pelicula pelicula1 = new Pelicula("Hachiko");
		sut.nuevaPelicula(pelicula1);
		Long id = pelicula1.getId();
		sut.borraPelicula(id);
		Pelicula pelicula2 = peliculaRepository.read(id);
		assertNull(pelicula2);
	}

	public void inicializa()
	{				
		Pelicula pelicula1 = new Pelicula("Hachiko");
		Pelicula pelicula2 = new Pelicula("Truman");
		Pelicula pelicula3 = new Pelicula("Magical Girl");
		
		em.persist(pelicula1);
		em.persist(pelicula2);
		em.persist(pelicula3);
	}
	
	public void limpiar(){
		List<Pelicula> lista = peliculaRepository.list();
		for (Pelicula pelicula: lista) {
			peliculaRepository.delete(pelicula);
		}
	}

}
