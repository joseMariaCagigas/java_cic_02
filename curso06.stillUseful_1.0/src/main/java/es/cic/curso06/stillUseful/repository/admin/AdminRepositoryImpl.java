package es.cic.curso06.stillUseful.repository.admin;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class AdminRepositoryImpl extends AbstractRepositoryImpl<Long, Admin> implements AdminRepository{

	@Override
	public Class<Admin> getClassDeT() {
		// TODO Auto-generated method stub
		return Admin.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "ADMIN";
	}
	

}
