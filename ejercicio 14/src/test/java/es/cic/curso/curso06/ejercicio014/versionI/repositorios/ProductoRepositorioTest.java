package es.cic.curso.curso06.ejercicio014.versionI.repositorios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.cic.curso.curso06.ejercicio014.versionI.archivos.Producto;
import es.cic.curso.curso06.ejercicio014.versionI.repositorios.ProductoRepositorio;

public class ProductoRepositorioTest {

	ProductoRepositorio classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		
		classUnderTest = new ProductoRepositorio();
	}

	@Test
	public void cargaInicialTest() {
		
		for(Producto productoActual:classUnderTest.getListaProductos()) {
			
			switch( productoActual.getId() ) {
			case 1:
				assertEquals("Nombre del cafe no entendible", "cafe", productoActual.getNombre().toLowerCase());
				assertEquals("Precio del cafe cargado incorrectamente", 4, productoActual.getPrecio());
				break;
			case 2:
				assertEquals("Nombre de la leche no entendible", "leche", productoActual.getNombre().toLowerCase());
				assertEquals("Precio de la leche cargado incorrectamente", 1, productoActual.getPrecio());
				break;
			case 3:
				assertEquals("Nombre de la caja de galletas no entendible", "caja de galletas", productoActual.getNombre().toLowerCase());
				assertEquals("Precio de la caja de galletas cargado incorrectamente", 2, productoActual.getPrecio());
				break;
			}
		}
		
	}
	
	@Test
	public void getProductoPorIndiceTest() {
		
		Producto producto1 = classUnderTest.getProductoPorIndice(1);
		Producto producto2 = classUnderTest.getProductoPorIndice(2);
		Producto producto3 = classUnderTest.getProductoPorIndice(3);
		
		assertEquals("Cafe no localizado por su indice", "cafe", producto1.getNombre().toLowerCase());
		assertEquals("Leche no localizada por su indice", "leche", producto2.getNombre().toLowerCase());
		assertEquals("Caja de galletas no localizada por su indice", "caja de galletas", producto3.getNombre().toLowerCase());
	}
	
	@Test
	public void getProductoPorNombreTest() {
		
		Producto producto1 = classUnderTest.getProductoPorNombre("CAFE");
		Producto producto2 = classUnderTest.getProductoPorNombre("LECHE");
		Producto producto3 = classUnderTest.getProductoPorNombre("cAjA dE gAlLeTaS");
		
		assertEquals("Cafe no localizado por su nombre", 1, producto1.getId());
		assertEquals("Leche no localizada por su nombre", 2, producto2.getId());
		assertEquals("Caja de galletas no localizada por su nombre", 3, producto3.getId());
		
	}

}
