package es.cic.curso.curso07.ejercicio013.dominio;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso07/ejercicio013/applicationContext.xml"
				})

public class SalaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Sala> {

	private static int salaActual;
	
	@Autowired
	private SalaRepository sut;
	
	@Before
	public void setUp() throws Exception {
	}

	@Override
	public Sala getInstanceDeTParaNuevo() {
		Sala sala = new Sala();
		sala.setCapacidad(100);
		sala.setNumeroDeSala(salaActual++);
		sala.setOcupadasSesion1(14);
		sala.setOcupadasSesion2(47);
		sala.setOcupadasSesion3(18);
		return sala;
	}

	@Override
	public Sala getInstanceDeTParaLectura() {
		Sala sala = new Sala();
		sala.setCapacidad(55);
		sala.setNumeroDeSala(salaActual++);
		sala.setOcupadasSesion1(4);
		sala.setOcupadasSesion2(7);
		sala.setOcupadasSesion3(8);
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
//		if (t1.getNumeroDeSala() != t2.getNumeroDeSala()) {
//			return false;
//		}		
		if (t1.getOcupadasSesion1() != t2.getOcupadasSesion1()) {
			return false;
		}
		if (t1.getOcupadasSesion2() != t2.getOcupadasSesion2()) {
			return false;
		}
		if (t1.getOcupadasSesion3() != t2.getOcupadasSesion3()) {
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
		sala.setCapacidad(101);
		return sala;
	}

	@Override
	public IRepository<Long, Sala> getRepository() {
		return sut;
	}	
}
