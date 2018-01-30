package es.cic.curso.curso06.ejercicio014.versionI.servicios;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.cic.curso.curso06.ejercicio014.versionI.servicios.Inventario;

public class InventarioTest {

Inventario cut;
	
	@Before
	public void setUp() throws Exception {
		
		cut = new Inventario();
	}

	@Test
	public void testAniadirEntradaTienda() {
		
		cut.aniadirEntradaTienda(1, 5);
		cut.aniadirEntradaTienda(3, 2);
		cut.aniadirEntradaTienda(2, 8);
		cut.aniadirEntradaTienda(1, 7);
		cut.aniadirEntradaTienda(3, 9);
		
		assertEquals( 5, cut.obtenerTamanioEntradaTienda() );
		assertEquals( 12, cut.obtenerUnidadesStockArticuloTienda(1) );
		assertEquals( 8, cut.obtenerUnidadesStockArticuloTienda(2) );
		assertEquals( 11, cut.obtenerUnidadesStockArticuloTienda(3) );
	}

	@Test
	public void testAniadirEntradaAlmacen() {
		
		cut.aniadirEntradaAlmacen(2, 5);
		cut.aniadirEntradaAlmacen(1, 2);
		cut.aniadirEntradaAlmacen(3, 8);
		cut.aniadirEntradaAlmacen(2, 7);
		cut.aniadirEntradaAlmacen(1, 9);
		
		assertEquals( 5, cut.obtenerTamanioEntradaAlmacen() );
		assertEquals( 11, cut.obtenerUnidadesStockArticuloAlmacen(1) );
		assertEquals( 12, cut.obtenerUnidadesStockArticuloAlmacen(2) );
		assertEquals( 8, cut.obtenerUnidadesStockArticuloAlmacen(3) );
	}

	@Test
	public void testAniadirEntradaDesdeString() {
		
		cut.aniadirEntradaDesdeString("1;5;1");
		cut.aniadirEntradaDesdeString("3;2;1");
		cut.aniadirEntradaDesdeString("2;8;1");
		cut.aniadirEntradaDesdeString("1;7;1");
		cut.aniadirEntradaDesdeString("3;9;1");
		
		cut.aniadirEntradaDesdeString("2;5;2");
		cut.aniadirEntradaDesdeString("1;2;2");
		cut.aniadirEntradaDesdeString("3;8;2");
		cut.aniadirEntradaDesdeString("2;7;2");
		cut.aniadirEntradaDesdeString("1;9;2");
		
		assertEquals( 5, cut.obtenerTamanioEntradaTienda() );
		assertEquals( 12, cut.obtenerUnidadesStockArticuloTienda(1) );
		assertEquals( 8, cut.obtenerUnidadesStockArticuloTienda(2) );
		assertEquals( 11, cut.obtenerUnidadesStockArticuloTienda(3) );
		
		assertEquals( 5, cut.obtenerTamanioEntradaAlmacen() );
		assertEquals( 11, cut.obtenerUnidadesStockArticuloAlmacen(1) );
		assertEquals( 12, cut.obtenerUnidadesStockArticuloAlmacen(2) );
		assertEquals( 8, cut.obtenerUnidadesStockArticuloAlmacen(3) );
	}

	@Test
	public void testAniadirEntradaDesdeArchivo() throws IOException {
		String fichero = ("src/test/resources/inventarioInicialTest.txt");
		cut.aniadirEntradaDesdeArchivo(fichero);
		
		assertEquals( 4, cut.obtenerTamanioEntradaTienda() );
		assertEquals( 5, cut.obtenerUnidadesStockArticuloTienda(1) );
		assertEquals( 60, cut.obtenerUnidadesStockArticuloTienda(2) );
		assertEquals( 8, cut.obtenerUnidadesStockArticuloTienda(3) );
		
		assertEquals( 3, cut.obtenerTamanioEntradaAlmacen() );
		assertEquals( 20, cut.obtenerUnidadesStockArticuloAlmacen(1) );
		assertEquals( 240, cut.obtenerUnidadesStockArticuloAlmacen(2) );
		assertEquals( 10, cut.obtenerUnidadesStockArticuloAlmacen(3) );
	}
}
