package emanueleCozz;

import emanueleCozz.dao.*;
import emanueleCozz.entities.*;
import emanueleCozz.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        LocationDAO locationDAO = new LocationDAO(em);
        EventoDAO eventoDAO = new EventoDAO(em);
        PersonaDAO personaDAO = new PersonaDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

        Location location = new Location("Teatro Centrale", "Milano");
        locationDAO.save(location);

        Evento evento = new Evento(
                "Serata Java",
                LocalDate.now().plusDays(5),
                "Evento di prova con relazioni",
                TipoEvento.PUBBLICO,
                100
        );
        evento.setLocation(location);
        eventoDAO.save(evento);

        Persona persona = new Persona(
                "Mario",
                "Rossi",
                "mario.rossi@mail.it",
                LocalDate.of(1999, 2, 15),
                Sesso.M
        );
        personaDAO.save(persona);

        Partecipazione partecipazione = new Partecipazione(persona, evento, StatoPartecipazione.CONFERMATA);

        persona.getListaPartecipazioni().add(partecipazione);
        evento.getListaPartecipazioni().add(partecipazione);

        partecipazioneDAO.save(partecipazione);

        System.out.println("LOCATION -> " + location);
        System.out.println("EVENTO -> " + evento);
        System.out.println("PERSONA -> " + persona);
        System.out.println("PARTECIPAZIONE -> " + partecipazione);

        em.close();
        JPAUtil.getEntityManagerFactory().close();
    }
}
