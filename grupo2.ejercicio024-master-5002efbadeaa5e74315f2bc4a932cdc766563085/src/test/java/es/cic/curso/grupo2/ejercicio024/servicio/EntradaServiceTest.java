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
import es.cic.curso.grupo2.ejercicio024.repositorio.VentaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class EntradaServiceTest {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	VentaRepository entradaRepository;
	
	@Autowired
	VentaService sut;

	//Clases
	private Sala sala1;
	private Pelicula pelicula1;
	private Sesion sesion1m;
	
	@Before
	public void setUp() throws Exception
	{
		limpiar();
	
		//Inicializa Salas
		sala1 = new Sala(1,100);
		em.persist(sala1);
		
		//Inicializa Peliculas
		pelicula1 = new Pelicula("Hachiko");
		em.persist(pelicula1);
		
		//Inicializa Sesiones
		sesion1m = new Sesion(pelicula1, sala1, 10.00);
		sesion1m.setAbierta(true);
		sesion1m.setAsientosDisponibles(50);
		em.persist(sesion1m);
		
		inicializa();
	}
	
	@Test
	public void testGetVentas(){
		List<Venta> lista = sut.getVentas();
		assertEquals(lista.size(), 3);
	}
	
	@Test
	public void leeVentaTest(){
 		List<Venta> lista = sut.getVentas();
 		assertNotNull(sut.leeVenta(lista.get(0).getId()));
	}
	
	@Test
	public void nuevaVentaTest(){
		Venta entrada1 = new Venta(1,sesion1m,Sesion.PRECIO);
		sut.nuevaVenta(entrada1);
		Venta entrada2 = entradaRepository.read(entrada1);
		assertEquals(entrada1, entrada2);
	}
	@Test
	public void editaVentaTest()
	{
		Venta entrada1 = new Venta(1,sesion1m,Sesion.PRECIO);
		sut.nuevaVenta(entrada1);
 		Long claveInicial = entrada1.getId();
 		
 		assertEquals(1,entrada1.getNumeroEntradas());
 		
 		entrada1.setNumeroEntradas(5);
 		sut.editaVenta(claveInicial,entrada1);
 		em.flush();
 		
 		assertEquals(5,entrada1.getNumeroEntradas());
 		
 		Long claveDDBB = entrada1.getId(); 		
 		Venta entradaDDBB = em.find(Venta.class,claveDDBB);
 		
 		assertEquals(claveDDBB,claveInicial);
		assertEquals(5,entradaDDBB.getNumeroEntradas());
	}
	
	@Test
	public void borrarVentaTest(){
		Venta entrada1 = new Venta(1,sesion1m,Sesion.PRECIO);
		sut.nuevaVenta(entrada1);
		Long id = entrada1.getId();
		sut.borraVenta(id);
		Venta entrada2 = entradaRepository.read(id);
		assertNull(entrada2);
	}

	public void inicializa()
	{				
		Venta entrada1 = new Venta(1,sesion1m,Sesion.PRECIO);
		Venta entrada2 = new Venta(2,sesion1m,Sesion.PRECIO);
		Venta entrada3 = new Venta(3,sesion1m,4.5);
		
		em.persist(entrada1);
		em.persist(entrada2);
		em.persist(entrada3);
	}
	
	public void limpiar(){
		List<Venta> lista = entradaRepository.list();
		for (Venta entrada: lista) {
			entradaRepository.delete(entrada);
		}
	}

}
