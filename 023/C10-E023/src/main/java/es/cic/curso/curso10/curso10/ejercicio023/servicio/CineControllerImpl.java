package es.cic.curso.curso10.curso10.ejercicio023.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.Pelicula;

@Controller
public class CineControllerImpl implements CineController
{
	@Autowired 
	private CineService cineService;

	@Override
	public String dameMensaje()
	{
		return cineService.dameMensaje();
	}
	@Override
	public List<Pelicula> getPeliculas() {
		return cineService.getPeliculas();
	}
	@Override
	public void nuevaPelicula(Pelicula nueva) {
		cineService.nuevaPelicula(nueva);
	}
	@Override
	public void editaPelicula(Long id, Pelicula pelicula) {
		cineService.editaPelicula(id, pelicula);	
	}
	@Override
	public void borraPelicula(Long id) {
		cineService.borraPelicula(id);	
	}
	
	//GETTERS Y SETTERS (REPOSITORY)
	public CineService getCineService() {
		return (CineService) cineService;
	}
	public void setCineRepository(CineService getCineService) {
		this.cineService = getCineService;
	}
}
