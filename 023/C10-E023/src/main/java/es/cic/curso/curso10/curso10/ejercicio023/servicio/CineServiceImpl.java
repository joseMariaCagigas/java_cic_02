package es.cic.curso.curso10.curso10.ejercicio023.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.Pelicula;
import es.cic.curso.curso10.curso10.ejercicio023.repositorio.*;

@Service
public class CineServiceImpl implements CineService
{
	@Autowired
	PeliculaRepository peliculaRepository;

	@Override
	public String dameMensaje()
	{
		return "Has pasado por aquí";
	}
	
	@Override
	public List<Pelicula> getPeliculas() {
		return peliculaRepository.list();
	}

	@Override
	public void nuevaPelicula(Pelicula nueva){
		peliculaRepository.add(nueva);
	}
	
	@Override
	public void editaPelicula(Long id, Pelicula pelicula){
		Pelicula modificado = peliculaRepository.read(id);
		modificado.setTitulo(pelicula.getTitulo());
		modificado.setDirector(pelicula.getDirector());
		modificado.setDuracion(pelicula.getDuracion());
		peliculaRepository.update(modificado);
	}

	@Override
	public void borraPelicula(Long id){
		peliculaRepository.delete(id);
	}
}
