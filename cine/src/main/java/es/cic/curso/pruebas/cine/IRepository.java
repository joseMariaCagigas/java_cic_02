package es.cic.curso.pruebas.cine;

import java.util.List;

import es.cic.curso.pruebas.cine.Identificable;

public interface IRepository<K extends Number, T extends Identificable<K>> {
	
	T add(T nuevo);
	T read(K id);
	List<T> list();	
	T update(T modificado);
	void delete(K id);
	void delete(T aBorrar);
	
	
	public abstract Class<T> getClassDeT();
	
	public abstract String getNombreTabla();	
}