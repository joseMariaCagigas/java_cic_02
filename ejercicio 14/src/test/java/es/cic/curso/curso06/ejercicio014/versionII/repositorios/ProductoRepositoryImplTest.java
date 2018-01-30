package es.cic.curso.curso06.ejercicio014.versionII.repositorios;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso06.ejercicio014.versionII.datos.Producto;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"classpath:es/cic/curso/curso06/ejercicio014/applicationContext.xml"	})

public class ProductoRepositoryImplTest extends AbstractRepositoryImplTest<Long, Producto> {


	@Autowired
	private ProductoRepository sut;
	
	@Before
	public void setUp() throws Exception {
	}

	@Override
	public Producto getInstanceDeTParaNuevo() {
		Producto producto = new Producto();
		producto.setCode("ES-123456789");
		producto.setNombre("Galletas Maria");
		producto.setPrecio(4.3);
		producto.setCantidadStock(12);
		producto.setCantidadTienda(6);
		producto.setCantidadAlmacen(6);
		return producto;
	}

	@Override
	public Producto getInstanceDeTParaLectura() {
		Producto producto = new Producto();
		producto.setCode("ES-987654321");
		producto.setNombre("Dulce De Leche Havanna");
		producto.setPrecio(5.5);
		producto.setCantidadStock(10);
		producto.setCantidadTienda(3);
		producto.setCantidadAlmacen(7);
		return producto;
	}

	@Override
	public boolean sonDatosIguales(Producto t1, Producto t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("Los nulos no se comparan");
		}
		if (t1.getNombre() != t2.getNombre()) {
			return false;
		}
		
		if (t1.getCode() != t2.getCode()) {
			return false;
		}
		
		return true;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Producto getInstanceDeTParaModificar(Long clave) {
		Producto producto = getInstanceDeTParaLectura();
		producto.setId(clave);
		producto.setCode("ES-987654321");
		return producto;
	}

	@Override
	public IRepository<Long, Producto> getRepository() {
		return sut;
	}	
}
