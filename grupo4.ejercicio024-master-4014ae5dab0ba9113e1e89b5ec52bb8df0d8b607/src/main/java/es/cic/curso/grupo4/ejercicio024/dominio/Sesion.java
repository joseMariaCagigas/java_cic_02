package es.cic.curso.grupo4.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.cic.curso.grupo4.ejercicio024.repositorio.Identificable;

@Entity
@Table(name="sesion")
public class Sesion implements Identificable<Long> {

	private static final long serialVersionUID = -3286463158092215317L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name ="nombreSala")
	private String nombreSala;
	
	

	@Column(name="numeSesion")
	private int numeSesion;
	
	@Column(name = "butacasDisp")
	private int butacasDisp;
	
	@JoinColumn(name="salaId")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sala sala;

	@Transient
	public boolean abierto;

	public Sesion() {
		super();
	}
	 
 

	public Sesion(String nombreSala, int numeSesion, int butacasDisp, Sala sala, boolean abierto) {
		super();
		this.nombreSala = nombreSala;
		this.numeSesion = numeSesion;
		this.butacasDisp = butacasDisp;
		this.sala = sala;
		this.abierto = abierto;
	}



	public Sesion(int numeSesion, int butacasDisp, Sala sala, boolean abierto) {
		super();
		this.numeSesion = numeSesion;
		this.butacasDisp = butacasDisp;
		this.sala = sala;
		this.abierto = abierto;
	}

	public Sesion(int numeSesion,int asientosDisponibles ) {
		this.numeSesion = numeSesion;
		this.butacasDisp = asientosDisponibles;
	}
	
	public Sesion(int butacasDisp) {
		super();
		this.butacasDisp = butacasDisp;
	}
	
	public Sesion(int numeSesion, int butacasDisp,boolean abierto) {
		super();
		this.numeSesion = numeSesion;	 
		this.butacasDisp = butacasDisp;
		this.abierto = abierto;
	}
	
	public Sesion(Long id, int numeSesion, int butacasDisp) {
		super();
		this.id = id;
		this.numeSesion = numeSesion;	 
		this.butacasDisp = butacasDisp;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getButacasDisp() {
		return butacasDisp;
	}

	public void setButacasDisp(int butacasDisp) {
		this.butacasDisp = butacasDisp;
	}

	public long getNumeSesion() {
		return numeSesion;
	}

	public void setNumeSesion(int numeSesion) {
		this.numeSesion = numeSesion;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	
	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
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
	
	@Override
	public String toString() {
		return "Sesion [id=" + id + ", numeSesion=" + numeSesion + ", butacasDisp=" + butacasDisp + "]";
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
 	
	public int getNumSala (){
		
		return sala.getNumSala();
		
		
	}
}