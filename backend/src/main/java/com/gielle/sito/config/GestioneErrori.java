package com.gielle.sito.config;

import com.gielle.sito.allegati.AllegatoNonValidoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.LinkedHashMap;
import java.util.Map;

/** Risponde al sito con l'elenco dei campi da correggere, invece di una pagina d'errore. */
@RestControllerAdvice
public class GestioneErrori {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> campiNonValidi(BindException e) {
        Map<String, String> campi = new LinkedHashMap<>();
        e.getFieldErrors().forEach(f -> campi.putIfAbsent(f.getField(), f.getDefaultMessage()));
        return Map.of("errore", "Controlla i campi evidenziati", "campi", campi);
    }

    @ExceptionHandler(AllegatoNonValidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> allegatoNonValido(AllegatoNonValidoException e) {
        return Map.of("errore", e.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public Map<String, Object> fileTroppoGrande() {
        return Map.of("errore", "Il file supera la dimensione massima consentita");
    }
}
