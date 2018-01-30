package es.cic.curso.curso11.ejercicio017.repository.figuras;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class Centro implements Serializable {

	private static final long serialVersionUID = 7377311612795249317L;
	
	private float centerX;
	private float centerY;
	
	public Centro() {
		
 		super();
 	}
	
	public Centro(float newCenterX, float newCenterY) {
		
		this.centerX = newCenterX;
		this.centerY = newCenterY;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(float centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(float centerY) {
		this.centerY = centerY;
	}
	
	@Override
	public int hashCode() {
		
		final int prime = 24;
		int resultado = 1;
		
		resultado = prime * resultado + Float.hashCode(centerX);
		resultado = prime * resultado + Float.hashCode(centerY);
		
		return resultado;
	}

	@Override
	public boolean equals(Object objetoComparado) {
		
		if (this == objetoComparado)
			
			return true;
		
		if (objetoComparado == null)
			
			return false;
		
		if (getClass() != objetoComparado.getClass())
			
			return false;
		
		Centro centroComparado = (Centro) objetoComparado;
		
		if ( Float.compare(centerX, centroComparado.centerX) != 0 )
			
			return false;
		
		if ( Float.compare(centerY, centroComparado.centerY) != 0 )
			
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Centro [ X = " + centerX + ", Y = " + centerY + " ]";
	}
	
}
