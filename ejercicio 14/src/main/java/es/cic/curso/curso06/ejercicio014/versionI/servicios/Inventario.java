package es.cic.curso.curso06.ejercicio014.versionI.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.cic.curso.curso06.ejercicio014.versionI.archivos.Entrada;
import es.cic.curso.curso06.ejercicio014.versionI.datos.ArchivoOperacion;

public class Inventario {

	private List<Entrada> EntradaTienda = new ArrayList<>();
	private List<Entrada> EntradaAlmacen = new ArrayList<>();
	
	public int obtenerTamanioEntradaTienda() {
		
		return EntradaTienda.size();
	}
	
	public int obtenerTamanioEntradaAlmacen() {
		
		return EntradaAlmacen.size();
	}
	
	public void aniadirEntradaTienda(int idProducto, int stock) {
		
		EntradaTienda.add( new Entrada(idProducto, stock) );
	}
	
	public void aniadirEntradaAlmacen(int idProducto, int stock) {
		
		EntradaAlmacen.add( new Entrada(idProducto, stock) );
	}
	
	public void aniadirEntradaDesdeString(String lineaDeEntrada) {
		
		String[] arrayDatos = lineaDeEntrada.split(";");
		
		if ( Integer.parseInt(arrayDatos[2]) == 1 ) {
			
			aniadirEntradaTienda(Integer.parseInt(arrayDatos[0]), Integer.parseInt(arrayDatos[1]));
			
		} else if ( Integer.parseInt(arrayDatos[2]) == 2 ) {

			aniadirEntradaAlmacen(Integer.parseInt(arrayDatos[0]), Integer.parseInt(arrayDatos[1]));
		}
	}
	
	public void aniadirEntradaDesdeArchivo(String rutaEntrada) throws IOException {
		
		List<String> rawDataArchivoEntrada = ArchivoOperacion.leerArchivoPorLineas(rutaEntrada);
		
		for ( String lineaDeEntrada:rawDataArchivoEntrada ) {
			
			aniadirEntradaDesdeString(lineaDeEntrada);
		}
	}
	
	public int obtenerUnidadesStockArticuloTienda(int idArticulo) {
		
		int stockTotal = 0;
		
		for (Entrada entradaActual:EntradaTienda) {
			
			if ( entradaActual.getIdProducto() == idArticulo ) {
				
				stockTotal += entradaActual.getStock();
			}
		}
		
		return stockTotal;
	}
	
	public int obtenerUnidadesStockArticuloAlmacen(int idArticulo) {
		
		int stockTotal = 0;
		
		for (Entrada entradaActual:EntradaAlmacen) {
			
			if ( entradaActual.getIdProducto() == idArticulo ) {
				
				stockTotal += entradaActual.getStock();
			}
		}
		
		return stockTotal;
	}
	
	public int obtenerUnidadesStockArticuloTotal(int idArticulo) {
		
		int stockTotal = 0;
		
		stockTotal += obtenerUnidadesStockArticuloTienda(idArticulo);
		stockTotal += obtenerUnidadesStockArticuloAlmacen(idArticulo);
		
		return stockTotal;
	}
}
