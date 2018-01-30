package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.producto.ProductoRepository;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService#crearProducto(java.lang.String, double, int, boolean, boolean, es.cic.curso06.stillUseful.dominio.producto.Categoria, es.cic.curso06.stillUseful.dominio.producto.Estado, es.cic.curso06.stillUseful.dominio.user.Usuario)
	 */
	@Override
	public Producto crearProducto(String nombre, double precioInicial, int cantidad, boolean reservado,
			boolean vendido, Categoria categoriaId, Estado estadoId, Usuario usuarioId){
		
		Producto nuevoProducto = new Producto();
		
		nuevoProducto.setNombre(nombre);
		nuevoProducto.setPrecioInicial(precioInicial);
		nuevoProducto.setCantidad(cantidad);
		nuevoProducto.setReservado(reservado);
		nuevoProducto.setVendido(vendido);
		nuevoProducto.setCategoriaId(categoriaId);
		nuevoProducto.setEstadoId(estadoId);
		nuevoProducto.setUsuarioId(usuarioId);
		
		productoRepository.add(nuevoProducto);
		
		return nuevoProducto;
		
	}


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService#editarProducto(long, java.lang.String, double, int, boolean, boolean, es.cic.curso06.stillUseful.dominio.producto.Categoria, es.cic.curso06.stillUseful.dominio.producto.Estado, es.cic.curso06.stillUseful.dominio.user.Usuario)
	 */
	@Override
	public boolean editarProducto(long productoid, String nombre, double precioInicial, int cantidad, boolean reservado,
			boolean vendido, Categoria categoriaId, Estado estadoId, Usuario usuarioId){
		
		Producto editaProducto;
		
		boolean editado = false;
		
		for (Producto i : productoRepository.list()){
			if(i.getId() == productoid){
				editaProducto = i;
				
				editaProducto.setNombre(nombre);
				editaProducto.setPrecioInicial(precioInicial);
				editaProducto.setCantidad(cantidad);
				editaProducto.setReservado(reservado);
				editaProducto.setVendido(vendido);
				editaProducto.setCategoriaId(categoriaId);
				editaProducto.setEstadoId(estadoId);
				editaProducto.setUsuarioId(usuarioId);
				
				productoRepository.update(editaProducto);
				editado = true;
				
			}
		}return editado;
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService#borrarProducto(long)
	 */
	@Override
	public boolean borrarProducto(long productoId){
		
		boolean eliminado = false;
		
		for(Producto i : productoRepository.list()){
			if(i.getId() == productoId){
				productoRepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.ProductoService#listarProducto()
	 */
	@Override
	public List<Producto> listarProducto(){
		return productoRepository.list();
	}
}
