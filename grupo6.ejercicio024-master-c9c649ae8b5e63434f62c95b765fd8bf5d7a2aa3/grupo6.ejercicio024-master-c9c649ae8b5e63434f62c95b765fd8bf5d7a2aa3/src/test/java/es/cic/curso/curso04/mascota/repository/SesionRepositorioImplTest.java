package es.cic.curso.curso04.mascota.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.repositorio.Repositorio;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSesion;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/grupo6.ejercicio024/applicationContext.xml" })

public class SesionRepositorioImplTest extends AbstractRepositoryImplTest<Long, Sesion> {

	@Autowired
	private RepositorioSesion repositorioSesion;

	
	Sala sala;

	@Before
	public void setUp() throws Exception {
		
	}

	@Override
	public Sesion getInstanceDeTParaNuevo() {
		Sesion sesion = new Sesion();
		sesion.setSala(sala);
		sesion.setAsientosOcupados(50);
		sesion.setAbierta(true);
		
		return sesion;
	}

	@Override
	public Sesion getInstanceDeTParaLectura() {
		Sesion sesion = new Sesion();
		sesion.setSala(sala);
		sesion.setAsientosOcupados(50);
		sesion.setAbierta(true);
		
		return sesion;
	}

	@Override
	public boolean sonDatosIguales(Sesion t1, Sesion t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getSala() != t2.getSala()) {
			return false;
		}
		if (t1.getAsientosOcupados() != t2.getAsientosOcupados()) {
			return false;
		}
		if (t1.isAbierta() != t2.isAbierta()) {
			return false;
		}
		return true;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Sesion getInstanceDeTParaModificar(Long clave) {
		Sesion habitacion = getInstanceDeTParaLectura();
		habitacion.setId(clave);
		habitacion.setAsientosOcupados(50);
		return habitacion;
	}

	@Override
	public Repositorio<Long, Sesion> getRepository() {
		return repositorioSesion;
	}
}
