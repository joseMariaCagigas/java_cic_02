package es.cic.curso.grupo2.ejercicio024.repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso.grupo2.ejercicio024.dominio.Venta;

@Repository
@Transactional
public class VentaRepositoryImpl extends AbstractRepositoryImpl <Long,Venta> implements VentaRepository
{

	@Override
	public Class<Venta> getClassDeT() {
		return Venta.class;
	}

	@Override
	public String getNombreTabla() {
		return "venta";
	}
	
}
