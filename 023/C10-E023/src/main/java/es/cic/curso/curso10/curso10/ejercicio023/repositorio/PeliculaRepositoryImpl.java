package es.cic.curso.curso10.curso10.ejercicio023.repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.Pelicula;

@Repository
@Transactional
public class PeliculaRepositoryImpl extends AbstractRepositoryImpl <Long,Pelicula> implements PeliculaRepository
{
	//EntityManager (Persistencia)
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Pelicula add(Pelicula nuevo)
	{
		//El Entity Manager Crea la Nueva
		entityManager.persist(nuevo);
		return nuevo;
	}
	
	@Override
	public Pelicula read(Long id)
	{
		//El Entity Manager Lee la Indicada
		return entityManager.find(Pelicula.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pelicula> list()
	{		
		//Devuelve una Lista de la Busqueda de la Tabla al Completo
		return entityManager
				.createNativeQuery("select id, titulo, director, duracion from pelicula", Pelicula.class)
				.getResultList();
	}

	@Override
	public Pelicula update(Pelicula modificado)
	{
		return entityManager.merge(modificado);
	}

	@Override
	public void delete(Long id)
	{
		Pelicula pelicula = new Pelicula();
		pelicula.setId(id);
		delete(pelicula);
	}
	
	public void delete(Pelicula pelicula)
	{
		entityManager.remove(read(pelicula.getId()));
	}
	
	@Override
	public Class<Pelicula> getClassDeT() {
		return Pelicula.class;
	}

	@Override
	public String getNombreTabla() {
		return Pelicula.class.getSimpleName().toLowerCase();
	}
}
