package es.cic.curso.curso06.ejercicio014.versionI.repositorios;

import java.util.ArrayList;
import java.util.List;

import es.cic.curso.curso06.ejercicio014.versionI.archivos.Venta;

public class VentasRepositorio {

	private List<Venta> listaVentas = new ArrayList<>();
	
	public int obtenerNumeroVentas() {
		
		return listaVentas.size();
	}
	
	public void apuntarVenta(int idProductoAVender, int unidadesAVender) {
		
		
	}
}
