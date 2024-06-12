package ticketapp.webtech.ticketapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticketnummer", nullable = false, unique = true)
    private String ticketnummer;

    @Column(name = "betreff", nullable = false)
    private String betreff;

    @Column(name = "nachricht", nullable = false, length = 10000)
    private String nachricht;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus status;

    @Column(name = "erstelltAm", nullable = false)
    private LocalDateTime erstelltAm;

    @Column(name = "kommentar")
    private String kommentar;


    @PrePersist
    private void generateTicketnummer(){
        this.ticketnummer = UUID.randomUUID().toString();
        this.erstelltAm = LocalDateTime.now();
    }
    public TicketEntity(String betreff, String nachricht, TicketStatus status){
        this.betreff = betreff;
        this.nachricht = nachricht;
        this.status = status;
    }

    protected TicketEntity() {}

    public Long getId() {
        return id;
    }

    public String getTicketnummer() {
        return ticketnummer;
    }

    public void setTicketnummer(String ticketnummer) {
        this.ticketnummer = ticketnummer;
    }

    public String getBetreff() {
        return betreff;
    }

    public void setBetreff(String betreff) {
        this.betreff = betreff;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getErstelltAm() {
        return erstelltAm;
    }

    public void setErstelltAm(LocalDateTime erstelltAm) {
        this.erstelltAm = erstelltAm;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public enum TicketStatus {
        GELÖST, OFFEN, WARTEND, IN_BEARBEITUNG
    }
}
