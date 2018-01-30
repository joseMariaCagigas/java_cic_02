package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.wallachof.dominio.categoria.versionI.Categoria;
import es.cic.curso.curso06.wallachof.dominio.categoria.versionI.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Long aniadirCategoria(String nombre) {
		Categoria categoria = new Categoria();
		
		categoria.setNombre(nombre);
		Categoria aniadido = aniadirCategoria(categoria);
		
		return aniadido.getId();
	}


	private Categoria aniadirCategoria(Categoria nuevo) {
		categoriaRepository.add(nuevo);
		entityManager.flush();
		
		return nuevo;
	}
	
	@Override
	public Categoria obtenerCategoria(Long id){
		return categoriaRepository.read(id);
	}

	@Override
	public List<Categoria> obtenerCategoria(){
		return categoriaRepository.list();
	}

	@Override
	public Categoria actualizarCategoria(Categoria modificado){
		return categoriaRepository.update(modificado);
	}


	@Override
	public void borrarCategoria(Long id) {
		Categoria aBorrar = obtenerCategoria(id);
		categoriaRepository.delete(aBorrar);
		
	}






}
