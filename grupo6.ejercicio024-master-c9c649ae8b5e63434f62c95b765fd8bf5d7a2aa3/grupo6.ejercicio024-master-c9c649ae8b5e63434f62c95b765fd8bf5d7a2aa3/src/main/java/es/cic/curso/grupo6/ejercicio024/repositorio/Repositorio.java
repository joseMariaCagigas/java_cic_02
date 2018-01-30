package es.cic.curso.grupo6.ejercicio024.repositorio;

import java.util.List;

public interface Repositorio<K extends Number, T extends Identificable<K>> {
	
	T create(T element);
	T read(K id);
	T update(T element);
	T delete(K id);
	void delete(T element);
	List<T> list();
	public abstract Class<T> getClassDeT();


}
