package es.cic.curso.grupo4.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.cic.curso.grupo4.ejercicio024.repositorio.Identificable;

@Entity
@Table(name="venta")
public class Venta implements Identificable<Long> {

	private static final long serialVersionUID = 2628266210895076542L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="cuantas_entradas")
	private int cuantasEntradas;
	
	@JoinColumn(name = "salaId")
	@OneToOne(fetch=FetchType.EAGER)
	private Sala sala;
	
	@JoinColumn(name = "sesionId")
	@OneToOne(fetch=FetchType.EAGER)
	private Sesion sesion;
	
	@Column
	private double precio;
	
	@Column
	private boolean descuento;
	
	@Column
	private boolean esVenta;
	
	@Column
	private boolean esDevolucion;
	
	
	@Column
	private boolean devuelta;
	
	
	@Column
	private double beneficios;
	
	public Venta() {
		super();
	}
	
	public Venta(int cuantasEntradas,Sala sala, Sesion sesion,double precio,boolean descuento,boolean esDevolucion) {
		super();
		this.cuantasEntradas = cuantasEntradas;
		this.sala = sala;
		this.sesion = sesion;
		this.precio = precio;
		this.descuento = descuento;
		this.esDevolucion = esDevolucion;
	}
	
	

	public Venta(int cuantasEntradas, Sala sala, Sesion sesion, double precio, boolean descuento, boolean esDevolucion,
			double beneficios) {
		super();
		this.cuantasEntradas = cuantasEntradas;
		this.sala = sala;
		this.sesion = sesion;
		this.precio = precio;
		this.descuento = descuento;
		this.esDevolucion = esDevolucion;
		this.beneficios = beneficios;
	}

	public Venta(int cuantasEntradas, Sala sala, Sesion sesion, double precio, boolean descuento, boolean esVenta,
			boolean esDevolucion, boolean devuelta) {
		super();
		this.cuantasEntradas = cuantasEntradas;
		this.sala = sala;
		this.sesion = sesion;
		this.precio = precio;
		this.descuento = descuento;
		this.esVenta = esVenta;
		this.esDevolucion = esDevolucion;
		this.devuelta = devuelta;
		 
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public int getCuantasEntradas() {
		return cuantasEntradas;
	}

	public void setCuantasEntradas(int cuantasEntradas) {
		this.cuantasEntradas = cuantasEntradas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sesion getSesiones() {
		return sesion;
	}

	public void setSesiones(Sesion sesion) {
		this.sesion = sesion;
	}
	
	public boolean isDescuento() {
		return descuento;
	}

	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}
	
 
	

	public double getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(double beneficios) {
		this.beneficios = beneficios;
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

	public boolean isEsVenta() {
		return esVenta;
	}

	public void setEsVenta(boolean esVenta) {
		this.esVenta = esVenta;
	}

	public boolean isEsDevolucion() {
		return esDevolucion;
	}

	public void setEsDevolucion(boolean esDevolucion) {
		this.esDevolucion = esDevolucion;
	}

	public boolean isDevuelta() {
		return devuelta;
	}

	public void setDevuelta(boolean devuelta) {
		this.devuelta = devuelta;
	}

}