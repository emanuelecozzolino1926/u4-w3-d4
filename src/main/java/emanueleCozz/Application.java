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
        PersonaDAO personaDAO = new PersonaDAO(em);
        EventoDAO eventoDAO = new EventoDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

        Location loc = new Location("Stadio Centrale", "Milano");
        locationDAO.save(loc);

        Persona p1 = new Persona("Mario", "Rossi", "mario.rossi@mail.it", LocalDate.of(1999, 2, 15), Sesso.M);
        Persona p2 = new Persona("Luisa", "Bianchi", "luisa.bianchi@mail.it", LocalDate.of(2000, 6, 10), Sesso.F);
        personaDAO.save(p1);
        personaDAO.save(p2);

        // PARTITA (vinta in casa)
        PartitaDiCalcio partita1 = new PartitaDiCalcio(
                "Derby",
                LocalDate.now().plusDays(3),
                "Partita di prova",
                TipoEvento.PUBBLICO,
                50000,
                "Milan",
                "Inter",
                "Milan",
                2,
                1
        );
        partita1.setLocation(loc);
        eventoDAO.save(partita1);

        // PARTITA (pareggio)
        PartitaDiCalcio partita2 = new PartitaDiCalcio(
                "Amichevole",
                LocalDate.now().plusDays(4),
                "Partita pareggiata",
                TipoEvento.PUBBLICO,
                10000,
                "Roma",
                "Lazio",
                null,
                0,
                0
        );
        partita2.setLocation(loc);
        eventoDAO.save(partita2);

        // GARA DI ATLETICA
        GaraDiAtletica gara = new GaraDiAtletica(
                "100 metri",
                LocalDate.now().plusDays(7),
                "Gara di atletica",
                TipoEvento.PUBBLICO,
                2000
        );
        gara.setLocation(loc);
        gara.getAtleti().add(p1);
        gara.getAtleti().add(p2);
        gara.setVincitore(p2);
        eventoDAO.save(gara);

        // CONCERTO (sold out con max=1)
        Concerto concerto = new Concerto(
                "Rock Night",
                LocalDate.now().plusDays(10),
                "Concerto rock",
                TipoEvento.PUBBLICO,
                1,
                GenereConcerto.ROCK,
                true
        );
        concerto.setLocation(loc);
        eventoDAO.save(concerto);

        // Partecipazioni (una confermata = sold out, una da confermare)
        Partecipazione conf = new Partecipazione(p1, concerto, StatoPartecipazione.CONFERMATA);
        concerto.getListaPartecipazioni().add(conf);
        p1.getListaPartecipazioni().add(conf);
        partecipazioneDAO.save(conf);

        Partecipazione daConf = new Partecipazione(p2, concerto, StatoPartecipazione.DA_CONFERMARE);
        concerto.getListaPartecipazioni().add(daConf);
        p2.getListaPartecipazioni().add(daConf);
        partecipazioneDAO.save(daConf);

        System.out.println("\n--- JPQL (Esercizio 1) ---");
        System.out.println("Concerti in streaming:");
        eventoDAO.getConcertiInStreaming(true).forEach(System.out::println);

        System.out.println("\nConcerti per genere ROCK:");
        eventoDAO.getConcertiPerGenere(GenereConcerto.ROCK).forEach(System.out::println);

        System.out.println("\n--- NamedQuery (Esercizio 2) ---");
        System.out.println("Partite vinte in casa:");
        eventoDAO.getPartiteVinteInCasa().forEach(System.out::println);

        System.out.println("\nPartite vinte in trasferta:");
        eventoDAO.getPartiteVinteInTrasferta().forEach(System.out::println);

        System.out.println("\nPartite pareggiate:");
        eventoDAO.getPartitePareggiate().forEach(System.out::println);

        System.out.println("\nGare di atletica per vincitore (p2):");
        eventoDAO.getGareDiAtleticaPerVincitore(p2).forEach(System.out::println);

        System.out.println("\nGare di atletica per partecipante (p1):");
        eventoDAO.getGareDiAtleticaPerPartecipante(p1).forEach(System.out::println);

        System.out.println("\nEventi sold out:");
        eventoDAO.getEventiSoldOut().forEach(System.out::println);

        System.out.println("\nPartecipazioni DA_CONFERMARE per concerto:");
        partecipazioneDAO.getPartecipazioniDaConfermarePerEvento(concerto).forEach(System.out::println);

        em.close();
        JPAUtil.getEntityManagerFactory().close();
    }
}
