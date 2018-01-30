package es.cic.curso06.stillUseful.repository.admin;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class AdministradorRepositoryImpl extends AbstractRepositoryImpl<Long, Administrador> implements AdministradorRepository{

	@Override
	public Class<Administrador> getClassDeT() {
		// TODO Auto-generated method stub
		return Administrador.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "ADMINISTRADOR";
	}
	

}
