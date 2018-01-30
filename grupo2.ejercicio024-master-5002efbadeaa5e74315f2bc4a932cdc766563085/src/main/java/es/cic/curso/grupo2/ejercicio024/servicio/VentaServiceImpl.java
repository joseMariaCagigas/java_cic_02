package es.cic.curso.grupo2.ejercicio024.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import es.cic.curso.grupo2.ejercicio024.dominio.Venta;
import es.cic.curso.grupo2.ejercicio024.repositorio.*;

@Transactional
@Service
public class VentaServiceImpl implements VentaService
{
	@Autowired
	VentaRepository ventaRepository;
	
	@Override
	public List<Venta> getVentas() {
		return ventaRepository.list();
	}

	@Override
	public Venta leeVenta(Long id){
		return ventaRepository.read(id);
	}
	
	@Override
	public Venta nuevaVenta(Venta nueva){
		return ventaRepository.add(nueva);
	}
	
	@Override
	public void editaVenta(Long id, Venta venta){
		Venta modificado = ventaRepository.read(id);
		modificado.setNumeroEntradas(venta.getNumeroEntradas());
		modificado.setSesionEntrada(venta.getSesionEntrada());
		modificado.setImporte(venta.getImporte());
		ventaRepository.update(modificado);
	}

	@Override
	public void borraVenta(Long id){
		ventaRepository.delete(id);
	}

}
