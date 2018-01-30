package es.cic.curso.curso10.curso10.ejercicio023.repositorio;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}