package es.cic.curso06.stillUseful.repository.producto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class CategoriaRepositoryImpl extends AbstractRepositoryImpl<Long, Categoria> implements CategoriaRepository{

	@Override
	public Class<Categoria> getClassDeT() {
		// TODO Auto-generated method stub
		return Categoria.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "CATEGORIA";
	}

	

}
