package es.cic.curso.grupo2.ejercicio024.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.grupo2.ejercicio024.dominio.Sala;
import es.cic.curso.grupo2.ejercicio024.repositorio.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService {
	
	@Autowired
	SalaRepository salaRepository;
	
	@Override
	public Sala leeSala(Long salaId){
		Sala sala = salaRepository.read(salaId);
		return sala;
	}
	
	@Override
	public Long nuevaSala(Sala sala){
		salaRepository.add(sala);
		return sala.getId();
	}
	
	@Override
	public List<Sala> leerSala(){
		return salaRepository.list();
	}
	
	@Override
	public void editaSala(Long id, Sala sala){
		salaRepository.update(sala);
	}
	
	@Override
	public void borraSala(Long id){
		salaRepository.delete(id);
	}
}
