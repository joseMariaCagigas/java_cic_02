package es.cic.curso.grupo6.ejercicio024.repositorio;

import java.io.Serializable;

public interface Identificable<K extends Number> extends Serializable {

	/**
	 * @return El identificador del objeto.
	 */
	

	void setId(Long id);

	Long getId();

}
