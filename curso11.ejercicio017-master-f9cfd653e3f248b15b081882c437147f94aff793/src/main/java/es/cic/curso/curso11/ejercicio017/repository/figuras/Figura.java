package es.cic.curso.curso11.ejercicio017.repository.figuras;

import java.io.Serializable;

public abstract class Figura implements Serializable {

	private static final long serialVersionUID = 522916337401229337L;
	
	private Centro center;
	private float size;
	private float rotation;
	private Color colour;
	
	public Figura(Centro newCenter, float newSize, float newRotation, Color newColour) {
		
		this.center = newCenter;
		this.size = newSize;
		this.rotation = newRotation;
		this.colour = newColour;
	}
	
	public Centro getCenter() {
		return center;
	}

	public void setCenter(Centro newCenter) {
		this.center = newCenter;
	}

	public double getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + Float.floatToIntBits(rotation);
		result = prime * result + Float.floatToIntBits(size);
		return result;
	}

	@Override
	public boolean equals(Object objetoComparado) {
		
		if (this == objetoComparado)
			return true;
		
		if (objetoComparado == null)
			return false;
		
		if (getClass() != objetoComparado.getClass())
			return false;
		
		Figura figuraComparada = (Figura) objetoComparado;
		
		if (center == null) {
			if (figuraComparada.center != null)
				return false;
		} else if (!center.equals(figuraComparada.center))
			return false;
		
		if (colour == null) {
			if (figuraComparada.colour != null)
				return false;
		} else if (!colour.equals(figuraComparada.colour))
			return false;
		
		if ( Float.compare(rotation, figuraComparada.rotation) != 0 )
			return false;
		
		if ( Float.compare(size, figuraComparada.size) != 0 )
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		
		return "Figura [ " + center + ", Tamaño = " + size + ", Rotación = " + rotation + ", " + colour + " ]";
	}
}
