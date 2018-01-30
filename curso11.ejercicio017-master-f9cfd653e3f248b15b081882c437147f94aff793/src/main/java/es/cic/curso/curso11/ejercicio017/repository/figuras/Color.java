package es.cic.curso.curso11.ejercicio017.repository.figuras;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class Color implements Serializable {

	private static final long serialVersionUID = 541448666749802020L;
	private int redColour;
	private int greenColour;
	private int blueColour;
	
	public Color() {
		
		super();
	}
	
	public Color( int newRedValue, int newGreenValue, int newBlueValue ) {
		
		this.redColour = newRedValue;
		this.greenColour = newGreenValue;
		this.blueColour = newBlueValue;
	}

	public int getRedColour() {
		return redColour;
	}

	public void setRedColour(int redColor) {
		this.redColour = redColor;
	}

	public int getGreenColour() {
		return greenColour;
	}

	public void setGreenColour(int greenColor) {
		this.greenColour = greenColor;
	}

	public int getBlueColour() {
		return blueColour;
	}

	public void setBlueColour(int blueColor) {
		this.blueColour = blueColor;
	}

	@Override
	public int hashCode() {
		
		final int prime = 87;
		int resultado = 1;
		
		resultado = prime * resultado + Float.hashCode(redColour);
		resultado = prime * resultado + Float.hashCode(greenColour);
		resultado = prime * resultado + Float.hashCode(blueColour);
		
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
		
		Color colorComparado = (Color) objetoComparado;
		
		if ( redColour != colorComparado.redColour )
			return false;
		
		if ( greenColour != colorComparado.greenColour )
			return false;
		
		if ( blueColour != colorComparado.blueColour )
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Color [ RED = " + redColour + ", GREEN = " + greenColour + ", BLUE = " + blueColour + " ]";
	}
	
	
}
