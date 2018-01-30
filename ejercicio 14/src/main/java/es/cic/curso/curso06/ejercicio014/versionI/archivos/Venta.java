package es.cic.curso.curso06.ejercicio014.versionI.archivos;

public class Venta {
	
	private int idProducto;
	private int unidadesVendidas;
	
	public Venta ( int idProductoVendido, int unidadesAVender ) {
		
		this.idProducto = idProductoVendido;
		
		this.unidadesVendidas = unidadesAVender;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getUnidadesVendidas() {
		return unidadesVendidas;
	}

	public void setUnidadesVendidas(int unidadesVendidas) {
		this.unidadesVendidas = unidadesVendidas;
	}
	
	
}
