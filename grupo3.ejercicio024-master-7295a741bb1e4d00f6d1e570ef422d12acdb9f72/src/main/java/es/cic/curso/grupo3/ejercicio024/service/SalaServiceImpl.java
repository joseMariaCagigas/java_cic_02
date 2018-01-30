package es.cic.curso.grupo3.ejercicio024.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;

@Service
@Transactional
public class SalaServiceImpl implements SalaService {
	
	@Autowired 
	private SalaRepository salaRepository;
	
	@Override
	public boolean hayAsientosLibres(Sala sala, int cantidad){
		boolean resultado = false;
		
		if (!esCerradoSesion(sala)){
			int asientosLibres = sala.getAsientosLibres();

			resultado = hayAsientos(asientosLibres, cantidad);
		}
		return resultado;
	}

	private boolean hayAsientos(int asientosLibres, int cantidad) {
		return asientosLibres >= cantidad;
	}
	
	@Override
	public void cerrarSesion(int numSala, int numSesion){
		for (Sala s: salaRepository.list()){
			if(s.getNumSala() == numSala && s.getNumSesion() == numSesion){
				s.setCerrado(true);
				salaRepository.update(s);
			}
		}
	}
	
	@Override
	public void abrirSesion(int numSala, int numSesion){
		for (Sala s: salaRepository.list()){
			if(s.getNumSala() == numSala && s.getNumSesion() == numSesion){
				s.setCerrado(false);
				salaRepository.update(s);
			}
		}
	}

	@Override
	public void cerrarCine() {
		for (Sala s : salaRepository.list()){
			s.setCerrado(true);
		}
	}

	@Override
	public void abrirCine() {
		for (Sala s : salaRepository.list()){
			s.setCerrado(false);
		}
	}
	
	@Override
	public boolean esCerradoSesion(Sala sala) {
		return sala.isCerrado();
	}
	
	@Override
	public Collection<Sala> getSalas() {
		return salaRepository.list();
	}
	
	@Override
	public Sala nuevaSala(Sala sala) {
		return salaRepository.add(sala);
	}
	
	@Override
	public void vaciarSalas() {
		for (Sala s : salaRepository.list()){
			salaRepository.delete(s);
		}
	}
}