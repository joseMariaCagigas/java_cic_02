package es.cic.curso.curso06.wallachof.dominio.administrador.versionI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.cic.curso.curso06.wallachof.repositorio.versionI.Identificable;

@Entity
public class Administrador implements Identificable<Long>{

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
	
	@Column(name="calle")
	private String calle;
	
	@Column(name="ciudad")
	private String ciudad;
	
	@Column(name="provincia")
	private String provincia;
	
	@Column(name="comunidad")
	private String comunidad;
	
	@Column(name="cp")
	private int cp;
	
	@Column(name="admin_id")
	private long adminId;
	
	

	public Administrador() {
		super();
	}

	public Administrador(String nick, String nombre, String apellidos, String dni, 
			String calle, String ciudad, String provincia, String comunidad, int cp, long adminId) {
		super();
		this.nick = nick;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.calle = calle;
		this.ciudad  = ciudad;
		this.provincia = provincia;
		this.comunidad = comunidad;
		this.cp = cp;
		this.adminId = adminId;
	}

	public Administrador(Long id, String nick, String nombre, String apellidos, String dni, 
			String calle, String ciudad, String provincia, String comunidad, int cp, long adminId) {
		super();
		this.id = id;
		this.nick = nick;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.calle = calle;
		this.ciudad  = ciudad;
		this.provincia = provincia;
		this.comunidad = comunidad;
		this.cp = cp;
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

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

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (adminId ^ (adminId >>> 32));
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((comunidad == null) ? 0 : comunidad.hashCode());
		result = prime * result + cp;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
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
		Administrador other = (Administrador) obj;
		if (adminId != other.adminId)
			return false;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (comunidad == null) {
			if (other.comunidad != null)
				return false;
		} else if (!comunidad.equals(other.comunidad))
			return false;
		if (cp != other.cp)
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
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", nick=" + nick + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", dni=" + dni + ", calle=" + calle + ", ciudad=" + ciudad + ", provincia=" + provincia
				+ ", comunidad=" + comunidad + ", cp=" + cp + ", adminId=" + adminId + "]";
	}
}
