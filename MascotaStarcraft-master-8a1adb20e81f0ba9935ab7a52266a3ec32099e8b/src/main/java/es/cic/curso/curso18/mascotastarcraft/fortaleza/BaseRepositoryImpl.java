package es.cic.curso.curso18.mascotastarcraft.fortaleza;

import es.cic.curso.curso18.mascotastarcraft.dominio.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseRepositoryImpl extends AbstractRepositoryImpl<Long, Base> implements BaseRepository {

    @Override
    public Class<Base> getClassDeT() {
        return Base.class;
    }

    @Override
    public String getNombreTabla() {
        return "Base";
    }
}
