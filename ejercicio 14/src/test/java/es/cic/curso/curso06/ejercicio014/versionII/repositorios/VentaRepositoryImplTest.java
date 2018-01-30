package es.cic.curso.curso06.ejercicio014.versionII.repositorios;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso06.ejercicio014.versionII.datos.Venta;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"classpath:es/cic/curso/curso06/ejercicio014/applicationContext.xml"})
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
		venta.setId_producto((long) 5);
		venta.setCantidad(2);
		venta.setImporte(10);
		
		
		return venta;
	}

	@Override
	public Venta getInstanceDeTParaLectura() {
		Venta venta = new Venta();
		venta.setId_producto((long) 5);
		venta.setCantidad(2);
		venta.setImporte(10);
		
		
		return venta;
	}

	@Override
	public boolean sonDatosIguales(Venta t1, Venta t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		
		if (t1.getId_producto() != t2.getId_producto()) {
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
		venta.setId_producto((long) 3);;
		return venta;
	}


}
