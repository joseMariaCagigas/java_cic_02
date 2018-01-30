package es.cic.curso.grupo3.ejercicio024.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.curso.grupo3.ejercicio024.repository.Identificable;

@Entity
@Table(name="sala")
public class Sala implements Identificable<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013722442887352179L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="asientosLibres")
	private int asientosLibres;
	
	@Column(name="numSesion")
	private int numSesion;
	
	@Column(name="numSala")
	private int numSala;
	
	@Column(name="cerrado")
	private boolean cerrado;
	
	@OneToMany(mappedBy="salaId") 
	private List<Venta> listaVentas = new ArrayList<>();
	
	public Sala() {
		super();
	}

	public Sala(int numSala, int numSesion, boolean cerrado) {
		
		asientosLibres(numSala);
		
		this.numSesion = numSesion;
		this.numSala = numSala;
		this.cerrado = cerrado;
	}

	private void asientosLibres(int numSala) {
		switch (numSala){
		case 1: {
			this.asientosLibres = 100;
			break;
		}
		case 2: {
			this.asientosLibres = 50;
			break;
		}
		case 3: {
			this.asientosLibres = 30;
			break;
		}
		default :
			break;
		}
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public int getAsientosLibres() {
		return asientosLibres;
	}

	public void setAsientosLibres(int asientosLibres) {
		this.asientosLibres = asientosLibres;
	}

	public int getNumSesion() {
		return numSesion;
	}

	public void setNumSesion(int numSesion) {
		this.numSesion = numSesion;
	}

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}
}