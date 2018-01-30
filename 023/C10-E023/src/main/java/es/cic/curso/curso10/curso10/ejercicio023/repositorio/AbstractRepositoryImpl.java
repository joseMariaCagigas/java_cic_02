package es.cic.curso.curso10.curso10.ejercicio023.repositorio;

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
		T resultado = entityManager.find(getClassDeT(), id);
		return resultado;
	}	
	@Override
	public T read (T nuevo){
		T resultado = entityManager.find(getClassDeT(), nuevo.getId());
		return resultado;
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
	}
	
	@Override
	public T update(T modificado) {
		modificado = entityManager.merge(modificado);
		return modificado;
	}		
}