package emanueleCozz.dao;

import emanueleCozz.entities.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonaDAO {

    private final EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Persona persona) {
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(persona);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }

    public Persona getById(Long id) {
        return em.find(Persona.class, id);
    }

    public void delete(Long id) {
        Persona persona = getById(id);
        if (persona == null) return;

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(persona);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }
}
