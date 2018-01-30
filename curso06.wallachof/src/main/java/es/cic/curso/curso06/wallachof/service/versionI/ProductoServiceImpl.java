package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.wallachof.dominio.producto.versionI.Producto;
import es.cic.curso.curso06.wallachof.dominio.producto.versionI.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Long aniadirProducto(String nombre, double precio, int cantidad, boolean estado) {
		Producto producto = new Producto();
		
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setCantidad(cantidad);
		producto.setEstado(estado);
		
		Producto aniadido = aniadirProducto(producto);
		
		return aniadido.getId();
	}
	

	@Override
	public Long aniadirProductoConCategoria(String nombre, double precio, int cantidad, boolean estado,
			long categoriaId) {
		
		Producto producto = new Producto();
		
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setCantidad(cantidad);
		producto.setEstado(estado);
		producto.setCategoriaId(categoriaId);
		
		Producto aniadido = aniadirProducto(producto);
		
		return aniadido.getId();
	}


	private Producto aniadirProducto(Producto nuevo) {
		productoRepository.add(nuevo);
		entityManager.flush();
		
		return nuevo;
	}
	
	@Override
	public Producto obtenerProducto(Long id){
		return productoRepository.read(id);
	}

	@Override
	public List<Producto> obtenerProductos(){
		return productoRepository.list();
	}

	@Override
	public Producto actualizarProducto(Producto modificado){
		return productoRepository.update(modificado);
	}


	@Override
	public void borrarProducto(Long id) {
		Producto aBorrar = obtenerProducto(id);
		productoRepository.delete(aBorrar);
		
	}





}
