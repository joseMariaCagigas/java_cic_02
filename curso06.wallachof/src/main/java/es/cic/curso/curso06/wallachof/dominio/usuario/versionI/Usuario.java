package es.cic.curso.curso06.wallachof.dominio.usuario.versionI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.cic.curso.curso06.wallachof.repositorio.versionI.Identificable;

@Entity
public class Usuario implements Identificable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -11451728416874955L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="nick")
	private String nick;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="dni")
	private String dni;
	
	@Column(name="coordenadas")
	private String coordenadas;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="producto_id")
	private long productoId;

	public Usuario() {
		super();
	}

	public Usuario(String nick, String nombre, String apellidos, String dni, String coordenadas, long userId, long productoId) {
		super();
		this.nick = nick;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.coordenadas = coordenadas;
		this.userId = userId;
		this.productoId = productoId;
	}

	public Usuario(Long id, String nick, String nombre, String nombreUsuario, String apellidos, String dni, String coordenadas, long userId, long productoId) {
		super();
		this.id = id;
		this.nick = nick;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.coordenadas = coordenadas;
		this.userId = userId;
		this.productoId = productoId;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProductoId() {
		return productoId;
	}

	public void setProductoId(long productoId) {
		this.productoId = productoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((coordenadas == null) ? 0 : coordenadas.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + (int) (productoId ^ (productoId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (coordenadas == null) {
			if (other.coordenadas != null)
				return false;
		} else if (!coordenadas.equals(other.coordenadas))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (productoId != other.productoId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni="
				+ dni + ", coordenadas=" + coordenadas + ", userId=" + userId + ", productoId=" + productoId + "]";
	}
}
