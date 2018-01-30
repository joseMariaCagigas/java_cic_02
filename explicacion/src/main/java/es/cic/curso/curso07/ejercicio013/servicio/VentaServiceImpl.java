package es.cic.curso.curso07.ejercicio013.servicio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso07.ejercicio013.dominio.Sala;
import es.cic.curso.curso07.ejercicio013.dominio.SalaRepository;
import es.cic.curso.curso07.ejercicio013.dominio.Venta;
import es.cic.curso.curso07.ejercicio013.dominio.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired 
	private SalaRepository salaRepository;
	

	@Override
	public boolean hayButacasLibres(long salaId, long sesionId){
		return hayButacasLibres(1, salaId, sesionId);
	}
	
	@Override
	public boolean hayButacasLibres(int cuantas, long salaId, long sesionId){
		Sala salaActual = salaRepository.read(salaId);		
		
		int asientosOcupados = getOcupadas(sesionId, salaActual);
		int asientosTotal = salaActual.getCapacidad();
		
		return asientosOcupados + cuantas <= asientosTotal;
	}
	
	private int getOcupadas(long sesionId, Sala salaActual) {
		switch ((int)sesionId) {
			case 1: return salaActual.getOcupadasSesion1();
			case 2: return salaActual.getOcupadasSesion2();
			case 3:	return salaActual.getOcupadasSesion3();
			default: throw new VentaException("No existe la sesion: " + sesionId);	
		}
	}
	
	private void setOcupadas(long sesionId, int ocupadas, Sala salaActual) {
		switch ((int)sesionId) {
			case 1: salaActual.setOcupadasSesion1(ocupadas);
					return;
			case 2: salaActual.setOcupadasSesion2(ocupadas);
					return; 
			case 3:	salaActual.setOcupadasSesion3(ocupadas);
					return;
			default: throw new VentaException("No existe la sesion: " + sesionId);	
		}
	}	
	
	@Override
	public double calcularPrecio(int numeroEntradas) {
		return numeroEntradas * PRECIO;
	}
	

	
	
	@Override
	public double comprarEntradas(long salaId, long sesionId, short cantidad){
		Sala salaActual = salaRepository.read(salaId);		
		if(!hayButacasLibres(cantidad, salaId, sesionId)){
			String mensaje = String.format("No existen en la salaId: %d, sesionId: %d, %d entradas disponibles",
											salaId, sesionId, cantidad);
			
			throw new VentaException(mensaje);

		}	

		int asientosOcupados = getOcupadas(sesionId, salaActual);
		int cuantosNuevosAsientosOcupados = asientosOcupados + cantidad;

		setOcupadas(sesionId, cuantosNuevosAsientosOcupados, salaActual);
		
		double precio = calcularPrecio(cantidad);
		
		boolean descuento = false;
		Venta venta = new Venta(cantidad, descuento, salaId, sesionId, precio);

		ventaRepository.add(venta);
		
		salaRepository.update(salaActual);		
		
		return precio;
	}

	@Override
	public double calcularRecaudacion(){
		List<Sala> listaSala = salaRepository.list();
		
//		double total = 0.0;
//		for(Sala salaActual: listaSala) {
//			total += recaudacionSala(salaActual);
//		}
		double total = listaSala.stream()
				.mapToDouble(s -> recaudacionSala(s))
				.sum();
		
		
		return total;
	}
	
	@Override
	public double calcularRecaudacionSala(long salaId){
		Sala salaActual = salaRepository.read(salaId);
		return recaudacionSala(salaActual);
	}
	
	private double recaudacionSala(Sala salaActual){	
		int totalButacas = 
				salaActual.getOcupadasSesion1() + 
				salaActual.getOcupadasSesion2() +
				salaActual.getOcupadasSesion3();
		
		return calcularPrecio(totalButacas);
	}	
}
