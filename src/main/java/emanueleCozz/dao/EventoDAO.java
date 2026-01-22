package emanueleCozz.dao;

import emanueleCozz.entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import emanueleCozz.entities.*;
import java.util.List;


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

    public List<Concerto> getConcertiInStreaming(boolean inStreaming) {
        return em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :inStreaming", Concerto.class)
                .setParameter("inStreaming", inStreaming)
                .getResultList();
    }

    public List<Concerto> getConcertiPerGenere(GenereConcerto genere) {
        return em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere", Concerto.class)
                .setParameter("genere", genere)
                .getResultList();
    }

    // ESERCIZIO 2 - NamedQuery
    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
        return em.createNamedQuery("PartitaDiCalcio.getPartiteVinteInCasa", PartitaDiCalcio.class)
                .getResultList();
    }

    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
        return em.createNamedQuery("PartitaDiCalcio.getPartiteVinteInTrasferta", PartitaDiCalcio.class)
                .getResultList();
    }

    public List<PartitaDiCalcio> getPartitePareggiate() {
        return em.createNamedQuery("PartitaDiCalcio.getPartitePareggiate", PartitaDiCalcio.class)
                .getResultList();
    }

    public List<GaraDiAtletica> getGareDiAtleticaPerVincitore(Persona vincitore) {
        return em.createNamedQuery("GaraDiAtletica.getGarePerVincitore", GaraDiAtletica.class)
                .setParameter("vincitore", vincitore)
                .getResultList();
    }

    public List<GaraDiAtletica> getGareDiAtleticaPerPartecipante(Persona partecipante) {
        return em.createNamedQuery("GaraDiAtletica.getGarePerPartecipante", GaraDiAtletica.class)
                .setParameter("partecipante", partecipante)
                .getResultList();
    }

    public List<Evento> getEventiSoldOut() {
        return em.createNamedQuery("Evento.getEventiSoldOut", Evento.class)
                .getResultList();
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
