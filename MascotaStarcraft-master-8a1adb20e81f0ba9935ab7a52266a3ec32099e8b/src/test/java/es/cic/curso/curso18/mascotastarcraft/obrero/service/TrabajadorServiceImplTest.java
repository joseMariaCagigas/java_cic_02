/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.mascotastarcraft.obrero.service;

import es.cic.curso.curso18.mascotastarcraft.obrero.Trabajador;
import es.cic.curso.curso18.mascotastarcraft.obrero.TrabajadorRepository;
import es.cic.curso.curso18.mascotastarcraft.service.obrero.TrabajadorService;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
            "classpath:es/cic/curso/curso18/mascotastarcraft/applicationContext.xml"
        })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
@Transactional
public class TrabajadorServiceImplTest {

    @Autowired
    TrabajadorService sut;

    @Autowired
    TrabajadorRepository trabajadorRepository;

    public TrabajadorServiceImplTest() {
    }

    public TrabajadorService getSut() {
        return sut;
    }

    public void setSut(TrabajadorService sut) {
        this.sut = sut;
    }

    @Before
    public void setUp() throws Exception {
        limpiarTrabajadors();
    }

    @Test
    public void testAniadirBase() {
        Long idTrabajador = sut.aniadirTrabajador("Sonda");

        assertNotNull(idTrabajador);
    }

    @Test
    public void testObtenerBase() {
        Long idTrabajador = sut.aniadirTrabajador("Sonda");

        Trabajador trabajador = sut.obtenerTrabajador(idTrabajador);

        assertNotNull(trabajador.getId());
        assertTrue(trabajador.getTipoTrabajador().equalsIgnoreCase("Sonda"));
    }

    @Test
    public void testObtenerBases() {
        List<Trabajador> trabajadores = sut.obtenerTrabajadors();
        trabajadores.forEach((trabajador) -> {
            assertNotNull(trabajador.getId());
        });
    }

    @Test
    public void testActualizarBase() {
        Long idTrabajador = sut.aniadirTrabajador("Sonda");

        Trabajador trabajador = sut.obtenerTrabajador(idTrabajador);
        trabajador.setTipoTrabajador("Sonda");

        Trabajador trabajadorMod = sut.obtenerTrabajador(idTrabajador);

        assertTrue(trabajadorMod.getTipoTrabajador().equalsIgnoreCase("Sonda"));
    }

    @Test
    public void testBorrarBase() {
        Long idTrabajador = sut.aniadirTrabajador("Sonda");

        sut.borrarTrabajador(idTrabajador);

        List<Trabajador> trabajadors = sut.obtenerTrabajadors();

        assertTrue(trabajadors.isEmpty());
    }

    private void limpiarTrabajadors() {
        List<Trabajador> bases = sut.obtenerTrabajadors();
        bases.forEach((base) -> {
            trabajadorRepository.delete(base);
        });
    }
}
