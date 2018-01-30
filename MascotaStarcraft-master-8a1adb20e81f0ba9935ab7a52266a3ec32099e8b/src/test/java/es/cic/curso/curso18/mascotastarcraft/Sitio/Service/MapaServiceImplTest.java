/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.mascotastarcraft.Sitio.Service;

import es.cic.curso.curso18.mascotastarcraft.Sitio.Mapa;
import es.cic.curso.curso18.mascotastarcraft.Sitio.MapaRepository;
import es.cic.curso.curso18.mascotastarcraft.service.mapa.MapaService;
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
public class MapaServiceImplTest {

    @Autowired
    MapaService sut;

    @Autowired
    MapaRepository mapaRepository;

    public MapaServiceImplTest() {
    }

    public MapaService getSut() {
        return sut;
    }

    public void setSut(MapaService sut) {
        this.sut = sut;
    }

    @Before
    public void setUp() throws Exception {
        limpiarMapas();
    }

    @Test
    public void testAniadirBase() {
        Long idBase = sut.aniadirMapa("Desert", 9, 8, false, 7);

        assertNotNull(idBase);
    }

    @Test
    public void testObtenerBase() {
        Long idMapa = sut.aniadirMapa("Desert", 9, 8, false, 7);

        Mapa mapa = sut.obtenerMapa(idMapa);

        assertNotNull(mapa.getId());
        assertTrue(mapa.getNombreMapa().equalsIgnoreCase("Desert"));
        assertTrue(mapa.getBasesMaximo() == 9);
        assertTrue(mapa.getJugadoresMaximo() == 8);
        assertTrue(mapa.isJugado() == false);
    }

    @Test
    public void testObtenerBases() {
        List<Mapa> mapas = sut.obtenerMapas();
        mapas.forEach((mapa) -> {
            assertNotNull(mapa.getId());
        });
    }

    @Test
    public void testActualizarBase() {
        Long idMapa = sut.aniadirMapa("Desert", 9, 8, false, 7);

        Mapa mapa = sut.obtenerMapa(idMapa);
        mapa.setNombreMapa("Oceanic");

        Mapa mapaMod = sut.obtenerMapa(idMapa);

        assertTrue(mapaMod.getNombreMapa().equalsIgnoreCase("Oceanic"));
    }

    @Test
    public void testBorrarBase() {
        Long idMapa = sut.aniadirMapa("Desert", 9, 8, false, 7);

        sut.borrarMapa(idMapa);

        List<Mapa> mapas = sut.obtenerMapas();

        assertTrue(mapas.isEmpty());
    }

    private void limpiarMapas() {
        List<Mapa> bases = sut.obtenerMapas();
        bases.forEach((base) -> {
            mapaRepository.delete(base);
        });
    }
}
