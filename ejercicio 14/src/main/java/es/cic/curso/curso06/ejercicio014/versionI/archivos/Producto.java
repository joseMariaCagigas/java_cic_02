package es.cic.curso.curso06.ejercicio014.versionI.archivos;

public class Producto {

	// ATRIBUTOS
	private int id;
	private String nombre;
	private int precio;
	
	
	// CONSTRUCTOR
	/**
	 * Constructor para el producto
	 * @param newId
	 * @param newNombre
	 * @param newPrecio
	 */
	public Producto(int newId, String newNombre, int newPrecio) {
		this.id = newId;
		this.nombre = newNombre;
		this.precio = newPrecio;
	}


	// GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	
}
