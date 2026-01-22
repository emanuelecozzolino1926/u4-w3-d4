package emanueleCozz.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gare_atletica")
@NamedQueries({
        @NamedQuery(
                name = "GaraDiAtletica.getGarePerVincitore",
                query = "SELECT g FROM GaraDiAtletica g WHERE g.vincitore = :vincitore"
        ),
        @NamedQuery(
                name = "GaraDiAtletica.getGarePerPartecipante",
                query = "SELECT g FROM GaraDiAtletica g JOIN g.atleti a WHERE a = :partecipante"
        )
})
public class GaraDiAtletica extends Evento {

    @ManyToMany
    @JoinTable(
            name = "gara_atleti",
            joinColumns = @JoinColumn(name = "gara_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    private Set<Persona> atleti = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "vincitore_id")
    private Persona vincitore;

    public GaraDiAtletica() {
    }

    public GaraDiAtletica(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti);
    }

    public Set<Persona> getAtleti() {
        return atleti;
    }

    public void setAtleti(Set<Persona> atleti) {
        this.atleti = atleti;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }

    @Override
    public String toString() {
        return "GaraDiAtletica{" +
                "id=" + getId() +
                ", titolo='" + getTitolo() + '\'' +
                ", numAtleti=" + (atleti != null ? atleti.size() : 0) +
                ", vincitoreId=" + (vincitore != null ? vincitore.getId() : null) +
                '}';
    }
}
