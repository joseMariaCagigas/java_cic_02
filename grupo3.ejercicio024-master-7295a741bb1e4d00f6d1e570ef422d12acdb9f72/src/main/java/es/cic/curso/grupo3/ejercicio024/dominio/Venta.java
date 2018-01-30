package es.cic.curso.grupo3.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cic.curso.grupo3.ejercicio024.repository.Identificable;

@Entity
@Table(name="venta")
public class Venta implements Identificable<Long> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="salaId")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sala salaId;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="beneficio")
	private double beneficio;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getNumSala(){
		return this.salaId.getNumSala();
	}
	
	public int getNumSesion(){
		return this.salaId.getNumSesion();
	}

	public Sala getSalaId() {
		return salaId;
	}

	public void setSalaId(Sala salaId) {
		this.salaId = salaId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}
}