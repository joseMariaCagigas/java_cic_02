package es.cic.curso06.stillUseful.dominio.producto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.abstracto.Identificable;


@Entity
public class Producto implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3269050239855086539L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PRECIO_INICIAL")
	private double precioInicial;
	
	@Column(name="CANTIDAD")
	private int cantidad;

	@Column(name="RESERVADO")
	private boolean reservado;
	
	@Column(name="VENDIDO")
	private boolean vendido;
	
	@JoinColumn(name="CATEGORIA_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Categoria categoriaId;
	
	@JoinColumn(name="ESTADO_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Estado estadoId;
	
	@JoinColumn(name="USUARIO_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuarioId;


	public Producto() {
		super();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioInicial() {
		return precioInicial;
	}

	public void setPrecioInicial(double precioInicial) {
		this.precioInicial = precioInicial;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public Categoria getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Categoria categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Estado getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Estado estadoId) {
		this.estadoId = estadoId;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precioInicial=" + precioInicial + ", cantidad="
				+ cantidad + ", reservado=" + reservado + ", vendido=" + vendido + ", categoriaId=" + categoriaId.getId()
				+ ", estadoId=" + estadoId.getId() + ", usuarioId=" + usuarioId.getId() + "]";
	}

	
	
}