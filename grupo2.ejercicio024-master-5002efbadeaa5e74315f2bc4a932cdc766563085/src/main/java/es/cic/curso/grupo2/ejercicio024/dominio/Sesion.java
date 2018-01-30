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
@Table(name="sesion")
public class Sesion implements Identificable<Long>
{
	private static final long serialVersionUID = 7744168709842099960L;
	public static final double PRECIO = 5.0;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; //ID Autogenerado al Insertar en DDBB
	
	//Attributes
	@JoinColumn(name="peliculaSesion")
	@ManyToOne(fetch=FetchType.EAGER)
	private Pelicula peliculaSesion;
    
	@JoinColumn(name="salaSesion")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sala salaSesion;
    
	@Column(name="hora")
	private double hora;
	@Column(name="asientosDisponibles")
	private int asientosDisponibles;
	@Column(name="abierta")
	private boolean abierta;

	
	//Constructor Vacio
	public Sesion(){
		super();
	}
	
	//Constructor
	public Sesion(Pelicula pelicula, Sala sala, double hora){
		this.peliculaSesion=pelicula;
		this.salaSesion=sala;
		this.hora=hora;
		this.asientosDisponibles=sala.getAsientosTotales();
		this.abierta=true;
		
	}
	
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pelicula getPelicula() {
		return peliculaSesion;
	}
	public void setPelicula(Pelicula pelicula) {
		this.peliculaSesion = pelicula;
	}

	public Sala getSala() {
		return salaSesion;
	}
	public void setSala(Sala sala) {
		this.salaSesion = sala;
	}

	public double getHora() {
		return hora;
	}
	public void setHora(double hora) {
		this.hora = hora;
	}
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}
	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
	public boolean isAbierta() {
		return abierta;
	}
	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

	@Override
	public String toString() {
		return "Sesion [id=" + id + ", peliculaSesion=" + peliculaSesion.getId() + ", salaSesion=" + salaSesion.getId() + ", hora="
				+ hora + ", asientosDisponibles=" + asientosDisponibles + ", abierta=" + abierta + "]";
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
		Sesion other = (Sesion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//Metodo - restarAsientos
	public int restarAsientos(int vender)
	{
		asientosDisponibles = asientosDisponibles-vender;
		return asientosDisponibles;
	}
	
	//Metodo - cerrarSesion
	public void cerrarSesion(){
		setAbierta(false);
	}
	
	//Metodo - reabrirSesion
	public void reabrirSesion(){
		setAbierta(true);
	}
	
	public String getTitulo(){
		return peliculaSesion.getTitulo();
		//return "Titulo";
	}
}
