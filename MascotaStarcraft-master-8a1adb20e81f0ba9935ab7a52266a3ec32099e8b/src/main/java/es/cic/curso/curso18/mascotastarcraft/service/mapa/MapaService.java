package es.cic.curso.curso18.mascotastarcraft.service.mapa;

import es.cic.curso.curso18.mascotastarcraft.Sitio.Mapa;
import es.cic.curso.curso18.mascotastarcraft.fortaleza.Base;
import java.util.List;

public interface MapaService {

    Mapa actualizarMapa(Mapa modificada);

    Long aniadirMapa(String nombreMapa, int basesMaximo, int jugadoresMaximo, boolean jugado, long idBase);

    void borrarMapa(Long id);

    Mapa obtenerMapa(Long id);

    List<Mapa> obtenerMapas();
    
}
