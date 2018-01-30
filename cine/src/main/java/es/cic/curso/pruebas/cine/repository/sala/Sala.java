package es.cic.curso.pruebas.cine.repository.sala;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.cic.curso.pruebas.cine.Identificable;
import es.cic.curso.pruebas.cine.repository.venta.Venta;

@Entity
public class Sala implements Identificable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2166025637725882880L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="aforo")
	private int aforo;
	
	@OneToMany(mappedBy = "sala")
    private List<Venta> ventas= new ArrayList<>();
	
	
	public Sala() {
		super();
	}

	public Sala(int aforo) {
		super();
		this.aforo = aforo;
	}

	public Sala(Long id, int aforo) {
		super();
		this.id = id;
		this.aforo = aforo;
	}
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
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
		return "Sala [id=" + id + ", aforo=" + aforo + "]";
	}
	
}
