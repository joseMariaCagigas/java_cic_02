package es.cic.curso.grupo6.ejercicio024.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo6.ejercicio024.ErrorVenta;
import es.cic.curso.grupo6.ejercicio024.modelo.Cine;
import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.modelo.Venta;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSala;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSesion;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioVenta;

@Service
@Transactional
public class ServicioGestorTaquillaImpl implements ServicioGestorTaquilla {

	public static final float PRECIO_ENTRADA = 5.0F;

	private static final String ERROR_SESION_ID = "No existe ninguna sesión en BB.DD. con ese ID";

	@Autowired
	private RepositorioSala repositorioSala;

	@Autowired
	private RepositorioSesion repositorioSesion;

	@Autowired
	private RepositorioVenta repositorioVenta;

	private Sesion obtenSesion(Long id) {
		Sesion resultado = repositorioSesion.read(id);
		if (resultado == null) {
			throw new IllegalArgumentException(ERROR_SESION_ID + ": " + id);
		}
		return resultado;
	}

	@Override
	public float calculaRecaudacion(Long idSala) {
		float resultado = 0;
		List<Sesion> l = repositorioSesion.list();

		for (Sesion sesion : l) {
			if(sesion.getSala().getId().equals(idSala))
				resultado += PRECIO_ENTRADA * sesion.getAsientosOcupados();
		}
		return resultado;
	}

	@Override
	public float calculaRecaudacion() {
		float resultado = 0;

		for (Sesion sesion : repositorioSesion.list()) {
			resultado += PRECIO_ENTRADA * sesion.getAsientosOcupados();
		}
		return resultado;
	}

	@Override
	public void cambiaEstadoSesion(Long idSesion, boolean abierta) {
		Sesion sesion = obtenSesion(idSesion);
		sesion.setAbierta(abierta);
		repositorioSesion.update(sesion);
	}

	@Override
	public void cambiaEstadoTaquilla(boolean abierta) {
		for (Sesion sesion : repositorioSesion.list()) {
			sesion.setAbierta(abierta);
			repositorioSesion.update(sesion);
		}
	}

	@Override
	public void cambiarEstadoCine(Cine cine, boolean estado){

		cine.setEstado(estado);
	}

	@Override
	public boolean estaAbierta(Long idSesion) {
		Sesion sesion = obtenSesion(idSesion);
		return sesion.isAbierta();
	}

	@Override
	public int localidadesDisponibles(Long idSesion) {
		Sesion sesion = obtenSesion(idSesion);
		Sala sala = sesion.getSala();
		return sala.getCapacidad() - sesion.getAsientosOcupados();
	}

	@Override
	public double vendeEntrada(Long idSesion, Long idLocalidad) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double vendeEntradas(Long idSesion, int nEntradas) throws ErrorVenta {
		
		
		Sesion sesion = obtenSesion(idSesion);
		
		Sala s = sesion.getSala();
		if(!s.isAbierto())
			throw new ErrorVenta();
		if(nEntradas<=0)
			throw new ErrorVenta();

		if ((sesion.getSala().getCapacidad() - sesion.getAsientosOcupados()) < nEntradas) {
			throw new IndexOutOfBoundsException();
		}
		float resultado = nEntradas * PRECIO_ENTRADA;
		int j = sesion.getAsientosOcupados();
		sesion.setAsientosOcupados(j + nEntradas);
		repositorioSesion.update(sesion);


		Venta venta = new Venta();
		venta.setDescuento(false);
		venta.setImporte(resultado);
		venta.setnEntradas((short) nEntradas);

		venta.setSesion(sesion);
		repositorioVenta.create(venta);

		return resultado;
	}

	@Override
	public List<Venta> listar(){
		return repositorioVenta.list();
	}

	@Override
	public void borrar(Venta v) {
		repositorioVenta.delete(v.getId());
		
	}

	@Override
	public Venta buscar(Long id) {
		return repositorioVenta.read(id);
		
	}

	

}