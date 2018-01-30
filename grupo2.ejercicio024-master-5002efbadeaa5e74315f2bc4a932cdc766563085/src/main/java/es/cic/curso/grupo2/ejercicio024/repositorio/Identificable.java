package es.cic.curso.grupo2.ejercicio024.repositorio;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}