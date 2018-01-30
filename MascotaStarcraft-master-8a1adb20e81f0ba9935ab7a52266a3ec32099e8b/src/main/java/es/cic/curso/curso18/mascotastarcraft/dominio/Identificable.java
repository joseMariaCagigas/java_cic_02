package es.cic.curso.curso18.mascotastarcraft.dominio;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}