package es.cic.curso.grupo4.ejercicio024.repositorio;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo4.ejercicio024.dominio.Sala;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo4/ejercicio024/applicationContext.xml"
				})

public class SalaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Sala> {

	@Autowired
	private SalaRepository sut;
	
	@Before
	public void setUp() throws Exception {
	}

	@Override
	public Sala getInstanceDeTParaNuevo() {
		Sala sala = new Sala();
		sala.setButacasTotales(100);
		sala.setNumSala(1);
		sala.setRecaudacion(0);
		return sala;
	}

	@Override
	public Sala getInstanceDeTParaLectura() {
		Sala sala = new Sala();
		sala.setButacasTotales(50);
		sala.setNumSala(2);
		sala.setRecaudacion(25);
		return sala;
	}

	@Override
	public boolean sonDatosIguales(Sala t1, Sala t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getButacasTotales() != t2.getButacasTotales()) {
			return false;
		}		
		if (t1.getNumSala() != t2.getNumSala()) {
			return false;
		}
		if (t1.getRecaudacion() != t2.getRecaudacion()) {
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
		sala.setButacasTotales(30);
		return sala;
	}

	@Override
	public IRepository<Long, Sala> getRepository() {
		return sut;
	}	
}