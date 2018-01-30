package es.cic.curso.grupo2.ejercicio024.repositorio;

import static org.junit.Assert.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
import es.cic.curso.grupo2.ejercicio024.dominio.*;
import es.cic.curso.grupo2.ejercicio024.repositorio.SesionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	 TransactionalTestExecutionListener.class})
@Transactional
public class SesionRepositoryTest
{
	//Repositorio Creado Mediante SPRING
	@Autowired
	SesionRepository sut;

	@PersistenceContext
	protected EntityManager em;

	//Clases
	Sala sala1;
	Pelicula pelicula1;
	Sesion sesion1m;
	
	@Before
	public void setUp()
	{
		//Inicializa Salas
		sala1 = new Sala(1,100);
		em.persist(sala1);
		
		//Inicializa Peliculas
		pelicula1 = new Pelicula("Hachiko");
		em.persist(pelicula1);
		
		//Sesion Mañana
		sesion1m = new Sesion(pelicula1, sala1, 10.00);
		sesion1m.setAbierta(true);
		sesion1m.setAsientosDisponibles(50);
	}
	
	//Constructor Vacio
	public SesionRepositoryTest(){
		super();
	}
	
	@Test
	public void testAdd()
	{
		//Crea Sesion en la DDBB (Genera su ID)
		sut.add(sesion1m);
		
		//Comprueba que se ha generado la ID automaticamente
		assertNotNull(sesion1m.getId());
	}
	
	@Test
	public void testRead()
	{
		//Lee la Clave Primaria de la Sesion Generada
		Long clavePrimaria = generaSesionLectura();
		
		//Lee la Sesion de la DDBB
		Sesion resultado = sut.read(clavePrimaria);
		
		//Comprueba que el Numero de Sesion es el Esperado
		assertTrue(10.00==resultado.getHora());
	}
	
	@Test(expected=PersistenceException.class)
	public void testRead_NoExiste()
	{
		//Lee la Clave Primaria de la Sesion Generada
		Long clavePrimaria = Long.MIN_VALUE;
		
		//Intenta Leer la Sesion de la DDBB
		sut.read(clavePrimaria);
	}
	
	@Test
	public void testList()
	{
		//Genera 3 Sesiones para la Misma Sala (1)
		generaSesionLectura();
		generaSesionLectura();
		generaSesionLectura();
		
		//Lee la Lista de Sesiones de la DDBB
		List<Sesion> resultado = sut.list();
		
		//Comprueba que hay 3 Sesiones en la DDBB
		assertTrue(resultado.size()==3);
	}

	@Test
	public void testUpdate()
	{
		//Lee la Clave Primaria de la Venta Generada
		Long clavePrimaria = generaSesionLectura();
		
		
		//Cambia el Titulo de la Pelicula
		pelicula1.setTitulo("Truman");
		
		//Crea una Venta2 con Datos Nuevos
		Sesion sesion2 = new Sesion();
		//Establece los Campos
		sesion2.setPelicula(pelicula1);
		sesion2.setSala(sala1);
		sesion2.setHora(10.00);
		sesion2.setAsientosDisponibles(95);
		sesion2.setAbierta(true);
		
		//Actualiza en DDBB
		Sesion resultado = sut.update(sesion2);

		//Le Busca y Comprueba que Ambos tienen la Pelicula Actualizada Correcta
		Sesion enBBDD = em.find(Sesion.class, clavePrimaria);
		assertEquals("Truman",resultado.getPelicula().getTitulo());
		assertEquals("Truman",enBBDD.getPelicula().getTitulo());
	}		
		
	@Test
	public void testDelete()
	{
		//Lee la Clave Primaria de la Sesion Generada
		Long clavePrimaria = generaSesionLectura();
		
		//Borra la Sesion de la DDBB
		sut.delete(clavePrimaria);
		
		Sesion s;
		
		try{
			//Intenta Encontrar la Sesion Borrada
			s = em.find(Sesion.class, clavePrimaria);
		}catch (PersistenceException pe){
			return;
		}
		//Si No lo ha Encontrado Comprueba que es Null
		assertNull(s);
	}

	private Long generaSesionLectura()
	{	
		//Sesion Mañana
		sesion1m = new Sesion(pelicula1, sala1, 10.00);
		em.persist(sesion1m);
		
		//Devuelve la Clave Primaria de la Nueva Venta
		return sesion1m.getId();
	}
}
