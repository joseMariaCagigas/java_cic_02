package es.cic.curso.curso07.ejercicio013.dominio;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}