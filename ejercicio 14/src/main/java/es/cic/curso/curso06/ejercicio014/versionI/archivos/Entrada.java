package es.cic.curso.curso06.ejercicio014.versionI.archivos;

public class Entrada {

	private int idProducto;
	private int stock;
	
	public Entrada(int idProductoInventariado, int stockDisponible) {
		
		this.idProducto = idProductoInventariado;
		this.stock = stockDisponible;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
