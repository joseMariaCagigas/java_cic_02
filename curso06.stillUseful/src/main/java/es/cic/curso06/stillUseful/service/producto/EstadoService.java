package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;

public interface EstadoService {

	Estado crearEstado(Producto producto, String nombre, String descripcion);

	boolean editarEstado(long estadoId, Producto producto, String nombre, String descripcion);

	boolean borrarEstado(long estadoId);

	List<Estado> listarEstado();

}