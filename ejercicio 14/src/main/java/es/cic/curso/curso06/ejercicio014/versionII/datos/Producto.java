package es.cic.curso.curso06.ejercicio014.versionII.datos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.cic.curso.curso06.ejercicio014.versionII.repositorios.Identificable;

@Entity
@Table(name="PRODUCTO")
public class Producto implements Identificable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730814569048493776L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="CODE")
	private String code;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="PRECIO")
	private double precio;
	@Column(name="CANTIDAD_STOCK")
	private int cantidadStock;
	@Column(name="CANTIDAD_TIENDA")
	private int cantidadTienda;
	@Column(name="CANTIDAD_ALMACEN")
	private int cantidadAlmacen;
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Producto(Long id, String code, String nombre, double precio, int cantidadStock, int cantidadTienda,
			int cantidadAlmacen) {
		super();
		this.id = id;
		this.code = code;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidadStock = cantidadTienda + cantidadAlmacen;
		this.cantidadTienda = cantidadTienda;
		this.cantidadAlmacen = cantidadAlmacen;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getCantidadStock() {
		return cantidadStock;
	}


	public void setCantidadStock(int cantidadStock) {
		this.cantidadStock = cantidadStock;
	}


	public int getCantidadTienda() {
		return cantidadTienda;
	}


	public void setCantidadTienda(int cantidadTienda) {
		this.cantidadTienda = cantidadTienda;
	}


	public int getCantidadAlmacen() {
		return cantidadAlmacen;
	}


	public void setCantidadAlmacen(int cantidadAlmacen) {
		this.cantidadAlmacen = cantidadAlmacen;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidadAlmacen;
		result = prime * result + cantidadStock;
		result = prime * result + cantidadTienda;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (cantidadAlmacen != other.cantidadAlmacen)
			return false;
		if (cantidadStock != other.cantidadStock)
			return false;
		if (cantidadTienda != other.cantidadTienda)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", code=" + code + ", nombre=" + nombre + ", precio=" + precio
				+ ", cantidadStock=" + cantidadStock + ", cantidadTienda=" + cantidadTienda + ", cantidadAlmacen="
				+ cantidadAlmacen + "]";
	}
	
}