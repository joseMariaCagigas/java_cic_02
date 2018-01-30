package es.cic.curso.grupo6.ejercicio024.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSala;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSesion;
@Service
@Transactional
public class ServicioGestorCineImpl implements ServicioGestorCine {
	
	private static final String ERROR_SALA_ID = "No existe ninguna sala en BB.DD. con ese ID";
	private static final String ERROR_SESION_ID = "No existe ninguna sesión en BB.DD. con ese ID";
	private static final String ERROR_ESTADO_SALA = "No puede haber sesiones programadas en la sala";
	
	@Autowired
	private RepositorioSala repositorioSala;
	
	@Autowired
	private RepositorioSesion repositorioSesion;

	@Override
	public void agregaSala(Sala sala) {
		repositorioSala.create(sala);
	}

	@Override
	public void agregaSesion(Long idSala, Sesion sesion) {
		Sala sala = obtenSala(idSala);
		sesion.setSala(sala);
	
		repositorioSesion.create(sesion);
	}

	@Override
	public Sala obtenSala(Long id) {
		Sala resultado = repositorioSala.read(id);
		if (resultado == null) {
			throw new IllegalArgumentException(ERROR_SALA_ID + ": " + id);
		}
		return resultado;
	}

	@Override
	public Sesion obtenSesion(Long id) {
		Sesion resultado = repositorioSesion.read(id);
		if (resultado == null) {
			throw new IllegalArgumentException(ERROR_SESION_ID + ": " + id);
		}
		return resultado;
	}

	@Override
	public Sala modificaSala(Sala sala) {
		
		return repositorioSala.update(sala);
		
	}
	
	@Override
	public void modificaSesion(Sesion sesion) {
		
		repositorioSesion.update(sesion);
	}

	@Override
	public Sesion modificaSesion(Long id, Sesion sesion) {
		Sesion sesionOriginal = obtenSesion(id);
		sesion.setId(id);
		repositorioSesion.update(sesion);
		return sesionOriginal;
	}

	@Override
	public Sala eliminaSala(Long id) {
		Sala sala = obtenSala(id);
		if (!listaSesiones(id).isEmpty()) {
			throw new IllegalStateException(ERROR_ESTADO_SALA);
		}
		repositorioSala.delete(sala);
		return sala;
	}

	@Override
	public Sesion eliminaSesion(Long id) {
		Sesion sesion = obtenSesion(id);
		repositorioSesion.delete(sesion);
		return sesion;
	}
	
	@Override
	public void eliminaSesiones(Long id) {
		Sala sala = repositorioSala.read(id);
		if (sala == null) {
			throw new IllegalArgumentException(ERROR_SALA_ID + ": " + id);
		}
		repositorioSesion.deleteSesionesSala(id);
	}

	@Override
	public List<Sala> listaSalas() {
		return repositorioSala.list();
	}

	@Override
	public List<Sesion> listaSesiones() {
		return repositorioSesion.list();
	}

	@Override
	public List<Sesion> listaSesiones(Long id) {
		Sala sala = repositorioSala.read(id);
		if (sala == null) {
			throw new IllegalArgumentException(ERROR_SALA_ID + ": " + id);
		}
		return repositorioSesion.listSesionesSala(id);
	}

}