package es.cic.curso.grupo4.ejercicio024.repositorio;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepositoryImpl<K extends Number, T extends Identificable<K>> implements IRepository<K, T>  {

	@PersistenceContext
	protected EntityManager entityManager;

	public AbstractRepositoryImpl() {
		super();
	}

	@Override
	public T add(T nuevo) {
		entityManager.persist(nuevo);
		entityManager.flush();
		return nuevo;
	}	
	
	@Override
	public T read(K id) {
		return entityManager.find(getClassDeT(), id);
	}	

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		return entityManager
				.createNativeQuery("select * from " + getNombreTabla(), getClassDeT())
				.getResultList();
	}	

	@Override
	public void delete(K id) {
		T aBorrar = entityManager.find(getClassDeT(), id);
		delete(aBorrar);
	}		
	
	@Override
	public void delete(T aBorrar) {
		entityManager.remove(aBorrar);
		entityManager.flush();
	}
	
	@Override
	public T update(T modificado) {
		modificado = entityManager.merge(modificado);
		entityManager.flush();
		return modificado;
	}		
}