package es.cic.curso.curso06.wallachof.dominio.usuario.versionI;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso06.wallachof.dominio.usuario.versionI.Usuario;
import es.cic.curso.curso06.wallachof.dominio.usuario.versionI.UsuarioRepository;
import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImplTest;
import es.cic.curso.curso06.wallachof.repositorio.versionI.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"classpath:es/cic/curso/curso06/wallachof/applicationContext.xml"})
public class UsuarioRepositoryTest extends AbstractRepositoryImplTest<Long, Usuario> {

	@Autowired
	private UsuarioRepository sut;



	@Override
	public IRepository<Long, Usuario> getRepository() {
		return sut;
	}

	@Override
	public Usuario getInstanceDeTParaNuevo() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Andrés");
		usuario.setApellidos("Gracia Desgraciado");
		usuario.setDni("20355689D");
		usuario.setCoordenadas("Latitud: 40.721214 - Longitud: -74.005941");
		usuario.setNick("Torrebruno");
		
		return usuario;
	}

	@Override
	public Usuario getInstanceDeTParaLectura() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Andrés");
		usuario.setApellidos("Gracia Desgraciado");
		usuario.setDni("20355689D");
		usuario.setCoordenadas("Latitud: 40.721214 - Longitud: -74.005941");
		usuario.setNick("Torrebruno");
		
		return usuario;
	}

	@Override
	public boolean sonDatosIguales(Usuario t1, Usuario t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}
		if (t1.getDni() != t2.getDni()) {
			return false;
		}
		
		if (t1.getNick() != t2.getNick()) {
			return false;
		}

		return true;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Usuario getInstanceDeTParaModificar(Long clave) {
		Usuario usuario = getInstanceDeTParaLectura();
		usuario.setId(clave);
		usuario.setNombre("David");
		return usuario;
	}

	
}
