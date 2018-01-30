package es.cic.curso06.stillUseful.dominio.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.cic.curso06.stillUseful.repository.abstracto.Identificable;


@Entity
public class User implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5645237745520003396L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="USUARIO_ID")
	@OneToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@Column(name="NICK")
	private String nick;
	
	@Column(name="PASSWORD")
	private String password;

	public User(Long id, Usuario usuario, String nick, String password) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nick = nick;
		this.password = password;
	}

	public User(Usuario usuario, String nick, String password) {
		super();
		this.usuario = usuario;
		this.nick = nick;
		this.password = password;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
	}

	public User(String nick, String password) {
		super();
		this.nick = nick;
		this.password = password;
	}

	public User(Long id, String nick, String password) {
		super();
		this.id = id;
		this.nick = nick;
		this.password = password;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usuario=" + usuario.getId() + ", nick=" + nick + ", password=" + password
				+ "]";
	}
}
