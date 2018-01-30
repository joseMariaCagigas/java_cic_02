package es.cic.curso.curso18.mascotastarcraft.fortaleza;

import es.cic.curso.curso18.mascotastarcraft.obrero.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.mascotastarcraft.AbstractRepositoryImplTest;
import es.cic.curso.curso18.mascotastarcraft.dominio.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
            "classpath:es/cic/curso/curso18/mascotastarcraft/applicationContext.xml"
        })
public class BaseRepositoryTest extends AbstractRepositoryImplTest<Long, Base> {

    @Autowired
    private BaseRepository sut;

    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Override
    public IRepository<Long, Base> getRepository() {
        return sut;
    }

    @Override
    public Base getInstanceDeTParaNuevo() {
        Base baseTest = new Base();
        baseTest.setTipoMineral("Vespeno");
        return baseTest;
    }

    @Override
    public Base getInstanceDeTParaLectura() {
        Base baseTest = new Base();
        baseTest.setTipoMineral("Vespeno");
        return baseTest;
    }

    @Override
    public boolean sonDatosIguales(Base t1, Base t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }

        if (!t1.getTipoMineral().equals(t2.getTipoMineral())) {
            return false;
        }

        return true;
    }

    @Override
    public Long getClavePrimariaNoExistente() {

        return Long.MAX_VALUE;

    }

    @Override
    public Base getInstanceDeTParaModificar(Long clave) {
        Base trabajador = getInstanceDeTParaLectura();
        trabajador.setId(clave);
        trabajador.setTipoMineral("Vespeno");
        return trabajador;
    }

}
