package com.gielle.sito.contatti;

import com.gielle.sito.allegati.ArchivioAllegati;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/contatti")
public class ContattiController {

    private static final Set<String> ESTENSIONI_DISEGNO = Set.of("pdf", "dxf", "dwg", "step", "stp", "igs", "iges");

    private final RichiestaContattoRepository repository;
    private final ArchivioAllegati archivio;

    public ContattiController(RichiestaContattoRepository repository, ArchivioAllegati archivio) {
        this.repository = repository;
        this.archivio = archivio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> ricevi(@Valid @ModelAttribute ModuloContatto modulo) {
        RichiestaContatto r = new RichiestaContatto();
        r.setNome(modulo.nome().trim());
        r.setAzienda(vuotoANull(modulo.azienda()));
        r.setEmail(modulo.email().trim());
        r.setTelefono(vuotoANull(modulo.telefono()));
        r.setOggetto(vuotoANull(modulo.oggetto()));
        r.setMessaggio(modulo.messaggio().trim());
        r.setPrivacyAccettata(true);
        if (modulo.disegno() != null && !modulo.disegno().isEmpty()) {
            r.setAllegatoNome(modulo.disegno().getOriginalFilename());
            r.setAllegatoPercorso(archivio.salva(modulo.disegno(), "disegni", ESTENSIONI_DISEGNO));
        }
        return Map.of("id", repository.save(r).getId());
    }

    private static String vuotoANull(String s) {
        return s == null || s.isBlank() ? null : s.trim();
    }
}
