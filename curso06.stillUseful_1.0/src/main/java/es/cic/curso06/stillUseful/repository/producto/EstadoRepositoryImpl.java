package es.cic.curso06.stillUseful.repository.producto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class EstadoRepositoryImpl extends AbstractRepositoryImpl<Long, Estado> implements EstadoRepository{

	@Override
	public Class<Estado> getClassDeT() {
		// TODO Auto-generated method stub
		return Estado.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "ESTADO";
	}

	

}
