package es.cic.curso.curso12.ejercicio023.dominio;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}