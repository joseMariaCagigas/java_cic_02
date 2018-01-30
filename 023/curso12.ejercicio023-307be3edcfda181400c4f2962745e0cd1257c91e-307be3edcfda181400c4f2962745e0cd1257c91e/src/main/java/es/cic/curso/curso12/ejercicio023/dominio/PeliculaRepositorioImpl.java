package es.cic.curso.curso12.ejercicio023.dominio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PeliculaRepositorioImpl extends AbstractRepositoryImpl<Long, Pelicula> implements PeliculaRepository{

	@Override
	public Class<Pelicula> getClassDeT() {
		return Pelicula.class;
	}

	@Override
	public String getNombreTabla() {
		return "PELICULA";
	}

}
