package es.cic.curso.grupo2.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import es.cic.curso.grupo2.ejercicio024.repositorio.Identificable;

@Entity
@Table(name="venta")
public class Venta implements Identificable<Long>
{
	private static final long serialVersionUID = 9149945219527320807L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; //ID Autogenerado al Insertar en DDBB
	
	//Attributes
	@Column(name="numeroEntradas")
	private int numeroEntradas;
	
	@JoinColumn(name="sesionEntrada")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sesion sesionEntrada;
    
	@Column(name="importe")
	private double importe;
	
	//Constructor Vacio
	public Venta(){
		super();
	}
	
	//Constructor
	public Venta(int numeroEntradas, Sesion sesionEntrada, double precioEntrada){
		this.numeroEntradas=numeroEntradas;
		this.sesionEntrada=sesionEntrada;
		this.importe=precioEntrada;
	}

	//Getters y Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumeroEntradas() {
		return numeroEntradas;
	}
	public void setNumeroEntradas(int numeroEntradas) {
		this.numeroEntradas = numeroEntradas;
	}
	public Sesion getSesionEntrada() {
		return sesionEntrada;
	}
	public void setSesionEntrada(Sesion sesionEntrada) {
		this.sesionEntrada = sesionEntrada;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double precioEntrada) {
		this.importe = precioEntrada;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", numeroEntrada=" + numeroEntradas + ", sesionEntrada=" + sesionEntrada
				+ ", precioEntrada=" + importe + "]";
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
	/*******************************************************************************************/
	public String getTitulo(){
		return sesionEntrada.getTitulo();
		//return "Titulo";
	}
	public Long getIdSesion(){
		return sesionEntrada.getId();
		//return "Titulo";
	}
	/*******************************************************************************************/
}