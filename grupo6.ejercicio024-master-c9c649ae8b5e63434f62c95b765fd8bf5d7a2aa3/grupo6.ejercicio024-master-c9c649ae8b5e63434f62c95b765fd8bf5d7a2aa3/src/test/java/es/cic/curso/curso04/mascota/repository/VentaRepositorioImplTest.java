package es.cic.curso.curso04.mascota.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.modelo.Venta;
import es.cic.curso.grupo6.ejercicio024.repositorio.Repositorio;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSesion;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioVenta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/grupo6.ejercicio024/applicationContext.xml" })

public class VentaRepositorioImplTest extends AbstractRepositoryImplTest<Long, Venta> {

	@Autowired
	private RepositorioVenta repositorioVenta;
	
	@PersistenceContext
	protected EntityManager em;
	
	Sesion sesion;
	Sala sala;
	List<Sesion> sesiones;
	
	@Autowired
	RepositorioSesion repositorioSesion;
	
	@Before
	public void setUp() throws Exception {
		sesiones = repositorioSesion.list();
		sala = new Sala();
		sala.setSesiones(sesiones);
		
		sesion = new Sesion();
		sesion.setSala(sala);
		sesion.setAsientosOcupados(2);
		sesion.setAbierta(true);
		em.persist(sala);
		em.persist(sesion);
	}

	@Override
	public Venta getInstanceDeTParaNuevo() {
		Venta venta = new Venta();
		
		venta.setnEntradas((short)5);
		venta.setSesion(sesion);
		venta.setDescuento(true);
		venta.setImporte(5L);
		
		return venta;
	}

	@Override
	public Venta getInstanceDeTParaLectura() {
Venta venta = new Venta();
		
		venta.setnEntradas((short)5);
		venta.setSesion(sesion);
		venta.setDescuento(true);
		venta.setImporte(5L);
		
		return venta;
	}

	@Override
	public boolean sonDatosIguales(Venta t1, Venta t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getImporte() != t2.getImporte()) {
			return false;
		}
		if (t1.getnEntradas() != t2.getnEntradas()) {
			return false;
		}
		if (t1.getSesion() != t2.getSesion()) {
			return false;
		}
		if (t1.isDescuento() != t2.isDescuento()) {
			return false;
		}
		
		return true;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MIN_VALUE;
	}

	@Override
	public Venta getInstanceDeTParaModificar(Long clave) {
		Venta venta = getInstanceDeTParaLectura();
		venta.setDescuento(false);
		return venta;
	}

	@Override
	public Repositorio <Long, Venta> getRepository() {
		return repositorioVenta;
	}
}
