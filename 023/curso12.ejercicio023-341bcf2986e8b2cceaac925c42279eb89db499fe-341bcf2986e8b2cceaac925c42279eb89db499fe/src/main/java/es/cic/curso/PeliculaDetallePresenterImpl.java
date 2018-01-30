package es.cic.curso;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.web.context.ContextLoader;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;
import es.cic.curso.curso12.ejercicio023.servicio.PeliculaService;

public class PeliculaDetallePresenterImpl implements PeliculaDetallePresenter {

	public static final Logger log = Logger.getLogger(PeliculaDetallePresenterImpl.class);
	
	private PeliculaDetalleVista vista;
	
	private PeliculaService peliculaService;
	
	private List<PeliculaListener> listaPeliculaLister;

	public PeliculaDetallePresenterImpl() {
		listaPeliculaLister = new ArrayList<>();
	}

	@Override
	public void crearActualizar() {
		Pelicula pelicula = vista.getPelicula();
		
		try {
			peliculaService.actualizarPelicula(pelicula);
			firePeliculaEvent(new PeliculaEvent(pelicula, TipoEventoPelicula.CREAR_ACTUALIZAR));
		} catch (RuntimeException re) {
			String mensaje = "Se ha producido un error al actualizar/crear " + re.getMessage();
			log.error(mensaje);
			re.printStackTrace();
			vista.setError(mensaje);
		}
	}
	
	@Override
	public void borrar() {
		Pelicula pelicula = vista.getPelicula();
		
		try {
			peliculaService.borrarPelicula(pelicula.getId());
			firePeliculaEvent(new PeliculaEvent(pelicula, TipoEventoPelicula.BORRAR));
		} catch (RuntimeException re) {
			String mensaje = "Se ha producido un error al borrar " + re.getMessage();
			log.error(mensaje);
			re.printStackTrace();
			vista.setError(mensaje);
		}
	}	

	protected void firePeliculaEvent(PeliculaEvent peliculaEvent) {
		for (PeliculaListener pl : listaPeliculaLister) {
			pl.onPeliculaEvent(peliculaEvent);
		}
	}
	
	public void addPeliculaLister(PeliculaListener peliculaListener) {
		listaPeliculaLister.add(peliculaListener);
	}
	
	public void removePeliculaLister(PeliculaListener peliculaListener) {
		listaPeliculaLister.remove(peliculaListener);
	}

	public void setVista(PeliculaDetalleVista vista) {
		this.vista = vista;
	}

	
	/* (non-Javadoc)
	 * @see es.cic.curso.PeliculaDetallePresenter#getVista()
	 */
	@Override
	public PeliculaDetalleVista getVista() {
		return vista;
	}
	
	public PeliculaService getPeliculaService() {
		return peliculaService;
	}

	public void setPeliculaService(PeliculaService peliculaService) {
		this.peliculaService = peliculaService;
	}	
}
