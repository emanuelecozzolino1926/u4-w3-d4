package emanueleCozz.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "partecipazioni")
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoPartecipazione stato;

    public Partecipazione() {
    }

    public Partecipazione(Persona persona, Evento evento, StatoPartecipazione stato) {
        this.persona = persona;
        this.evento = evento;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public StatoPartecipazione getStato() {
        return stato;
    }

    public void setStato(StatoPartecipazione stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", personaId=" + (persona != null ? persona.getId() : null) +
                ", eventoId=" + (evento != null ? evento.getId() : null) +
                ", stato=" + stato +
                '}';
    }
}
