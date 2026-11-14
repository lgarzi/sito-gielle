package com.gielle.sito.candidature;

import com.gielle.sito.allegati.AllegatoNonValidoException;
import com.gielle.sito.allegati.ArchivioAllegati;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/candidature")
public class CandidatureController {

    private static final Set<String> ESTENSIONI_CV = Set.of("pdf", "doc", "docx");

    private final CandidaturaRepository repository;
    private final ArchivioAllegati archivio;

    public CandidatureController(CandidaturaRepository repository, ArchivioAllegati archivio) {
        this.repository = repository;
        this.archivio = archivio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> ricevi(@Valid @ModelAttribute ModuloCandidatura modulo) {
        if (modulo.curriculum().isEmpty()) {
            throw new AllegatoNonValidoException("Il curriculum è obbligatorio");
        }
        Candidatura c = new Candidatura();
        c.setNome(modulo.nome().trim());
        c.setCognome(modulo.cognome().trim());
        c.setEmail(modulo.email().trim());
        c.setTelefono(modulo.telefono().trim());
        c.setPosizione(modulo.posizione() == null || modulo.posizione().isBlank() ? null : modulo.posizione().trim());
        c.setMessaggio(modulo.messaggio() == null || modulo.messaggio().isBlank() ? null : modulo.messaggio().trim());
        c.setPrivacyAccettata(true);
        c.setCvNome(modulo.curriculum().getOriginalFilename());
        c.setCvPercorso(archivio.salva(modulo.curriculum(), "cv", ESTENSIONI_CV));
        return Map.of("id", repository.save(c).getId());
    }
}
