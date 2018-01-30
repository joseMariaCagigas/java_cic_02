package es.cic.curso.grupo2.ejercicio024.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import es.cic.curso.grupo2.ejercicio024.dominio.Pelicula;
import es.cic.curso.grupo2.ejercicio024.repositorio.*;

@Transactional
@Service
public class PeliculaServiceImpl implements PeliculaService
{
	@Autowired
	PeliculaRepository peliculaRepository;

	@Override
	public List<Pelicula> getPeliculas() {
		return peliculaRepository.list();
	}
	
	@Override
	public Pelicula leePelicula(Long id){
		return peliculaRepository.read(id);
	}
	
	@Override
	public void nuevaPelicula(Pelicula nueva){
		peliculaRepository.add(nueva);
	}
	
	@Override
	public void editaPelicula(Long id, Pelicula pelicula){
		Pelicula modificado = peliculaRepository.read(id);
		modificado.setTitulo(pelicula.getTitulo());
		peliculaRepository.update(modificado);
	}

	@Override
	public void borraPelicula(Long id){
		peliculaRepository.delete(id);
	}
}
