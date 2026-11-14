package com.gielle.sito.contatti;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Una richiesta arrivata dal modulo della pagina Contatti. */
@Entity
@Table(name = "richieste_contatto")
public class RichiestaContatto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(length = 160)
    private String azienda;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(length = 40)
    private String telefono;

    @Column(length = 80)
    private String oggetto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String messaggio;

    /** Nome originale del disegno allegato (facoltativo). */
    @Column(name = "allegato_nome", length = 255)
    private String allegatoNome;

    /** Dove è stato salvato il file sul server, relativo alla cartella degli allegati. */
    @Column(name = "allegato_percorso", length = 500)
    private String allegatoPercorso;

    @Column(name = "privacy_accettata", nullable = false)
    private boolean privacyAccettata;

    @Column(name = "ricevuta_il", nullable = false)
    private LocalDateTime ricevutaIl = LocalDateTime.now();

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getAzienda() { return azienda; }
    public void setAzienda(String azienda) { this.azienda = azienda; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getOggetto() { return oggetto; }
    public void setOggetto(String oggetto) { this.oggetto = oggetto; }
    public String getMessaggio() { return messaggio; }
    public void setMessaggio(String messaggio) { this.messaggio = messaggio; }
    public String getAllegatoNome() { return allegatoNome; }
    public void setAllegatoNome(String allegatoNome) { this.allegatoNome = allegatoNome; }
    public String getAllegatoPercorso() { return allegatoPercorso; }
    public void setAllegatoPercorso(String allegatoPercorso) { this.allegatoPercorso = allegatoPercorso; }
    public boolean isPrivacyAccettata() { return privacyAccettata; }
    public void setPrivacyAccettata(boolean privacyAccettata) { this.privacyAccettata = privacyAccettata; }
    public LocalDateTime getRicevutaIl() { return ricevutaIl; }
}
