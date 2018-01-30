package es.cic.curso.curso07.ejercicio013.dominio;

import org.springframework.stereotype.Repository;

@Repository
public class VentaRepositoryImpl extends RepositorioAbstractoImpl<Long, Venta> implements VentaRepository {

	@Override
	public Class<Venta> getClassDeT() {
		return Venta.class;
	}

	@Override
	public String getNombreTabla() {
		return "venta";
	}

}
