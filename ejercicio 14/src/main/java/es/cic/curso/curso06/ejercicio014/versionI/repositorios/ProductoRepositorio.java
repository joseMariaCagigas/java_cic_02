package es.cic.curso.curso06.ejercicio014.versionI.repositorios;

import java.util.ArrayList;
import java.util.List;

import es.cic.curso.curso06.ejercicio014.versionI.archivos.Producto;

public class ProductoRepositorio {

	private List<Producto> listaProductos = new ArrayList<>();
	
	public ProductoRepositorio() {
		// Inicio dummy de la clase para tener datos precargados
		listaProductos.add(new Producto(1, "Cafe", 4));
		listaProductos.add(new Producto(2, "Leche", 1));
		listaProductos.add(new Producto(3, "Caja de Galletas", 2));
	}

	/**
	 * Metodo que devuelve la lista completa de Productos
	 * @return List<Producto> listaProductos
	 */
	public List<Producto> getListaProductos() {
		
		return listaProductos;
	}
	
	public Producto getProductoPorIndice(int idProducto) {
		
		Producto productoADevolver = null;
		
		for (Producto productoActual:listaProductos) {
			if (idProducto == productoActual.getId()) {
				productoADevolver = productoActual;
			}
		}
		
		return productoADevolver;
	}
	
	public Producto getProductoPorNombre(String nombreProducto) {
		
		Producto productoADevolver = null;
		
		for (Producto productoActual:listaProductos) {
			if ( nombreProducto.toLowerCase().equalsIgnoreCase(productoActual.getNombre().toLowerCase()) ) {
				productoADevolver = productoActual;
			}
		}
		
		return productoADevolver;
	}
	
	public void aniadirProducto(Producto nuevoProducto) {
		
		listaProductos.add(nuevoProducto);
	}
	
}
