package es.cic.curso.pruebas.cine.repository.venta;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.pruebas.cine.AbstractRepositoryImpl;

@Repository
@Transactional
public class VentaRepositoryImpl  extends AbstractRepositoryImpl<Long, Venta> implements VentaRepository {

	@Override
	public Class<Venta> getClassDeT() {
		// TODO Auto-generated method stub
		return Venta.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "venta";
	}

}
