package es.cic.curso.curso04.mascota.repository;

import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.repositorio.Repositorio;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSala;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSesion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/grupo6.ejercicio024/applicationContext.xml" })

public class SalaRepositorioImplTest extends AbstractRepositoryImplTest<Long, Sala> {

	@Autowired
	private RepositorioSala salaRepository;
	
	@Autowired
	private RepositorioSesion sesiones;
	
	List<Sesion> sesion;

	@Before
	public void setUp() throws Exception {
		sesion = sesiones.list();
		
	}

	@Override
	public Sala getInstanceDeTParaNuevo() {
		
		Sala sala = new Sala();
		sala.setSesiones(sesion);
		sala.setCapacidad(100);
		sala.setAbierto(true);

		return sala;
	}

	@Override
	public Sala getInstanceDeTParaLectura() {

		Sala sala = new Sala();
		sala.setSesiones(sesion);
		sala.setCapacidad(100);
		sala.setAbierto(true);

		return sala;
	}

	@Override
	public boolean sonDatosIguales(Sala t1, Sala t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getCapacidad() != t2.getCapacidad()) {
			return false;
		}
		if (t1.getSesiones() != t2.getSesiones()) {
			return false;
		}
		if (t1.isAbierto()!= t2.isAbierto()) {
			return false;
		}
		
		return true;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Sala getInstanceDeTParaModificar(Long clave) {
		Sala sala = getInstanceDeTParaLectura();
		sala.setId(clave);
		sala.setCapacidad(90);
		return sala;
	}

	@Override
	public Repositorio<Long, Sala> getRepository() {
		return salaRepository;
	}
}
