package es.cic.curso.curso18.mascotastarcraft.Sitio;

import es.cic.curso.curso18.mascotastarcraft.dominio.Identificable;
import es.cic.curso.curso18.mascotastarcraft.fortaleza.Base;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mapa implements Identificable<Long> {

    /**
     *
     */
    private static final long serialVersionUID = 8315207157111876609L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMapa;

    @Column(name = "nombreMapa")
    private String nombreMapa;

    @Column(name = "basesMaximo")
    private int basesMaximo;
    
    @Column(name = "jugadoresMaximo")
    private int jugadoresMaximo;
    
    @Column(name = "jugado")
    private boolean jugado;

    @JoinColumn(name = "idBase")
    @ManyToOne(fetch = FetchType.LAZY)
    private Base base;

    public Mapa() {
    }

    public Mapa(String nombreMapa, int basesMaximo, int jugadoresMaximo,boolean jugado, Base base) {
        this.nombreMapa = nombreMapa;
        this.basesMaximo = basesMaximo;
        this.jugadoresMaximo = jugadoresMaximo;
        this.base = base;
        this.jugado=jugado;
    }

    @Override
    public Long getId() {
        return idMapa;
    }

    @Override
    public void setId(Long idMapa) {
        this.idMapa = idMapa;
    }

    public String getNombreMapa() {
        return nombreMapa;
    }

    public void setNombreMapa(String nombreMapa) {
        this.nombreMapa = nombreMapa;
    }

    public int getBasesMaximo() {
        return basesMaximo;
    }

    public void setBasesMaximo(int basesMaximo) {
        this.basesMaximo = basesMaximo;
    }

    public int getJugadoresMaximo() {
        return jugadoresMaximo;
    }

    public void setJugadoresMaximo(int jugadoresMaximo) {
        this.jugadoresMaximo = jugadoresMaximo;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idMapa);
        hash = 41 * hash + Objects.hashCode(this.nombreMapa);
        hash = 41 * hash + this.basesMaximo;
        hash = 41 * hash + this.jugadoresMaximo;
        hash = 41 * hash + (this.jugado ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.base);
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
        final Mapa other = (Mapa) obj;
        if (this.basesMaximo != other.basesMaximo) {
            return false;
        }
        if (this.jugadoresMaximo != other.jugadoresMaximo) {
            return false;
        }
        if (this.jugado != other.jugado) {
            return false;
        }
        if (!Objects.equals(this.nombreMapa, other.nombreMapa)) {
            return false;
        }
        if (!Objects.equals(this.idMapa, other.idMapa)) {
            return false;
        }
        if (!Objects.equals(this.base, other.base)) {
            return false;
        }
        return true;
    }
    
}
