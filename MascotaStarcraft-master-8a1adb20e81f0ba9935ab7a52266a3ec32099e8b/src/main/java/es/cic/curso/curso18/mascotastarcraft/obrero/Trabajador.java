package es.cic.curso.curso18.mascotastarcraft.obrero;

import es.cic.curso.curso18.mascotastarcraft.dominio.Identificable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trabajador implements Identificable<Long> {

    /**
     *
     */
    private static final long serialVersionUID = 8315207157111876609L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTrabajador;

    @Column(name = "tipoTrabajador")
    private String tipoTrabajador;

    public Trabajador(String tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    public Trabajador() {
    }

    @Override
    public Long getId() {
        return this.idTrabajador;
    }

    @Override
    public void setId(Long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getTipoTrabajador() {
        return tipoTrabajador;
    }

    public void setTipoTrabajador(String tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idTrabajador);
        hash = 89 * hash + Objects.hashCode(this.tipoTrabajador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trabajador other = (Trabajador) obj;
        if (!Objects.equals(this.tipoTrabajador, other.tipoTrabajador)) {
            return false;
        }
        if (!Objects.equals(this.idTrabajador, other.idTrabajador)) {
            return false;
        }
        return true;
    }

}
