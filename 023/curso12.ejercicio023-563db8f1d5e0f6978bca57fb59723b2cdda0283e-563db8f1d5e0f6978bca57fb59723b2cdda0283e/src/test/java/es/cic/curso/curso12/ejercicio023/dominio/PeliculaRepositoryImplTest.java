package es.cic.curso.curso12.ejercicio023.dominio;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso12/ejercicio023/applicationContext.xml"
				})
public class PeliculaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Pelicula>{
	
	@Autowired
	private PeliculaRepository sut;

	@Before
	public void setUp() throws Exception {
	}
	
	@Override
	public IRepository<Long, Pelicula> getRepository() {
		return sut;
	}

	@Override
	public Pelicula getInstanceDeTParaNuevo() {
		Pelicula peli = new Pelicula();
		peli.setDirector("Michael Bay");
		peli.setTitulo("Pain & gain");
		peli.setInterprete("Mark Whalberg");
		peli.setProductora("Universal");
		peli.setDuracion(129);
		peli.setYear(2013);
		peli.setGenero("Crimen");
		
		return peli;
	}

	@Override
	public Pelicula getInstanceDeTParaLectura() {
		Pelicula peli = new Pelicula();
		peli.setDirector("Michael Bay");
		peli.setTitulo("Pain & gain");
		peli.setInterprete("Mark Whalberg");
		peli.setProductora("Universal");
		peli.setDuracion(129);
		peli.setYear(2013);
		peli.setGenero("Crimen");
		
		return peli;
	}

	@Override
	public boolean sonDatosIguales(Pelicula p1, Pelicula p2) {
		if (p1 == null || p2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (!p1.getTitulo().equals(p2.getTitulo())){
			return false;
		}
		if(!p1.getDirector().equals(p2.getDirector())){
			return false;
		}
		if(!p1.getInterprete().equals(p2.getInterprete())){
			return false;
		}
		if(!p1.getProductora().equals(p2.getProductora())){
			return false;
		}
		if(!p1.getGenero().equals(p2.getGenero())){
			return false;
		}
		if(p1.getDuracion() != p2.getDuracion()){
			return false;
		}
		if(p1.getYear()!=p2.getYear()){
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
		Pelicula peli = getInstanceDeTParaLectura();
		peli.setId(clave);
		peli.setTitulo("Doraemon");
		
		return peli;
	}

}
