package es.cic.curso.curso06.wallachof.repositorio.versionI;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}