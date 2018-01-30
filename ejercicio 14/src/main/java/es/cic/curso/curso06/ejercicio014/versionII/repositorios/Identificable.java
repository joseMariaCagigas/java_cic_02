package es.cic.curso.curso06.ejercicio014.versionII.repositorios;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}