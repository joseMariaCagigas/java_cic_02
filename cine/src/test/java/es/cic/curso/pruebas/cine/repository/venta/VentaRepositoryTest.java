package es.cic.curso.pruebas.cine.repository.venta;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.pruebas.cine.AbstractRepositoryImplTest;
import es.cic.curso.pruebas.cine.IRepository;
import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/pruebas/cine/applicationContext.xml"
		})
public class VentaRepositoryTest extends AbstractRepositoryImplTest<Long, Venta>{

	@Autowired
	private VentaRepository sut;
	//Creamos los objetos
	private Sala sala;
	private Sesion sesion;
	
	@Before
	public void setUp(){
		super.setUp();
		//los llenamos en el orden de necesidad
		sala = new Sala(100);
		em.persist(sala);
		
		sesion = new Sesion(10, false, sala);
		em.persist(sesion);
	}	
	
	@Override
	public IRepository<Long, Venta> getRepository() {

		return sut;
	}
	@Override
	public Venta getInstanceDeTParaNuevo() {

		Venta venta = new Venta();
		venta.setSala(sala);
		venta.setSesion(sesion);
		venta.setNumEntradas(10);
		venta.setImporte(30.5);
				
		return venta;
	}
	@Override
	public Venta getInstanceDeTParaLectura() {

		Venta venta = new Venta();
		venta.setSala(sala);
		venta.setSesion(sesion);
		venta.setNumEntradas(10);
		venta.setImporte(30.5);
				
		return venta;
	}
	@Override
	public boolean sonDatosIguales(Venta t1, Venta t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (!t1.getSala().equals(t2.getSala())) {
			return false;
		}//equals para objetos y strings
		if (!t1.getSesion().equals(t2.getSesion())) {
			return false;
		}		
		if (t1.getNumEntradas() != t2.getNumEntradas()) {
			return false;
		}	
		if (t1.getImporte() != t2.getImporte()) {
			return false;
		}		
		
		return true;
	}
	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Venta getInstanceDeTParaModificar(Long clave) {
		Venta venta = getInstanceDeTParaLectura();
		venta.setId(clave);
		venta.setNumEntradas(20);
		
		return venta;
	}
	
	

}
