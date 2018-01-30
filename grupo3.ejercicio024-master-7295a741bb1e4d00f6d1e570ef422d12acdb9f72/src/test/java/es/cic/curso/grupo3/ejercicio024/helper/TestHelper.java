package es.cic.curso.grupo3.ejercicio024.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;

@Repository
public class TestHelper {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long generaVentaSesionUno(Sala sala) {
		Venta v = new Venta();
		v.setSalaId(sala);
		v.setPrecio(5.0);
		v.setCantidad(10);
		v.setBeneficio(50);
		
		em.persist(v);
		return v.getId();
	}
	
	public Long generaVentaSesionDos(Sala sala) {
		Venta v = new Venta();
		v.setSalaId(sala);
		v.setPrecio(5.0);
		v.setCantidad(10);
		v.setBeneficio(50);
		
		em.persist(v);
		return v.getId();
	}
	
	public Long generaSalaUnoSesionUno(){
		Sala s = new Sala(1, 1, false);
		
		em.persist(s);
		return s.getId();
	}
	
	public Long generaSalaDosSesionDos(){
		Sala s = new Sala(2, 2, false);
		
		em.persist(s);
		return s.getId();
	}
	
	public Long generaSalaTresSesionTres(){
		Sala s = new Sala(3, 3, false);
		
		em.persist(s);
		return s.getId();
	}
}