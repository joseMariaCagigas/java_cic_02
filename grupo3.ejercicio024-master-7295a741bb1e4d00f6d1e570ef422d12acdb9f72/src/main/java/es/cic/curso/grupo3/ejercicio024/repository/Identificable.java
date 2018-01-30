package es.cic.curso.grupo3.ejercicio024.repository;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);
}