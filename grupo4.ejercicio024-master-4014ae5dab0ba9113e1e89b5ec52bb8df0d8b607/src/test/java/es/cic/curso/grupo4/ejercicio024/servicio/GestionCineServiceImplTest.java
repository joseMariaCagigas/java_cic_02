package es.cic.curso.grupo4.ejercicio024.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
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

 
import es.cic.curso.grupo4.ejercicio024.dominio.Sala;
import es.cic.curso.grupo4.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo4.ejercicio024.dominio.Venta;
import es.cic.curso.grupo4.ejercicio024.repositorio.SalaRepository;
import es.cic.curso.grupo4.ejercicio024.repositorio.SesionRepository;
import es.cic.curso.grupo4.ejercicio024.repositorio.VentaRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo4/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})

@Transactional
public class GestionCineServiceImplTest {

	@Autowired
	private GestionCineService sut;

	@Autowired
	private VentaRepository ventaRepo;
	
	@Autowired
	private SalaRepository salaRepo;
	

	@Autowired
	private SesionRepository sesionRepo;
	
	@PersistenceContext
	private EntityManager em;

	private Sala	sala1,sala2,sala3;
	private Sesion  sala1Sesion1,sala1Sesion2,sala1Sesion3;
	private Sesion	sala2Sesion1,sala2Sesion2,sala2Sesion3;
	private Sesion	sala3Sesion1,sala3Sesion2,sala3Sesion3;

	
	private static final boolean HAY_DESCUENTO = true;
	private static final boolean NO_HAY_ESCUENTO = false;
	
	private static final boolean ES_DEVLUCION = true;
	private static final boolean ES_VENTA = false;
	
	@Before
	public void setUp() throws Exception {

		limpiarSalas();
		limpiarSesiones();
		limpiarVentas();

		generaBBDD();

	}


	@Test 
	public void testCalcularPrecio() {


		double resultado = sut.calcularPrecio(11);

		assertEquals(49.5,resultado,0.001);
	}
	
	@Test
	public void testComprobarEstadoSesion(){
		
		assertTrue(sala1Sesion1.isAbierto());
		
	}
	
	@Test 
	public void testCambiarACerrado(){
		
		Sesion sesionCerrada = sut.cambiarEstadoSesion(sala1Sesion1);
		assertFalse(sesionCerrada.isAbierto());
		
	}

	@Test
	public void testHayEntradas(){

		assertTrue(sut.hayAsientos(50, sala1Sesion1));
		assertFalse(sut.hayAsientos(31, sala3Sesion1));

	}
	
	@Test
	public void testHayDescuento(){
		
		assertTrue(sut.hayDescuento(6));
		assertFalse(sut.hayDescuento(4));
		
	}

	@Test
	public void testVentaConDescuento(){
		
		Venta venta=sut.venderEntradas(6, sala1Sesion1, sala1);
		
		assertTrue(27 == sala1.getRecaudacion());
 
		
		assertTrue(venta.isDescuento());
		assertTrue(27==venta.getPrecio());
	}
	@Test 
	public void testeVenderEntradas()throws FileNotFoundException, IOException {


		sut.venderEntradas(6, sala1Sesion1, sala1);
		sut.venderEntradas(6, sala2Sesion1, sala2);
		sut.venderEntradas(6, sala3Sesion1, sala3);
		
		assertTrue(94.0==sala1Sesion1.getButacasDisp());
		assertTrue(44.0==sala2Sesion1.getButacasDisp());
		assertTrue(24.0==sala3Sesion1.getButacasDisp());

	}

	@Test(expected=es.cic.curso.grupo4.ejercicio024.exceptions.VentaException.class)
	public void esteVenderEntradas_No_Disponibles()throws FileNotFoundException, IOException {


		sut.venderEntradas(101,sala1Sesion1, sala1);	
	}

	@Test
	public void testVentaCompleta(){
			
		
		boolean abierto = sut.comprobarEstadoSesion(sala1Sesion1);
		
		assertTrue(abierto);
		
		boolean hay=false;
		
		Venta venta= new Venta();
		
		if(abierto){
			
			hay = sut.hayAsientos(3, sala1Sesion1);
			
			if(hay){
				
				venta =sut.venderEntradas(3, sala1Sesion1, sala1);
					
			}			
		}
				
	
		assertTrue(hay);
		assertEquals(97,sala1Sesion1.getButacasDisp());
		assertTrue(15==sala1.getRecaudacion());
		assertFalse(venta.isDescuento());
		assertTrue(venta.getPrecio()==15);
			
	}
		
	@Test
	public void testRecaudacionSala()throws FileNotFoundException, IOException{

		sut.venderEntradas(3,sala1Sesion1, sala1);
		sut.venderEntradas(4,sala1Sesion1, sala1);
		sut.venderEntradas(3,sala1Sesion1, sala1);

		assertTrue(50==sala1.getRecaudacion());

	}

	@Test
	public void testRecaudacionTotal(){


		sut.venderEntradas(4, sala1Sesion1, sala1);
		sut.venderEntradas(12, sala2Sesion1, sala2); //venta rebajada
		sut.venderEntradas(6, sala3Sesion1, sala3);  //venta rebajada

		double recaudacion = sut.calculaPrecioTotal(sala1, sala2, sala3);

		assertTrue(101==recaudacion);

	}

