package es.cic.curso.curso06.wallachof.dominio.producto.versionI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImpl;

@Repository
@Transactional
public class ProductoRepositoryImpl extends AbstractRepositoryImpl<Long, Producto> implements ProductoRepository {

	@Override
	public Class<Producto> getClassDeT() {
		return Producto.class;
	}

	@Override
	public String getNombreTabla() {
		return "producto";
	}

}
