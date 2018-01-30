package es.cic.curso.curso06.ejercicio014.versionII.servicios;

import java.util.List;

import es.cic.curso.curso06.ejercicio014.versionII.datos.Producto;

public interface VentaService {

	void vendeProd(List<Producto> p, List<Integer> numero);

	List<Producto> leeInventarioProductos()	;

}