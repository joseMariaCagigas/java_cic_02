package es.cic.curso.pruebas.cine.repository.sesion;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.pruebas.cine.AbstractRepositoryImplTest;
import es.cic.curso.pruebas.cine.IRepository;
import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;
import es.cic.curso.pruebas.cine.repository.sesion.SesionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"classpath:es/cic/curso/pruebas/cine/applicationContext.xml"})
public class SesionRepositoryTest  extends AbstractRepositoryImplTest<Long, Sesion>{


	@Autowired
	private SesionRepository sut;
	
	//Creamos objeto por la salaid de la sesion
	private Sala sala;
	
	@Before
	public void setUp() {
		super.setUp();
		
		sala = new Sala(100);
		em.persist(sala);
	}
	
	//Después añadimos los métodos

	@Override
	public IRepository<Long, Sesion> getRepository() {

		return sut;
	}

	@Override
	public Sesion getInstanceDeTParaNuevo() {

		Sesion sesion = new Sesion();
		sesion.setAsientosOcupados(0);
		sesion.setEstaCerrada(false);
		sesion.setSala(sala);
		
		return sesion;
	}

	@Override
	public Sesion getInstanceDeTParaLectura() {

		Sesion sesion = new Sesion();
		sesion.setAsientosOcupados(0);
		sesion.setEstaCerrada(false);
		sesion.setSala(sala);
		
		return sesion;
	}

	@Override
	public boolean sonDatosIguales(Sesion t1, Sesion t2) {

		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getAsientosOcupados() != t2.getAsientosOcupados()) {
			return false;
		}
		if (!t1.getSala().equals(t2.getSala())){
			return false;
		}
		
		if (t1.isEstaCerrada() != t2.isEstaCerrada()) {
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
		sesion.setAsientosOcupados(10);
		return sesion;
	}
	
	
	
}
