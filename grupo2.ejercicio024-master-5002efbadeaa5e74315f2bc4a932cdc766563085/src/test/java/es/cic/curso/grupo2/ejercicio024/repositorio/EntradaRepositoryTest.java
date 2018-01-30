package es.cic.curso.grupo2.ejercicio024.repositorio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
import es.cic.curso.grupo2.ejercicio024.dominio.Venta;
import es.cic.curso.grupo2.ejercicio024.dominio.Pelicula;
import es.cic.curso.grupo2.ejercicio024.dominio.Sala;
import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	 TransactionalTestExecutionListener.class})
@Transactional
public class EntradaRepositoryTest
{
	//Repositorio Creado Mediante SPRING
	@Autowired
	VentaRepository sut;

	@PersistenceContext
	protected EntityManager em;

	//Clases
	Sala sala1;
	Pelicula pelicula1;
	Sesion sesion1m;
	Venta entrada1;
	
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
		em.persist(sesion1m);
		
		//Entrada
		entrada1 = new Venta(1,sesion1m,Sesion.PRECIO);
	}
	
	//Constructor Vacio
	public EntradaRepositoryTest(){
		super();
	}
	
	@Test
	public void testAdd()
	{
		//Crea Sesion en la DDBB (Genera su ID)
		sut.add(entrada1);
		
		//Comprueba que se ha generado la ID automaticamente
		assertNotNull(entrada1.getId());
	}
	
	@Test
	public void testRead()
	{
		//Lee la Clave Primaria de la Entrada Generada
		Long clavePrimaria = generaEntradaLectura();
		
		//Lee la Sesion de la DDBB
		Venta resultado = sut.read(clavePrimaria);
		
		//Comprueba que el Numero de Entrada es el Esperado
		assertTrue(1==resultado.getNumeroEntradas());
	}
	
	@Test(expected=PersistenceException.class)
	public void testRead_NoExiste()
	{
		//Lee la Clave Primaria de la Entrada Generada
		Long clavePrimaria = Long.MIN_VALUE;
		
		//Intenta Leer la Entrada de la DDBB
		sut.read(clavePrimaria);
	}
	
	@Test
	public void testList()
	{
		//Genera 3 Entradas para la Misma Sesion (1)
		generaEntradaLectura();
		generaEntradaLectura();
		generaEntradaLectura();
		
		//Lee la Lista de Sesiones de la DDBB
		List<Venta> resultado = sut.list();
		
		//Comprueba que hay 3 Sesiones en la DDBB
		assertTrue(resultado.size()==3);
	}

	@Test
	public void testUpdate()
	{
		//Lee la Clave Primaria de la Entrada Generada
		Long clavePrimaria = generaEntradaLectura();
		
		//Crea una Entrada2 con Datos Nuevos
		Venta entrada2 = new Venta();
		//Establece los Campos
		entrada2.setId(clavePrimaria);
		entrada2.setNumeroEntradas(1);
		entrada2.setSesionEntrada(sesion1m);
		//Cambiamos el Precio
		entrada2.setImporte(4.5);
		
		//Actualiza en DDBB
		Venta resultado = sut.update(entrada2);

		//Le Busca y Comprueba que Ambos tienen el Precio de la Entrada Actualizado Correcto
		Venta enBBDD = em.find(Venta.class, clavePrimaria);
		assertTrue(4.5==resultado.getImporte());
		assertTrue(4.5==enBBDD.getImporte());
	}		
		
	@Test
	public void testDelete()
	{
		//Lee la Clave Primaria de la Sesion Generada
		Long clavePrimaria = generaEntradaLectura();
		
		//Borra la Sesion de la DDBB
		sut.delete(clavePrimaria);
		
		Venta s;
		
		try{
			//Intenta Encontrar la Entrada Borrada
			s = em.find(Venta.class, clavePrimaria);
		}catch (PersistenceException pe){
			return;
		}
		//Si No lo ha Encontrado Comprueba que es Null
		assertNull(s);
	}

	private Long generaEntradaLectura()
	{	
		//Entrada
		entrada1 = new Venta(1,sesion1m,Sesion.PRECIO);
		em.persist(entrada1);
		
		//Devuelve la Clave Primaria de la Nueva Entrada
		return entrada1.getId();
	}
}
