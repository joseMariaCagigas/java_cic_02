package es.cic.curso.curso06.ejercicio014.versionII.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.ejercicio014.versionII.datos.Venta;
import es.cic.curso.curso06.ejercicio014.versionII.datos.Producto;

import es.cic.curso.curso06.ejercicio014.versionII.repositorios.ProductoRepository;
import es.cic.curso.curso06.ejercicio014.versionII.repositorios.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired 
	private ProductoRepository productoRepository;

	private Venta badulake;

	public VentaServiceImpl(){
		setBadulake(new Venta());
	}
	
	public List<Producto> leeInventarioProductos(){
		List<Producto> lista = productoRepository.list();
		for(Producto p : lista){
			if(!compruebaExistencia(p)){
				productoRepository.add(p);
			}
		}
		return lista;
	}



	private boolean compruebaExistencia(Producto p){
		List<Producto> lista = productoRepository.list();
		for(Producto prod : lista){
			if(prod.getCantidadStock() ==p.getCantidadStock() && prod.getCode().equalsIgnoreCase(p.getCode())){
				int cantidad = prod.getCantidadStock()+p.getCantidadStock();
				prod.setCantidadTienda(cantidad);
				productoRepository.update(prod);
			}
		}
		return false;
	}

	private int vendeProdTienda(Producto p,int numero){
		int restantes = 0;
		List<Producto> lista = new ArrayList<>();

		lista = productoRepository.list();
		for(Producto pd : lista){
			if(p.getCode().equalsIgnoreCase(pd.getCode())){
				restantes = pd.getCantidadTienda() - numero;
				pd.setCantidadTienda(restantes);
				productoRepository.update(pd);
			}
		}
		Venta v = new Venta();
		v.setId_producto(p.getId());
		v.setCantidad(numero);
		v.setImporte(p.getPrecio() * numero);
		ventaRepository.add(v);
		return restantes;
	}


	private boolean hayExistencias(Producto p,int peticion){
		List<Producto> lista = productoRepository.list();
		int existencias = 0;
		for(Producto pd : lista){
			if(p.getCode().equalsIgnoreCase(pd.getCode()))
				existencias += pd.getCantidadStock();
		}
		existencias -= peticion;
		if(existencias>0)
			return true;
		return false;
	}
	private int calculaDisponibles(Producto p){
		List<Producto> lista = productoRepository.list();
		int existencias = 0;
		for(Producto pd : lista){
			if(p.getCode().equalsIgnoreCase(pd.getCode()))
				existencias += pd.getCantidadStock();
		}
		return existencias;

	}

	public VentaRepository getVentaRepository() {
		return ventaRepository;
	}

	public void setVentaRepository(VentaRepository ventaRepository) {
		this.ventaRepository = ventaRepository;
	}

	public ProductoRepository getProductoRepository() {
		return productoRepository;
	}

	public void setProductoRepository(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public Venta getBadulake() {
		return badulake;
	}

	public void setBadulake(Venta badulake) {
		this.badulake = badulake;
	}

	@Override
	public void vendeProd(List<Producto> p, List<Integer> numero) {
		// TODO Auto-generated method stub
		
	}
	

	
}
