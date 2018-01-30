package es.cic.curso.pruebas.cine.repository.sesion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.cic.curso.pruebas.cine.Identificable;
import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.venta.Venta;

@Entity
@Table(name="sesion")
public class Sesion implements Identificable<Long>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8222580053988059536L;
	
	public static final double PRECIO = 5.5; 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="asientosOcupados")
	private int asientosOcupados;
	
	@Column(name="estaCerrada")
	private boolean estaCerrada = false;
	
	@JoinColumn(name="sala_id")
	@OneToOne(fetch=FetchType.LAZY)
	private Sala sala;
	
	@OneToMany(mappedBy = "sesion")
    private List<Venta> ventas= new ArrayList<>();
	
	public Sesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Sesion(Long id, int asientosOcupados, boolean estaCerrada, Sala sala) {
		super();
		this.id = id;
		this.asientosOcupados = asientosOcupados;
		this.estaCerrada = estaCerrada;
		this.sala = sala;
	}

	

	public Sesion(int asientosOcupados, boolean estaCerrada, Sala sala) {
		super();
		this.asientosOcupados = asientosOcupados;
		this.estaCerrada = estaCerrada;
		this.sala = sala;
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
	public Long getId() {
		
		return id;
	}

	@Override
	public void setId(Long id) {
		
		this.id = id;
	}


	@Override
	public String toString() {
		return "Sesion [id=" + id + ", asientosOcupados=" + asientosOcupados + ", estaCerrada=" + estaCerrada
				+ ", salaId =" + sala.getId() + "]";
	}


	public int getAsientosOcupados() {
		return asientosOcupados;
	}


	public void setAsientosOcupados(int asientosOcupados) {
		this.asientosOcupados = asientosOcupados;
	}


	public boolean isEstaCerrada() {
		return estaCerrada;
	}


	public void setEstaCerrada(boolean estaCerrada) {
		this.estaCerrada = estaCerrada;
	}


	public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public List<Venta> getVentas() {
		return ventas;
	}


	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
}
