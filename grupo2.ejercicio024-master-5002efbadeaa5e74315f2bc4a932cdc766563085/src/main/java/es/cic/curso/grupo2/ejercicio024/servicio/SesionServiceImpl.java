package es.cic.curso.grupo2.ejercicio024.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo2.ejercicio024.repositorio.SesionRepository;

@Service
public class SesionServiceImpl implements SesionService {
	
	@Autowired
	SesionRepository sesionRepository;
	
	@Override
	public Sesion leeSesion(Long id){
		return sesionRepository.read(id);
	}
	
	@Override
	public void nuevaSesion(Sesion sesion){
		sesionRepository.add(sesion);
	}
	
	@Override
	public List<Sesion> leerSesiones(){
		return sesionRepository.list();
	}
	
	@Override
	public void editaSesion(Long id, Sesion sesion){
		sesionRepository.update(sesion);
	}
	
	@Override
	public void borraSesion(Long id){
		sesionRepository.delete(id);
	}
	
	@Override
	public void abrirSesion(Sesion sesion){
		Sesion sesionAbrir = sesionRepository.read(sesion);
		
		sesionAbrir.setAbierta(true);
		
		sesionRepository.update(sesionAbrir);
	}
	
	@Override
	public void cerrarSesion(Sesion sesion){
		Sesion sesionCerrar = sesionRepository.read(sesion);
		
		sesionCerrar.setAbierta(false);
		
		sesionRepository.update(sesionCerrar);
	}
}
