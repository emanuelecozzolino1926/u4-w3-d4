package emanueleCozz.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "partite_calcio")
@NamedQueries({
        @NamedQuery(
                name = "PartitaDiCalcio.getPartiteVinteInCasa",
                query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraDiCasa"
        ),
        @NamedQuery(
                name = "PartitaDiCalcio.getPartiteVinteInTrasferta",
                query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite"
        ),
        @NamedQuery(
                name = "PartitaDiCalcio.getPartitePareggiate",
                query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente IS NULL"
        )
})
public class PartitaDiCalcio extends Evento {

    @Column(name = "squadra_casa", nullable = false)
    private String squadraDiCasa;

    @Column(name = "squadra_ospite", nullable = false)
    private String squadraOspite;

    @Column(name = "squadra_vincente")
    private String squadraVincente; // null se pareggio

    @Column(name = "gol_casa")
    private int numeroGolSquadraDiCasa;

    @Column(name = "gol_ospite")
    private int numeroGolSquadraOspite;

    public PartitaDiCalcio() {
    }

    public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti,
                           String squadraDiCasa, String squadraOspite, String squadraVincente, int numeroGolSquadraDiCasa, int numeroGolSquadraOspite) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti);
        this.squadraDiCasa = squadraDiCasa;
        this.squadraOspite = squadraOspite;
        this.squadraVincente = squadraVincente;
        this.numeroGolSquadraDiCasa = numeroGolSquadraDiCasa;
        this.numeroGolSquadraOspite = numeroGolSquadraOspite;
    }

    public String getSquadraDiCasa() {
        return squadraDiCasa;
    }

    public void setSquadraDiCasa(String squadraDiCasa) {
        this.squadraDiCasa = squadraDiCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public String getSquadraVincente() {
        return squadraVincente;
    }

    public void setSquadraVincente(String squadraVincente) {
        this.squadraVincente = squadraVincente;
    }

    public int getNumeroGolSquadraDiCasa() {
        return numeroGolSquadraDiCasa;
    }

    public void setNumeroGolSquadraDiCasa(int numeroGolSquadraDiCasa) {
        this.numeroGolSquadraDiCasa = numeroGolSquadraDiCasa;
    }

    public int getNumeroGolSquadraOspite() {
        return numeroGolSquadraOspite;
    }

    public void setNumeroGolSquadraOspite(int numeroGolSquadraOspite) {
        this.numeroGolSquadraOspite = numeroGolSquadraOspite;
    }

    @Override
    public String toString() {
        return "PartitaDiCalcio{" +
                "id=" + getId() +
                ", titolo='" + getTitolo() + '\'' +
                ", casa='" + squadraDiCasa + '\'' +
                ", ospite='" + squadraOspite + '\'' +
                ", vincente='" + squadraVincente + '\'' +
                ", golCasa=" + numeroGolSquadraDiCasa +
                ", golOspite=" + numeroGolSquadraOspite +
                '}';
    }
}
