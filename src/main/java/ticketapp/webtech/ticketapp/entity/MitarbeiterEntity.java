package ticketapp.webtech.ticketapp.entity;

import jakarta.persistence.*;

@Entity(name = "mitarbeiter")
public class MitarbeiterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "vorname", nullable = false)
    private String vorname;

    @Column(name = "nachname", nullable = false)
    private String nachname;

    @Column(name = "personalnummer", nullable = false)
    private long personalnummer;

    @Column(name = "mailadresse")
    private String mailadresse;

    @Column(name = "benutzername", nullable = false, unique = true)
    private String benutzername;

    @Column(name = "passwort", nullable = false)
    private String passwort;

    @Enumerated(EnumType.STRING)
    @Column(name = "MitarbeiterBereich", nullable = false)
    private MitarbeiterBereich mitarbeiterBereich;


    public MitarbeiterEntity(String vorname, String nachname, long personalnummer, String mailadresse, String benutzername, String passwort, MitarbeiterBereich mitarbeiterBereich) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.personalnummer = personalnummer;
        this.mailadresse = mailadresse;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.mitarbeiterBereich = mitarbeiterBereich;
    }

    protected MitarbeiterEntity() {}

    public long getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public long getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(long personalnummer) {
        this.personalnummer = personalnummer;
    }

    public String getMailadresse() {
        return mailadresse;
    }

    public void setMailadresse(String mailadresse) {
        this.mailadresse = mailadresse;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public MitarbeiterBereich getMitarbeiterBereich() {
        return mitarbeiterBereich;
    }

    public void setMitarbeiterBereich(MitarbeiterBereich mitarbeiterBereich) {
        this.mitarbeiterBereich = mitarbeiterBereich;
    }

    public enum MitarbeiterBereich {
        FACHLICHER_MITARBEITER, IT_MITARBEITER
    }
}
