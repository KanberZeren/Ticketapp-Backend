package ticketapp.webtech.ticketapp.web.api;

public class Mitarbeiter {

    private long id;
    private String vorname;
    private String nachname;
    private long personalnummer;
    private String mailadresse;
    private String benutzername;
    private String passwort;
    private String mitarbeiterBereich;

    public Mitarbeiter(long id, String vorname, String nachname, long personalnummer, String mailadresse, String benutzername, String passwort, String mitarbeiterBereich) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.personalnummer = personalnummer;
        this.mailadresse = mailadresse;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.mitarbeiterBereich = mitarbeiterBereich;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getMitarbeiterBereich() {
        return mitarbeiterBereich;
    }

    public void setMitarbeiterBereich(String mitarbeiterBereich) {
        this.mitarbeiterBereich = mitarbeiterBereich;
    }
}
