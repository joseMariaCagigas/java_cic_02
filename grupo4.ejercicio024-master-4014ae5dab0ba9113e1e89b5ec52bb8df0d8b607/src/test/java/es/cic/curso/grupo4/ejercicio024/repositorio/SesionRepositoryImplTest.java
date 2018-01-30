package es.cic.curso.grupo4.ejercicio024.repositorio;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo4.ejercicio024.dominio.Sesion;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo4/ejercicio024/applicationContext.xml"
				})

public class SesionRepositoryImplTest extends AbstractRepositoryImplTest<Long, Sesion> {

	@Autowired
	private SesionRepository sut;
	
	@Before
	public void setUp() throws Exception {
	}

	@Override
	public Sesion getInstanceDeTParaNuevo() {
		Sesion sesion = new Sesion();
		sesion.setButacasDisp(100);
		sesion.setNumeSesion(1);
		return sesion;
	}

	@Override
	public Sesion getInstanceDeTParaLectura() {
		Sesion sesion = new Sesion();
		sesion.setButacasDisp(50);
		sesion.setNumeSesion(1);
		return sesion;
	}

	@Override
	public boolean sonDatosIguales(Sesion t1, Sesion t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getButacasDisp() != t2.getButacasDisp()) {
			return false;
		}		
		if (t1.getNumeSesion() != t2.getNumeSesion()) {
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
		Sesion sesion = getInstanceDeTParaLectura();
		sesion.setId(clave);
		sesion.setButacasDisp(30);
		return sesion;
	}

	@Override
	public IRepository<Long, Sesion> getRepository() {
		return sut;
	}	
}
