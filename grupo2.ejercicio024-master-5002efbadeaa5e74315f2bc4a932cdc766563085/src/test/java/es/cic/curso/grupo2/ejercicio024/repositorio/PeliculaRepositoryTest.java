package es.cic.curso.grupo2.ejercicio024.repositorio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.cic.curso.grupo2.ejercicio024.dominio.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo2/ejercicio024/applicationContext.xml"
		})
public class PeliculaRepositoryTest extends AbstractRepositoryImplTest<Long, Pelicula>{

	@Autowired
	private PeliculaRepository sut;
	
	@Before
	public void setUp(){
		super.setUp();

	}


	@Override
	public IRepository<Long, Pelicula> getRepository() {
		return sut;
	}

	@Override
	public Pelicula getInstanceDeTParaNuevo() {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Titulo");
		return pelicula;
	}

	@Override
	public Pelicula getInstanceDeTParaLectura() {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Titulo");
		return pelicula;
	}

	@Override
	public boolean sonDatosIguales(Pelicula t1, Pelicula t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		
		if (!t1.getTitulo().equals(t2.getTitulo())) {
			return false;
		}		
		
		return true;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Pelicula getInstanceDeTParaModificar(Long clave) {
		Pelicula pelicula = getInstanceDeTParaLectura();
		pelicula.setId(clave);
		pelicula.setTitulo("TitluloNuevo");
		return pelicula;
	}

}
