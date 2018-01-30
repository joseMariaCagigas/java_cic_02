package es.cic.curso.curso11.ejercicio017.repository.figuras;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

public class Cuadrado extends Figura{
	
	private static final long serialVersionUID = -7770443371744630990L;

	public Cuadrado(Centro newCenter, float newSize, float newRotation, Color newColour) {
		super(newCenter, newSize, newRotation, newColour);
	}

	@Override
	public String toString() {
		
		return "Cuadrado [ " + super.getCenter() + ", Tamaño = " + super.getSize() + ", Rotación = " + super.getRotation() + ", " + super.getColour() + " ]";
	}
}
