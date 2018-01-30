package es.cic.curso.curso06.ejercicio014.versionII.datos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.cic.curso.curso06.ejercicio014.versionII.repositorios.Identificable;

@Entity
@Table(name="VENTA")
public class Venta implements Identificable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2124412949726530847L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	@Column(name="id_producto")
	private Long id_producto;
	@Column(name="cantidad")
	private int cantidad;
	@Column(name="importe")
	private double importe;
	
	
	public Venta() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Venta(Long id, Long id_producto, int cantidad, double importe) {
		super();
		Id = id;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.importe = importe;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public Long getId_producto() {
		return id_producto;
	}


	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}
	

	
	
	
}
