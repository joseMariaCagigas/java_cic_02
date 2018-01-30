package es.cic.curso.grupo2.ejercicio024.repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso.grupo2.ejercicio024.dominio.Sala;

@Repository
@Transactional
public class SalaRepositoryImpl extends AbstractRepositoryImpl <Long,Sala> implements SalaRepository
{
	//EntityManager (Persistencia)
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Sala add(Sala nuevo)
	{
		//El Entity Manager Crea la Nueva
		entityManager.persist(nuevo);
		return nuevo;
	}
	
	@Override
	public Sala read(Long id)
	{
		//El Entity Manager Lee la Indicada
		return entityManager.find(Sala.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> list()
	{		
		//Devuelve una Lista de la Busqueda de la Tabla al Completo
		return entityManager
				.createNativeQuery("select id, numeroSala, asientosTotales from sala", Sala.class)
				.getResultList();
	}

	@Override
	public Sala update(Sala modificado)
	{
		return entityManager.merge(modificado);
	}

	@Override
	public void delete(Long id)
	{
		Sala sala = new Sala();
		sala.setId(id);
		delete(sala);
	}
	
	public void delete(Sala sala)
	{
		entityManager.remove(read(sala.getId()));
	}
	
	@Override
	public Class<Sala> getClassDeT() {
		return Sala.class;
	}

	@Override
	public String getNombreTabla() {
		return Sala.class.getSimpleName().toLowerCase();
	}
}
