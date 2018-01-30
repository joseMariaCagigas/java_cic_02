package es.cic.curso.grupo4.ejercicio024.repositorio;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo4.ejercicio024.dominio.Venta;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo4/ejercicio024/applicationContext.xml"
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
		venta.setEsDevolucion(false);
		
		return venta;
	}

	@Override
	public Venta getInstanceDeTParaLectura() {
		Venta venta = new Venta();
		venta.setDescuento(true);
		venta.setCuantasEntradas((short)6);
		venta.setPrecio(27);
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
		if (t1.getSala() != t2.getSala()) {
			return false;
		}
		if (t1.getSesiones() != t2.getSesiones()) {
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
		venta.setId(clave);
		return venta;
	}
}
