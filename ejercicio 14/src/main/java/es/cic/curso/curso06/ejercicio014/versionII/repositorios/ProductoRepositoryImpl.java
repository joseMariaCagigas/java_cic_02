package es.cic.curso.curso06.ejercicio014.versionII.repositorios;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso06.ejercicio014.versionII.datos.Producto;

@Repository
public class ProductoRepositoryImpl extends AbstractRepositoryImpl<Long, Producto> implements ProductoRepository {

	@Override
	public Class<Producto> getClassDeT() {
		return Producto.class;
	}

	@Override
	public String getNombreTabla() {
		return "Producto";
	}

}
