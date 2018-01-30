package es.cic.curso.grupo3.ejercicio024.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;
import es.cic.curso.grupo3.ejercicio024.helper.TestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo3/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class VentaRepositoryTest {

	private static final double DELTA = 0.001;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private TestHelper tHelper;
	@Autowired
	private SalaRepository salaRepository;

	@PersistenceContext
	private EntityManager em;
	
	Long claveSala;
	Sala sala;
	
	@Before
	public void setUp() throws Exception {
		claveSala = tHelper.generaSalaUnoSesionUno();
		sala = salaRepository.read(claveSala);
	}

	@Test
	public void testAdd() {
		Venta venta = new Venta();
		venta.setCantidad(100);
		assertNull(venta.getId());

		ventaRepository.add(venta);

		assertNotNull(venta.getId());
	}

	@Test
	public void testRead() {
		Long clavePrimaria = tHelper.generaVentaSesionUno(sala);

		Venta resultado = ventaRepository.read(clavePrimaria);

		assertEquals(1, resultado.getSalaId().getNumSesion());
	}

	@Test(expected=PersistenceException.class)
	public void testRead_noExiste() {
		Long clavePrimaria = Long.MIN_VALUE;

		Venta resultado = ventaRepository.read(clavePrimaria);

		assertEquals(5, resultado.getSalaId().getNumSesion());
	}

	@Test
	public void testList() {
		tHelper.generaVentaSesionUno(sala);
		tHelper.generaVentaSesionUno(sala);
		tHelper.generaVentaSesionUno(sala);

		List<Venta> resultado = ventaRepository.list();

		assertTrue(resultado.size()>= 3);
	}

	@Test
	public void testDelete() {
		Long clavePrimaria = tHelper.generaVentaSesionUno(sala);

		ventaRepository.delete(clavePrimaria);
		Venta v;
		try {
			v  = em.find(Venta.class, clavePrimaria);
		} catch (PersistenceException pe){
			return;
		}
		assertNull(v);
	}

	@Test
	public void testUpdate() {
		Long clavePrimaria = tHelper.generaVentaSesionUno(sala);

		Venta venta2 = new Venta();
		venta2.setId(clavePrimaria);
		venta2.setBeneficio(100);

		Venta resultado = ventaRepository.update(venta2);

		Venta enBBDD = em.find(Venta.class, clavePrimaria);
		assertEquals(100, enBBDD.getBeneficio(), DELTA);
		assertEquals(100, resultado.getBeneficio(), DELTA);
	}
}