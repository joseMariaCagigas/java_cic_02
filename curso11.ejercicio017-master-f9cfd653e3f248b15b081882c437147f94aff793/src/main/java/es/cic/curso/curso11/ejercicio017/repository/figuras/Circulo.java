package es.cic.curso.curso11.ejercicio017.repository.figuras;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

public class Circulo extends Figura {
	
	private static final long serialVersionUID = 4867838399840847057L;

	public Circulo(Centro newCenter, float newSize, float newRotation, Color newColour) {
		super(newCenter, newSize, newRotation, newColour);
	}
	
	@Override
	public String toString() {
	
		return "Circulo [ " + super.getCenter() + ", Tamaño = " + super.getSize() + ", Rotación = " + super.getRotation() + ", " + super.getColour() + " ]";
	}
}
