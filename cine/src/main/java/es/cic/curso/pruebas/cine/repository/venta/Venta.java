package es.cic.curso.pruebas.cine.repository.venta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.cic.curso.pruebas.cine.Identificable;
import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;

@Entity
public class Venta  implements Identificable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5390262595191433122L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="sala_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Sala sala;
	
	@JoinColumn(name="sesion_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Sesion sesion;
	
	@Column(name="numEntradas")
	private int numEntradas;
	
	@Column(name="importe")
	private double importe;

	public Venta() {
		super();
	}
	
	public Venta(Sala sala, Sesion sesion, int numEntradas, double importe) {
		super();
		this.sala = sala;
		this.sesion = sesion;
		this.numEntradas = numEntradas;
		this.importe = importe;
	}



	public Venta(Long id, Sala sala, Sesion sesion, int numEntradas, double importe) {
		super();
		this.id = id;
		this.sala = sala;
		this.sesion = sesion;
		this.numEntradas = numEntradas;
		this.importe = importe;
	}
	
	@Override
	public Long getId() {
		
		return id;
	}

	@Override
	public void setId(Long id) {
		
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public int getNumEntradas() {
		return numEntradas;
	}

	public void setNumEntradas(int numEntradas) {
		this.numEntradas = numEntradas;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
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
		return "Venta [id=" + id + ", sala=" + sala.getId() + ", sesion=" + sesion.getId() + ", numEntradas=" + numEntradas
				+ ", importe=" + importe + "]";
	}
	
	
}
