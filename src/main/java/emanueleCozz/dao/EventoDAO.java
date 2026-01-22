package emanueleCozz.dao;

import emanueleCozz.entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventoDAO {

    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Evento evento) {
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(evento);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }

    public Evento getById(Long id) {
        return em.find(Evento.class, id);
    }

    public void delete(Long id) {
        Evento evento = getById(id);
        if (evento == null) return;

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(evento);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }
}
