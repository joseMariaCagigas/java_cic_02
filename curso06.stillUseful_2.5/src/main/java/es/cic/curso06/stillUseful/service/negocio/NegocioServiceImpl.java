package es.cic.curso06.stillUseful.service.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.service.exceptions.ProductoException;
import es.cic.curso06.stillUseful.service.exceptions.ReservadoException;
import es.cic.curso06.stillUseful.service.exceptions.VendidoException;
import es.cic.curso06.stillUseful.service.producto.CategoriaService2;
import es.cic.curso06.stillUseful.service.producto.EstadoService2;
import es.cic.curso06.stillUseful.service.producto.ProductoService2;
import es.cic.curso06.stillUseful.service.user.UsuarioService2;

@Service
public class NegocioServiceImpl {

	@Autowired
	CategoriaService2 categoriaService;
	
	@Autowired
	EstadoService2 estadoService;
	
	@Autowired
	ProductoService2 productoService;
	
	@Autowired
	UsuarioService2 usuarioService;
	
	String vendido = "Este producto ya ha sido vendido";
	String reservado = "Este producto está reservado por el momento";
	
	
	
	public Long vender(Long idProducto, int cantidad) throws ProductoException{
		Producto producto = productoService.obtenerProducto(idProducto);
		
		if(estaReservado(idProducto)){
			throw new ReservadoException(reservado);
		}
		if(!hayProducto(idProducto, cantidad)){
			throw new VendidoException();
		}
		sesion.setAsientosOcupados(sesion.getAsientosOcupados() + numEntradas);
		sesionService.actualizarSesion(sesion);
		Long idSala = sesion.getSala().getId();
		double importe = calcularImporte(numEntradas);
		
		return ventaService.aniadirVenta(idSala, idSesion, numEntradas, importe);
	}


	private boolean hayProducto(Long idProducto, int cantidad) {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean estaReservado(Long idProducto) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
