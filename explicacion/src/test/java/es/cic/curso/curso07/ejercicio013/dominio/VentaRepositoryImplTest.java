package es.cic.curso.curso07.ejercicio013.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso07/ejercicio013/applicationContext.xml"
				})
public class VentaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Venta> {

	@Autowired
	private VentaRepository sut;
	
	@Before
	public void setUp() throws Exception {
	}

	@Override
	public IRepository<Long, Venta> getRepository() {
		return sut;
	}

	@Override
	public Venta getInstanceDeTParaNuevo() {
		Venta venta = new Venta();
		venta.setDescuento(true);
		venta.setCuantasEntradas((short)6);
		venta.setPrecio(27);
		venta.setSalaId(1L);
		venta.setSesionId(2L);
		return venta;
	}

	@Override
	public Venta getInstanceDeTParaLectura() {
		Venta venta = new Venta();
		venta.setDescuento(true);
		venta.setCuantasEntradas((short)6);
		venta.setPrecio(27);
		venta.setSalaId(1L);
		venta.setSesionId(0L);
		return venta;
	}

	@Override
	public boolean sonDatosIguales(Venta t1, Venta t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.isDescuento() && !t2.isDescuento() || !t1.isDescuento() && t2.isDescuento()) {
			return false;
		}
		
		if (t1.getCuantasEntradas() != t2.getCuantasEntradas()) {
			return false;
		}
		if (t1.getSalaId() != t2.getSalaId()) {
			return false;
		}
		if (t1.getSesionId() != t2.getSesionId()) {
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
		venta.setSalaId(2L);
		return venta;
	}


}