	@Test
	public void testListaVentas(){

		//cuando vendemos, dentro del metodo se genera una nueva venta en bbdd
		//al vender 4 veces tendremos 4
		
		sut.venderEntradas(5, sala1Sesion2, sala1);
		sut.venderEntradas(5, sala2Sesion1, sala2);
		sut.venderEntradas(5, sala2Sesion3, sala2);
		sut.venderEntradas(5, sala3Sesion1, sala3);
		
		//leermos las 4 ventas en base de datos, tamaño >= 4
		
		List<Venta> listaVenta = ventaRepo.list();
		
		assertTrue(listaVenta.size()==4);
	}

	@Test
	public void testNoPuedoDevolver(){
		
		
		//no puedo devolver si no ha habido venta previa
		
		Venta ventaTipoDevolucion = new Venta(2,sala1,sala1Sesion1,-10,NO_HAY_ESCUENTO,ES_DEVLUCION);
		
		boolean puedo = sut.puedoDevolverEntradas(ventaTipoDevolucion);
		 
		assertFalse(puedo);
		
	}	
	@Test
	public void testPuedoDevolver(){
		
		Venta ventaAdevolver =  new Venta(6,sala1, sala1Sesion1,27,true,true,false,false);
		boolean puedo = sut.puedoDevolverEntradas(ventaAdevolver);
		
		assertTrue(puedo);
		
	}
	
	@Test 
	public void testDevolverEntradas(){
		
		
		Venta venta = sut.venderEntradas(6, sala1Sesion1, sala1);
		
		assertEquals(venta.getSesiones().getButacasDisp(),94);
		assertTrue(sala1.getRecaudacion()== 27.0);
		
		
		sut.devolverEntradas(venta);
		
		assertTrue(sala1.getRecaudacion()== 0.0);
		assertEquals (sala1Sesion1.getButacasDisp(),100);
		
	
		
	}
	
	@Test
	public void testCambiarEntradas(){
		
		
		Venta ventaACambiar = sut.venderEntradas(6, sala1Sesion1, sala1);
		
		assertTrue(sala1.getRecaudacion()== 27);
		assertEquals(sala1Sesion1.getButacasDisp(),94);
		
	    sut.cambioEntradas(ventaACambiar,sala2,sala2Sesion2);
		
		assertTrue(sala1.getRecaudacion()== 0);
		assertTrue(sala2.getRecaudacion()== 27);
		assertEquals(sala2Sesion2.getButacasDisp(),44);
		assertEquals(sala1Sesion1.getButacasDisp(),100);
	}
	
	private void generaBBDD() {

		//inicializamos las 3 salas

		sala1 =new Sala(1,100,0);
		salaRepo.add(sala1);

		sala2 =new Sala(2,50,0);
		salaRepo.add(sala2);

		sala3 =new Sala(1,30,0);
		salaRepo.add(sala3);

		//3 sessiones por sala ,inicializamos las 3 abiertas

		sala1Sesion1 = new Sesion(1,sala1.getButacasTotales(),true);
		sesionRepo.add(sala1Sesion1); 

		sala1Sesion2 = new Sesion(2,sala1.getButacasTotales(),true);
		sesionRepo.add(sala1Sesion2); 

		sala1Sesion3 = new Sesion(3,sala1.getButacasTotales(),true);
		sesionRepo.add(sala1Sesion3); 

		sala2Sesion1 = new Sesion(1,sala2.getButacasTotales(),true);
		sesionRepo.add(sala2Sesion1); 

		sala2Sesion2 = new Sesion(2,sala2.getButacasTotales(),true);
		sesionRepo.add(sala2Sesion2); 

		sala2Sesion3 = new Sesion(3,sala2.getButacasTotales(),true);
		sesionRepo.add(sala2Sesion3); 

		sala3Sesion1 = new Sesion(1,sala3.getButacasTotales(),true);
		sesionRepo.add(sala3Sesion1); 

		sala3Sesion2 = new Sesion(2,sala3.getButacasTotales(),true);
		sesionRepo.add(sala3Sesion2); 

		sala3Sesion3 = new Sesion(3,sala3.getButacasTotales(),true);
		sesionRepo.add(sala3Sesion3); 


	}	

	//metodo privado
	private void limpiarSalas(){

		List<Sala> listaSalas = salaRepo.list();
		for (Sala sala: listaSalas) {
			Long salaId = sala.getId();
			salaRepo.delete(salaId);

		}
	}
	//metodo privado
	private void limpiarSesiones(){

		List<Sesion> listaSesiones = sesionRepo.list();
		for (Sesion sesion: listaSesiones) {
			Long sesionId = sesion.getId();
			sesionRepo.delete(sesionId);

		}
	}
	//metodo privado
	private void limpiarVentas() {
		List<Venta> listaVentas = ventaRepo.list();
		for (Venta venta: listaVentas) {
			Long ventaId = venta.getId();
			ventaRepo.delete(ventaId);

		}
	}
}