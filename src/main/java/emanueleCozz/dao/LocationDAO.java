package emanueleCozz.dao;

import emanueleCozz.entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LocationDAO {

    private final EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Location location) {
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(location);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }

    public Location getById(Long id) {
        return em.find(Location.class, id);
    }

    public void delete(Long id) {
        Location location = getById(id);
        if (location == null) return;

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(location);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }
}
