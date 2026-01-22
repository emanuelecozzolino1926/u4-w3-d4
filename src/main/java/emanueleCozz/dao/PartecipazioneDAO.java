package emanueleCozz.dao;

import emanueleCozz.entities.Partecipazione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import emanueleCozz.entities.Evento;
import emanueleCozz.entities.StatoPartecipazione;
import java.util.List;


public class PartecipazioneDAO {

    private final EntityManager em;

    public PartecipazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione partecipazione) {
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(partecipazione);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }

    public Partecipazione getById(Long id) {
        return em.find(Partecipazione.class, id);
    }

    public List<Partecipazione> getPartecipazioniDaConfermarePerEvento(Evento evento) {
        return em.createNamedQuery("Partecipazione.getDaConfermarePerEvento", Partecipazione.class)
                .setParameter("evento", evento)
                .setParameter("stato", StatoPartecipazione.DA_CONFERMARE)
                .getResultList();
    }

    public void delete(Long id) {
        Partecipazione partecipazione = getById(id);
        if (partecipazione == null) return;

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(partecipazione);
            t.commit();
        } catch (Exception ex) {
            if (t.isActive()) t.rollback();
            throw ex;
        }
    }
}
