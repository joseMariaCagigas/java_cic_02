package es.cic.curso.grupo5.ejercicio024.modelo;

import java.io.Serializable;

public interface Identificable<K extends Number> extends Serializable {

	/**
	 * @return El identificador del objeto.
	 */
	K getId();

	/**
	 * Establece un nuevo identificador para el objeto.
	 * 
	 * @param id
	 *            Nuevo identificador
	 */
	void setId(K id);

}
