package es.cic.curso.grupo3.ejercicio024.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.grupo3.ejercicio024.dominio.Venta;

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