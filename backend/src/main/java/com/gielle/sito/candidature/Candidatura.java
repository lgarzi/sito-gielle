package com.gielle.sito.candidature;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Una candidatura arrivata dal modulo "Lavora con noi", con il curriculum allegato. */
@Entity
@Table(name = "candidature")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 80)
    private String cognome;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 40)
    private String telefono;

    @Column(length = 80)
    private String posizione;

    @Column(columnDefinition = "TEXT")
    private String messaggio;

    @Column(name = "cv_nome", nullable = false, length = 255)
    private String cvNome;

    @Column(name = "cv_percorso", nullable = false, length = 500)
    private String cvPercorso;

    @Column(name = "privacy_accettata", nullable = false)
    private boolean privacyAccettata;

    @Column(name = "ricevuta_il", nullable = false)
    private LocalDateTime ricevutaIl = LocalDateTime.now();

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getPosizione() { return posizione; }
    public void setPosizione(String posizione) { this.posizione = posizione; }
    public String getMessaggio() { return messaggio; }
    public void setMessaggio(String messaggio) { this.messaggio = messaggio; }
    public String getCvNome() { return cvNome; }
    public void setCvNome(String cvNome) { this.cvNome = cvNome; }
    public String getCvPercorso() { return cvPercorso; }
    public void setCvPercorso(String cvPercorso) { this.cvPercorso = cvPercorso; }
    public boolean isPrivacyAccettata() { return privacyAccettata; }
    public void setPrivacyAccettata(boolean privacyAccettata) { this.privacyAccettata = privacyAccettata; }
    public LocalDateTime getRicevutaIl() { return ricevutaIl; }
}
