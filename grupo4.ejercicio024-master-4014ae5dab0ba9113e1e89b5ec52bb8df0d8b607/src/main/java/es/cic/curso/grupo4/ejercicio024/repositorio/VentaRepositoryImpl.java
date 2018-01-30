package es.cic.curso.grupo4.ejercicio024.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo4.ejercicio024.dominio.Venta;

@Repository
@Transactional
public class VentaRepositoryImpl extends AbstractRepositoryImpl<Long, Venta> implements VentaRepository{

	@Override
	public Class<Venta> getClassDeT() {
		return Venta.class;
	}

	@Override
	public String getNombreTabla() {
		return "venta";
	}

}
