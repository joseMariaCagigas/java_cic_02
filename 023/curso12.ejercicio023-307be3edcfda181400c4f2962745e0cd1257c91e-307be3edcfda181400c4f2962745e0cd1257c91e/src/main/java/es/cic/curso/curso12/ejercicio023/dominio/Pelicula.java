package es.cic.curso.curso12.ejercicio023.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.vaadin.ui.TextField;

@Entity
@Table(name="PELICULA")
public class Pelicula implements Identificable<Long>{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Size(min=1)
	@Column(name="titulo")
	private String titulo;
	@Column(name="director")
	private String director;
	@Column(name="productora")
	private String productora;
	@Column(name="duracion")
	private int duracion;
	@Column(name="year")
	@Min(value=1900, message="Valores mayores o iguales que 1900")
	@Max(value=2100, message="Valores menores o iguales que 2100")
	private int year;
	@Column(name="interprete")
	private String interprete;
	@Column(name="genero")
	private String genero;
	
	public Pelicula(){
		super();
	}
	
	public Pelicula(String titulo, String director, String productora, String interprete, int year, int duracion,String genero){
		super();
		this.titulo=titulo;
		this.director=director;
		this.productora=productora;
		this.interprete=interprete;
		this.year=year;
		this.duracion=duracion;
		this.genero = genero;
	}
	
	

	public String getTitulo() {
		return titulo;
	}

	public String getDirector() {
		return director;
	}

	public String getProductora() {
		return productora;
	}

	public int getDuracion() {
		return duracion;
	}

	public int getYear() {
		return year;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pelicula other = (Pelicula) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
