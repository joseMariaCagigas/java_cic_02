package es.cic.curso.pruebas.cine.service.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sala.SalaRepository;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;
import es.cic.curso.pruebas.cine.repository.sesion.SesionRepository;
import es.cic.curso.pruebas.cine.repository.venta.Venta;
import es.cic.curso.pruebas.cine.repository.venta.VentaRepository;

@Service
public class VentasServiceImpl implements VentasService{

	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private VentaRepository ventaRepository;
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.ventas.VentasService#aniadirVenta(java.lang.Long, java.lang.Long, int, double)
	 */
	@Override
	public Long aniadirVenta(Long idSala, Long idSesion, int numEntradas, double importe) {
		
		Venta nuevaVenta = new Venta();
		
		Sala sala = salaRepository.read(idSala);
		nuevaVenta.setSala(sala);
		
		Sesion sesion = sesionRepository.read(idSesion);
		nuevaVenta.setSesion(sesion);
		
		nuevaVenta.setNumEntradas(numEntradas);
		nuevaVenta.setImporte(importe);
		Venta aniadida = aniadirVenta(nuevaVenta);
		
		return aniadida.getId();
	}

	private Venta aniadirVenta(Venta nuevaVenta) {
		return ventaRepository.add(nuevaVenta);
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.ventas.VentasService#obtenerVenta(java.lang.Long)
	 */
	@Override
	public Venta obtenerVenta(Long id) {
		
		return ventaRepository.read(id);
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.ventas.VentasService#obtenerVentas()
	 */
	@Override
	public List<Venta> obtenerVentas(){
		return ventaRepository.list();
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.ventas.VentasService#actualizarVenta(es.cic.curso.pruebas.cine.repository.venta.Venta)
	 */
	@Override
	public Venta actualizarVenta(Venta ventaModificada) {
		
		return ventaRepository.update(ventaModificada);
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.ventas.VentasService#borrarVenta(java.lang.Long)
	 */
	@Override
	public void borrarVenta(Long id) {
		
		Venta ventaBorrable = obtenerVenta(id);
		ventaRepository.delete(ventaBorrable);
	}
	
	
	
	
}
