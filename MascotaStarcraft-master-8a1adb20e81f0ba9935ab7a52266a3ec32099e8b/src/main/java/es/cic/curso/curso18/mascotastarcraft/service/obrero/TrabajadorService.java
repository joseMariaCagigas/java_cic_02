package es.cic.curso.curso18.mascotastarcraft.service.obrero;

import es.cic.curso.curso18.mascotastarcraft.obrero.Trabajador;
import java.util.List;

public interface TrabajadorService {

    Trabajador actualizarTrabajador(Trabajador modificada);

    Long aniadirTrabajador(String tipoTrabajador);

    void borrarTrabajador(Long id);

    Trabajador obtenerTrabajador(Long id);

    List<Trabajador> obtenerTrabajadors();
    
}
