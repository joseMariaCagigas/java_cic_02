package es.cic.curso.curso07.ejercicio013.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sala implements Identificable<Long> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="numero_sala")
	private int numeroDeSala;
	
	private int capacidad;
	
	@Column(name="ocupadas_sesion_1")
	private int ocupadasSesion1;
	@Column(name="ocupadas_sesion_2")
	private int ocupadasSesion2;
	@Column(name="ocupadas_sesion_3")
	private int ocupadasSesion3;


	public Sala() {
		super();
	}
	
	

	public Sala(int numeroDeSala, int capacidad, int ocupadasSesion1, int ocupadasSesion2, int ocupadasSesion3) {
		super();
		this.numeroDeSala = numeroDeSala;
		this.capacidad = capacidad;
		this.ocupadasSesion1 = ocupadasSesion1;
		this.ocupadasSesion2 = ocupadasSesion2;
		this.ocupadasSesion3 = ocupadasSesion3;
	}



	public Sala(Long id, int numeroDeSala, int capacidad, int ocupadasSesion1, int ocupadasSesion2,
			int ocupadasSesion3) {
		super();
		this.id = id;
		this.numeroDeSala = numeroDeSala;
		this.capacidad = capacidad;
		this.ocupadasSesion1 = ocupadasSesion1;
		this.ocupadasSesion2 = ocupadasSesion2;
		this.ocupadasSesion3 = ocupadasSesion3;
	}



	/* (non-Javadoc)
	 * @see es.cic.curso.curso07.ejercicio013.dominio.Identificable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see es.cic.curso.curso07.ejercicio013.dominio.Identificable#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	


	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getOcupadasSesion1() {
		return ocupadasSesion1;
	}

	public void setOcupadasSesion1(int ocupadasSesion1) {
		this.ocupadasSesion1 = ocupadasSesion1;
	}

	public int getOcupadasSesion2() {
		return ocupadasSesion2;
	}

	public void setOcupadasSesion2(int ocupadasSesion2) {
		this.ocupadasSesion2 = ocupadasSesion2;
	}

	public int getOcupadasSesion3() {
		return ocupadasSesion3;
	}

	public void setOcupadasSesion3(int ocupadasSesion3) {
		this.ocupadasSesion3 = ocupadasSesion3;
	}

	

	public int getNumeroDeSala() {
		return numeroDeSala;
	}

	public void setNumeroDeSala(int numeroDeSala) {
		this.numeroDeSala = numeroDeSala;
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
		Sala other = (Sala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", capacidad=" + capacidad + ", ocupadasSesion1=" + ocupadasSesion1
				+ ", ocupadasSesion2=" + ocupadasSesion2 + ", ocupadasSesion3=" + ocupadasSesion3 + "]";
	}	
}
