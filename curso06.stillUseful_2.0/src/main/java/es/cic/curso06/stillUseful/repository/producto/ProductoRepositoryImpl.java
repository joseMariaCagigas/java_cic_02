package es.cic.curso06.stillUseful.repository.producto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class ProductoRepositoryImpl extends AbstractRepositoryImpl<Long, Producto> implements ProductoRepository{

	@Override
	public Class<Producto> getClassDeT() {
		// TODO Auto-generated method stub
		return Producto.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "PRODUCTO";
	}

	

}
