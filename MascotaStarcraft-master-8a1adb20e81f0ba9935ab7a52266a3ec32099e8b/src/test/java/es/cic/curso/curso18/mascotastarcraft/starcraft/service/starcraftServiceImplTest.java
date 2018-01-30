package es.cic.curso.curso18.mascotastarcraft.starcraft.service;

import es.cic.curso.curso18.mascotastarcraft.service.base.BaseService;
import es.cic.curso.curso18.mascotastarcraft.service.mapa.MapaService;
import es.cic.curso.curso18.mascotastarcraft.service.obrero.TrabajadorService;
import es.cic.curso.curso18.mascotastarcraft.service.starcraft.StarcraftService;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
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
public class starcraftServiceImplTest {

    @Autowired
    StarcraftService starcraftService;
    @Autowired
    TrabajadorService trabajadorService;

    @Before
    public void setUp() throws Exception {

    }

    @Ignore
    @Test
    public void testAnnadirTrabajador() {
        starcraftService.annadirTrabajador("Zangano",5L);
        assertTrue(!trabajadorService.obtenerTrabajadors().isEmpty());
    }

}
