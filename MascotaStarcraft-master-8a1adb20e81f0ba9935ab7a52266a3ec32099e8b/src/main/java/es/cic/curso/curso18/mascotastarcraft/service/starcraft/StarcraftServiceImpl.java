package es.cic.curso.curso18.mascotastarcraft.service.starcraft;

import es.cic.curso.curso18.mascotastarcraft.fortaleza.Base;
import es.cic.curso.curso18.mascotastarcraft.service.base.BaseService;
import es.cic.curso.curso18.mascotastarcraft.service.mapa.MapaService;
import es.cic.curso.curso18.mascotastarcraft.service.obrero.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;

public class StarcraftServiceImpl implements StarcraftService {

    @Autowired
    MapaService mapaService;

    @Autowired
    BaseService baseService;

    @Autowired
    TrabajadorService trabajadorService;

    public StarcraftServiceImpl() {
    }

    
    
    @Override
    public void annadirTrabajador(String tipoTrabajador, Long idBase){
        
        Base modificada = baseService.obtenerBase(idBase);
        
        trabajadorService.aniadirTrabajador(tipoTrabajador);
        modificada.setTrabajadoresMaximo(modificada.getTrabajadoresMaximo()+1);
        
        baseService.actualizarBase(modificada);
        
    }
    
}
