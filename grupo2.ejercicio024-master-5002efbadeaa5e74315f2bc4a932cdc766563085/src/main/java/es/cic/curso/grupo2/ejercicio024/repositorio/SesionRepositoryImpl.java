package es.cic.curso.grupo2.ejercicio024.repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;

@Repository
@Transactional
public class SesionRepositoryImpl extends AbstractRepositoryImpl <Long,Sesion> implements SesionRepository
{	
	//EntityManager (Persistencia)
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Sesion add(Sesion nuevo)
	{
		//El Entity Manager Crea la Nueva
		entityManager.persist(nuevo);
		return nuevo;
	}
	
	@Override
	public Sesion read(Long id)
	{
		//El Entity Manager Lee la Indicada
		return entityManager.find(Sesion.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesion> list()
	{		
		//Devuelve una Lista de la Busqueda de la Tabla al Completo
		return entityManager
				.createNativeQuery("select id, peliculaSesion, salaSesion, hora, asientosDisponibles, abierta from sesion", Sesion.class)
				.getResultList();
	}

	@Override
	public Sesion update(Sesion modificado)
	{
		return entityManager.merge(modificado);
	}

	@Override
	public void delete(Long id)
	{
		Sesion sesion = new Sesion();
		sesion.setId(id);
		delete(sesion);
	}
	
	public void delete(Sesion sesion)
	{
		entityManager.remove(read(sesion.getId()));
	}

	@Override
	public Class<Sesion> getClassDeT() {
		return Sesion.class;
	}

	@Override
	public String getNombreTabla() {
		return Sesion.class.getSimpleName().toLowerCase();
	}
}
