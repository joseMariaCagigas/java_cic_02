package es.cic.curso.curso18.mascotastarcraft.fortaleza.Sitio;

import es.cic.curso.curso18.mascotastarcraft.obrero.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.mascotastarcraft.AbstractRepositoryImplTest;
import es.cic.curso.curso18.mascotastarcraft.Sitio.Mapa;
import es.cic.curso.curso18.mascotastarcraft.Sitio.MapaRepository;
import es.cic.curso.curso18.mascotastarcraft.dominio.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
            "classpath:es/cic/curso/curso18/mascotastarcraft/applicationContext.xml"
        })
public class MapaRepositoryTest extends AbstractRepositoryImplTest<Long, Mapa> {

    @Autowired
    private MapaRepository sut;

    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Override
    public IRepository<Long, Mapa> getRepository() {
        return sut;
    }

    @Override
    public Mapa getInstanceDeTParaNuevo() {
        Mapa mapaTest = new Mapa();
        mapaTest.setNombreMapa("Ocean");
        return mapaTest;
    }

    @Override
    public Mapa getInstanceDeTParaLectura() {
        Mapa mapaTest = new Mapa();
        mapaTest.setNombreMapa("Ocean");
        return mapaTest;
    }

    @Override
    public boolean sonDatosIguales(Mapa t1, Mapa t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }

        if (!t1.getNombreMapa().equals(t2.getNombreMapa())) {
            return false;
        }

        return true;
    }

    @Override
    public Long getClavePrimariaNoExistente() {

        return Long.MAX_VALUE;

    }

    @Override
    public Mapa getInstanceDeTParaModificar(Long clave) {
        Mapa trabajador = getInstanceDeTParaLectura();
        trabajador.setId(clave);
        trabajador.setNombreMapa("Ocean");
        return trabajador;
    }

}
