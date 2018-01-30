package es.cic.curso.curso06.ejercicio014.versionII.repositorios;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso06.ejercicio014.versionII.datos.Venta;

@Repository
public class VentaRepositoryImpl extends AbstractRepositoryImpl<Long, Venta> implements VentaRepository {

	@Override
	public Class<Venta> getClassDeT() {
		return Venta.class;
	}

	@Override
	public String getNombreTabla() {
		return "venta";
	}

}
