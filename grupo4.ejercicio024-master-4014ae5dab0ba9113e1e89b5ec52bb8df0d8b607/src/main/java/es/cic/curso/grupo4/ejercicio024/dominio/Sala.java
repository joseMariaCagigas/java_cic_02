package es.cic.curso.grupo4.ejercicio024.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.curso.grupo4.ejercicio024.repositorio.Identificable;


@Entity
@Table(name="sala")
public class Sala implements Identificable<Long> {

	private static final long serialVersionUID = -7443571169142368655L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="numSala")
	private int numSala;
	
	@Column (name = "butacasTotales")
	private int butacasTotales;
	
	@Column(name="recaudacion")
	private double recaudacion=0; 
	
	@OneToMany (mappedBy="sala")
	private List<Sesion> sesiones;
		
	public Sala(){	
		super();
//		sesiones = new ArrayList<>(); 		
	}
	
 	public Sala(int numSala, int butacasTotales, double recaudacion) {
		this.numSala = numSala;
		this.butacasTotales = butacasTotales;
		this.recaudacion = recaudacion;
	}	

 	@Override
	public Long getId() {
		return id;
	}

 	@Override
	public void setId(Long id) {
		this.id = id;
	}
 	
	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public void setButacasTotales(int butacasTotales) {
		this.butacasTotales = butacasTotales;
	}
	
	public int getButacasTotales() {
		return butacasTotales;
	}

	public double getRecaudacion() {
		return recaudacion;
	}
	
	public void setRecaudacion(double recaudacion) {
		this.recaudacion =  recaudacion;
	}

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
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
		return "Sala [id=" + id + ", numSala=" + numSala + ", butacasTotales=" + butacasTotales + ", recaudacion="
				+ recaudacion + ", sesiones=" + sesiones + "]";
	}
 
}