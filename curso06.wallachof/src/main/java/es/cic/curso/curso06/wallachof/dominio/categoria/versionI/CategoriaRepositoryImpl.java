package es.cic.curso.curso06.wallachof.dominio.categoria.versionI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImpl;

@Repository
@Transactional
public class CategoriaRepositoryImpl extends AbstractRepositoryImpl<Long, Categoria> implements CategoriaRepository {

	@Override
	public Class<Categoria> getClassDeT() {
		return Categoria.class;
	}

	@Override
	public String getNombreTabla() {
		return "categoria";
	}

}
