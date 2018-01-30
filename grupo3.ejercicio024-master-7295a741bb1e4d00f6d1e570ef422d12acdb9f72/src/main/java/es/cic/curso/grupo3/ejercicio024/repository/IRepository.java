package es.cic.curso.grupo3.ejercicio024.repository;

import java.util.List;

public interface IRepository<K extends Number, T extends Identificable<K>> {
	
	T add(T nuevo);
	
	T read(K id);
	
	List<T> list();
	
	T update(T modificado);
	
	void delete (K id);
	
	void delete(T aBorrar);
}