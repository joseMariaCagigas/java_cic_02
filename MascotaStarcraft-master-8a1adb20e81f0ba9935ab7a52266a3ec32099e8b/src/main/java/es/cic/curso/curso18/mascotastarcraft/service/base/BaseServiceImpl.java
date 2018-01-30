package es.cic.curso.curso18.mascotastarcraft.service.base;

import es.cic.curso.curso18.mascotastarcraft.fortaleza.Base;
import es.cic.curso.curso18.mascotastarcraft.fortaleza.BaseRepository;
import es.cic.curso.curso18.mascotastarcraft.obrero.Trabajador;
import es.cic.curso.curso18.mascotastarcraft.obrero.TrabajadorRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseRepository baseRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public BaseServiceImpl() {
    }

    @Override
    public Long aniadirBase(int cantidadMineral, String tipoMineral, int TrabajadoresMaximo, long idTrabajador) {

        Base base = new Base();
        base.setCantidadMineral(cantidadMineral);
        base.setTipoMineral(tipoMineral);
        base.setTrabajadoresMaximo(TrabajadoresMaximo);
        Trabajador trabajador = trabajadorRepository.read(idTrabajador);
        base.setTrabajador(trabajador);
        Base aniadida = aniadirBase(base);

        return aniadida.getId();
    }

    private Base aniadirBase(Base nueva) {
        return baseRepository.add(nueva);
    }

    @Override
    public Base obtenerBase(Long id) {
        return baseRepository.read(id);
    }

    @Override
    public List<Base> obtenerBases() {
        return baseRepository.list();
    }

    @Override
    public Base actualizarBase(Base modificada) {
        return baseRepository.update(modificada);
    }

    @Override
    public void borrarBase(Long id) {
        Base aBorrar = obtenerBase(id);
        baseRepository.delete(aBorrar);
    }

}
