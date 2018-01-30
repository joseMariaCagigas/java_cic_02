package es.cic.curso06.stillUseful.testHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.dominio.user.User;
import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.admin.AdminRepository;
import es.cic.curso06.stillUseful.repository.admin.AdministradorRepository;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;
import es.cic.curso06.stillUseful.repository.producto.ProductoRepository;
import es.cic.curso06.stillUseful.repository.user.UserRepository;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;


@Repository
public class TestHelper {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdministradorRepository administradorRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ProductoRepository productoRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public Long generaAdmin() {
		Admin admin = new Admin();
		
		admin.setNick("Trini");
		admin.setPassword("12ad123f456afd123a");
		
		em.persist(admin);
		
		return admin.getId();
	}
	
	public Long generaUser() {
		User user = new User();
		
		user.setNick("Trini");
		user.setPassword("12ad123f456afd123a");
		
		em.persist(user);
		
		return user.getId();
	}
	
	public Long generaAdministrador() {
		Administrador administrador = new Administrador();
		
		administrador.setNombre("Manuel Trinidad");
		administrador.setApellidos("Hacha Ventilador");
		administrador.setDni("20356358F");
		administrador.setCalle("Villa Porrosa s/n");
		administrador.setCiudad("Santander");
		administrador.setProvincia("Cantabria");
		administrador.setComunidad("Cantabria");
		administrador.setCodigoPostal(39012);
		administrador.setEmail("allendelasguas@gmail.com");
		
		em.persist(administrador);
		
		return administrador.getId();
	}
	
	public Long generaUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Manuel Trinidad");
		usuario.setApellidos("Hacha Ventilador");
		usuario.setDni("20356358F");
		usuario.setCalle("Villa Porrosa s/n");
		usuario.setCiudad("Santander");
		usuario.setProvincia("Cantabria");
		usuario.setComunidad("Cantabria");
		usuario.setCodigoPostal(39012);
		usuario.setEmail("allendelasguas@gmail.com");
		
		em.persist(usuario);
		
		return usuario.getId();
	}
	
	public Long generaCategoria(){
		Categoria categoria = new Categoria();
		
		categoria.setNombre("Calzado");
		categoria.setDescripcion("Zapatos Zara 2016");
		
		em.persist(categoria);
		
		return categoria.getId();
	}
	
	public Long generaEstado(){
		Estado estado = new Estado();
		
		estado.setNombre("Usado");
		estado.setDescripcion("Solo puestos para una boda");
		
		em.persist(estado);
		
		return estado.getId();
	}
	
	public Long generaProducto(){
		Producto producto = new Producto();
		
		producto.setNombre("Nike Jordan V 2001");
		producto.setPrecioInicial(90.50);
		producto.setCantidad(1);
		producto.setReservado(false);
		producto.setVendido(false);
		producto.setCategoriaId(categoriaRepository.read(generaCategoria()));
		producto.setEstadoId(estadoRepository.read(generaEstado()));
		producto.setUsuarioId(usuarioRepository.read(generaUsuario()));
		
		
		em.persist(producto);
		
		return producto.getId();
		}
}
