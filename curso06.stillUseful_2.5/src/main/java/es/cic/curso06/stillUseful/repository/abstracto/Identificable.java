package es.cic.curso06.stillUseful.repository.abstracto;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}