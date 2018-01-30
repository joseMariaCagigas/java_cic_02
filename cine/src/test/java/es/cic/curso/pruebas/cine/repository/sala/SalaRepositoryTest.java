package es.cic.curso.pruebas.cine.repository.sala;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.pruebas.cine.AbstractRepositoryImplTest;
import es.cic.curso.pruebas.cine.IRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"classpath:es/cic/curso/pruebas/cine/applicationContext.xml"})
public class SalaRepositoryTest extends AbstractRepositoryImplTest<Long, Sala>{

	@Autowired
	private SalaRepository sut;
	
	@Before
	public void setUp(){
		super.setUp();

	}

	@Override
	public IRepository<Long, Sala> getRepository() {
		
		return sut;
	}

	@Override
	public Sala getInstanceDeTParaNuevo() {
		Sala sala = new Sala();
		sala.setAforo(50);
		return sala;
	}

	@Override
	public Sala getInstanceDeTParaLectura() {
		Sala sala = new Sala();
		sala.setAforo(50);
		return sala;
	}

	@Override
	public boolean sonDatosIguales(Sala t1, Sala t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		
		if (t1.getAforo() != t2.getAforo() ){
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
		sala.setAforo(50);
		return sala;
	}


}
