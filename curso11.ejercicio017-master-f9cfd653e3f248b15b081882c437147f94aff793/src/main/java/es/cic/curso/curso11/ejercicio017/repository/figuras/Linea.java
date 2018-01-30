package es.cic.curso.curso11.ejercicio017.repository.figuras;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

public class Linea extends Figura {
	
	private static final long serialVersionUID = -6840213148654449352L;

	public Linea(Centro newCenter, float newSize, float newRotation, Color newColour) {
		super(newCenter, newSize, newRotation, newColour);
	}

	@Override
	public String toString() {
		
		return "Linea [ " + super.getCenter() + ", Tamaño = " + super.getSize() + ", Rotación = " + super.getRotation() + ", " + super.getColour() + " ]";
	}
}
