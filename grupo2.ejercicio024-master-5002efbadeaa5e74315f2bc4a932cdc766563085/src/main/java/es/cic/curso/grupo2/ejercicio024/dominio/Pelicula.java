package es.cic.curso.grupo2.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import es.cic.curso.grupo2.ejercicio024.repositorio.Identificable;

@Entity
@Table(name="pelicula")
public class Pelicula implements Identificable<Long>
{
	private static final long serialVersionUID = 4940930570139281149L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; //ID Autogenerado al Insertar en DDBB
	
	//Attributes
	@Column(name="titulo")
	String titulo;
	
	//Constructor Vacio
	public Pelicula(){
		super();
	}
	
	//Constructor
	public Pelicula(String titulo){
		this.titulo=titulo;
	}

	//Getters y Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + "]";
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
		Pelicula other = (Pelicula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
