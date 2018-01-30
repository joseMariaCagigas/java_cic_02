package es.cic.curso.curso07.ejercicio013.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Venta implements Identificable<Long> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="cuantas_entradas")
	private short cuantasEntradas;
	private boolean descuento;
	private Long salaId;
	private Long sesionId;
	private double precio;
	
	
	
	
	public Venta() {
		super();
	}

	
	
	public Venta(short cuantasEntradas, boolean descuento, Long salaId, Long sesionId, double precio) {
		super();
		this.cuantasEntradas = cuantasEntradas;
		this.descuento = descuento;
		this.salaId = salaId;
		this.sesionId = sesionId;
		this.precio = precio;
	}



	public Venta(Long id, short cuantasEntradas, boolean descuento, Long salaId, Long sesionId, double precio) {
		this(cuantasEntradas, descuento, salaId, sesionId, precio);
		this.id = id;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public short getCuantasEntradas() {
		return cuantasEntradas;
	}
	public void setCuantasEntradas(short cuantasEntradas) {
		this.cuantasEntradas = cuantasEntradas;
	}
	public boolean isDescuento() {
		return descuento;
	}
	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}
	public Long getSalaId() {
		return salaId;
	}
	public void setSalaId(Long salaId) {
		this.salaId = salaId;
	}
	public Long getSesionId() {
		return sesionId;
	}
	public void setSesionId(Long sesionId) {
		this.sesionId = sesionId;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Venta other = (Venta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", cuantasEntradas=" + cuantasEntradas + ", descuento=" + descuento + ", salaId="
				+ salaId + ", sesionId=" + sesionId + ", precio=" + precio + "]";
	}
}
