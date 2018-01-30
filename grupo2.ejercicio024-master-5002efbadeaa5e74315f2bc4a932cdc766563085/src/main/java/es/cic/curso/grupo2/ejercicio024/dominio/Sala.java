package es.cic.curso.grupo2.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import es.cic.curso.grupo2.ejercicio024.repositorio.Identificable;

@Entity
@Table(name="sala")
public class Sala implements Identificable<Long>
{
	private static final long serialVersionUID = 5379404802664082207L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; //ID Autogenerado al Insertar en DDBB
	
	//Attributes
	@Column(name="numeroSala")
	private int numeroSala;
	@Column(name="asientosTotales")
	private int asientosTotales;
	
	//Constructor Vacio
	public Sala(){
		super();
	}
	
	//Constructor
	public Sala(int numeroSala, int asientosTotales){
		this.numeroSala=numeroSala;
		this.asientosTotales=asientosTotales;
	}
	
	public Sala(int asientosTotales){
		this.numeroSala=numeroSala;
		this.asientosTotales=asientosTotales;
	}

	//Getters y Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAsientosTotales() {
		return asientosTotales;
	}
	public void setAsientosTotales(int asientosTotales) {
		this.asientosTotales = asientosTotales;
	}
	public int getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", numeroSala=" + numeroSala + ", asientosTotales=" + asientosTotales + "]";
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
}
