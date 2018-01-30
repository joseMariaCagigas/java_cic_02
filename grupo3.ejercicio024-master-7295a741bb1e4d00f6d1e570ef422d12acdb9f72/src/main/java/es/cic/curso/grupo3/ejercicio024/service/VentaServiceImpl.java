package es.cic.curso.grupo3.ejercicio024.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;
import es.cic.curso.grupo3.ejercicio024.repository.VentaRepository;

@Service
@Transactional
public class VentaServiceImpl implements VentaService {
	
	private static final int PRECIO = 5;
	@Autowired 
	private VentaRepository ventaRepository;
	@Autowired 
	private SalaRepository salaRepository;
	@Autowired 
	private SalaService salaService;
	
	public void anadirAsientosLibres(Sala sala, int cantidad){
		sala.setAsientosLibres(sala.getAsientosLibres() - cantidad);
		salaRepository.update(sala);
	}
	
	@Override
	public double obtenerRecaudacionSala(int numSala){
		double recaudacion = 0;
		
		for (Venta v : ventaRepository.list()){
			if (v.getSalaId().getNumSala() == numSala){
			recaudacion += v.getBeneficio();
			}
		}
		return recaudacion;
	}
	
	@Override
	public double obtenerRecaudacionCine(){
		double recaudacion = 0;
		
		for (Venta v : ventaRepository.list()){
			recaudacion += v.getBeneficio();
		}
		return recaudacion;
	}
	
	@Override
	public void venderEntrada(Sala sala, int cantidad){
		Venta venta = new Venta();

		if(salaService.hayAsientosLibres(sala, cantidad)){
			if (cantidad >= 5){
				anadirAsientosLibres(sala, cantidad);
				venta.setSalaId(sala);
				venta.setCantidad(cantidad);
				venta.setPrecio(PRECIO);
				venta.setBeneficio(cantidad * PRECIO * 0.9);
				ventaRepository.add(venta);
			} else {
				anadirAsientosLibres(sala, cantidad);
				venta.setSalaId(sala);
				venta.setCantidad(cantidad);
				venta.setPrecio(PRECIO);
				venta.setBeneficio((double)cantidad * PRECIO);
				ventaRepository.add(venta);
			}
		}
	}
	
	@Override
	public void borrarVenta(Venta venta){
		anadirAsientosLibres(venta.getSalaId(), -venta.getCantidad());
		
		ventaRepository.delete(venta.getId());
	}
	
	@Override
	public void borrarVenta(Long id){
		Venta venta = ventaRepository.read(id);
		anadirAsientosLibres(venta.getSalaId(), -venta.getCantidad());
		
		ventaRepository.delete(id);
	}
	
	@Override
	public Collection<Venta> getVentas() {
		return ventaRepository.list();
	}
	
	@Override
	public void vaciarVentas() {
		for (Venta v : ventaRepository.list()){
			ventaRepository.delete(v);
		}
	}
}