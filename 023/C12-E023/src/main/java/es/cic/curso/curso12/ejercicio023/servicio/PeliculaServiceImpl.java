 package es.cic.curso.curso12.ejercicio023.servicio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;
import es.cic.curso.curso12.ejercicio023.dominio.PeliculaRepositorioImpl;
import es.cic.curso.curso12.ejercicio023.dominio.PeliculaRepository;

@Transactional
@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepo;
	
	/* (non-Javadoc)
	 * @see es.cic.curso.curso12.ejercicio023.servicio.PeliculaService#aniadirPelicula(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public Long aniadirPelicula(String titulo, String director, String productora, String interprete, int year, int duracion, String genero){
		Pelicula peli = new Pelicula(titulo, director, productora, interprete, year, duracion, genero);
		
		Pelicula peliAdd = aniadirPelicula(peli);		
		
		return peliAdd.getId();
	}
	
	@Override
	public Pelicula aniadirPelicula(Pelicula nueva){
		return peliculaRepo.add(nueva);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.curso12.ejercicio023.servicio.PeliculaService#actualizarPelicula(es.cic.curso.curso12.ejercicio023.dominio.Pelicula)
	 */
	@Override
	public Pelicula actualizarPelicula(Pelicula peli){
		return peliculaRepo.update(peli);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.curso12.ejercicio023.servicio.PeliculaService#obtenerPelicula(java.lang.Long)
	 */
	@Override
	public Pelicula obtenerPelicula(Long id){
		return peliculaRepo.read(id);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.curso12.ejercicio023.servicio.PeliculaService#obtenerPeliculas()
	 */
	@Override
	public List<Pelicula> obtenerPeliculas(){
		return peliculaRepo.list();
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.curso12.ejercicio023.servicio.PeliculaService#borrarPelicula(java.lang.Long)
	 */
	@Override
	public void  borrarPelicula(Long id){
		Pelicula aBorrar = obtenerPelicula(id);
		peliculaRepo.delete(aBorrar);
	}
}
