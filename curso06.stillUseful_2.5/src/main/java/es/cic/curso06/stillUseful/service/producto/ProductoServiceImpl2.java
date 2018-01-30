package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;
import es.cic.curso06.stillUseful.repository.producto.ProductoRepository;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;
@Service
public class ProductoServiceImpl2 implements ProductoService2 {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService2#aniadirProducto(java.lang.String, double, int, boolean, boolean, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public Long aniadirProducto(String nombre, double precioInicial, int cantidad, boolean reservado, boolean vendido,
			Long categoriaId, Long estadoId, Long usuarioId) {
		
		Producto nuevaProducto = new Producto();
		
		nuevaProducto.setNombre(nombre);
		nuevaProducto.setPrecioInicial(precioInicial);
		nuevaProducto.setCantidad(cantidad);
		nuevaProducto.setReservado(false);
		nuevaProducto.setVendido(false);
		
		Categoria categoria = categoriaRepository.read(categoriaId);
		nuevaProducto.setCategoriaId(categoria);
		
		Estado estado = estadoRepository.read(estadoId);
		nuevaProducto.setEstadoId(estado);
		
		Usuario usuario = usuarioRepository.read(usuarioId);
		nuevaProducto.setUsuarioId(usuario);
		
		Producto aniadida = aniadirProducto(nuevaProducto);
		
		return aniadida.getId();
	}

	private Producto aniadirProducto(Producto nuevaProducto) {
		return productoRepository.add(nuevaProducto);
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService2#obtenerProducto(java.lang.Long)
	 */
	@Override
	public Producto obtenerProducto(Long id) {
		
		return productoRepository.read(id);
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService2#obtenerProductos()
	 */
	@Override
	public List<Producto> obtenerProductos(){
		return productoRepository.list();
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService2#actualizarProducto(es.cic.curso06.stillUseful.dominio.producto.Producto)
	 */
	@Override
	public Producto actualizarProducto(Producto productoModificada) {
		
		return productoRepository.update(productoModificada);
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService2#borrarVenta(java.lang.Long)
	 */
	@Override
	public void borrarProducto(Long id) {
		
		Producto productoBorrable = obtenerProducto(id);
		productoRepository.delete(productoBorrable);
	}
	
	
	
	
}
