package es.cic.curso.grupo6.ejercicio024;

public class ErrorVenta extends Exception{

	public ErrorVenta(){
		new Exception("Error en la venta");
	}
}
