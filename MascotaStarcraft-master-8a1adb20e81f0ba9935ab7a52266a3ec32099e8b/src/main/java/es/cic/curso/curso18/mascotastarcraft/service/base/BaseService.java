package es.cic.curso.curso18.mascotastarcraft.service.base;

import es.cic.curso.curso18.mascotastarcraft.fortaleza.Base;
import java.util.List;

public interface BaseService {

    Base actualizarBase(Base modificada);

    Long aniadirBase(int cantidadMineral, String tipoMineral, int TrabajadoresMaximo, long idTrabajador);

    void borrarBase(Long id);

    Base obtenerBase(Long id);

    List<Base> obtenerBases();
    
}
