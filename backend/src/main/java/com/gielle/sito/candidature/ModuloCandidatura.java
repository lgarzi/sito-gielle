package com.gielle.sito.candidature;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

/** I campi del modulo "Lavora con noi", con gli stessi nomi usati in contatti.html. */
public record ModuloCandidatura(
        @NotBlank @Size(max = 80) String nome,
        @NotBlank @Size(max = 80) String cognome,
        @NotBlank @Email @Size(max = 200) String email,
        @NotBlank @Size(max = 40) String telefono,
        @Size(max = 80) String posizione,
        @Size(max = 5000) String messaggio,
        @NotNull(message = "Il curriculum è obbligatorio") MultipartFile curriculum,
        @AssertTrue(message = "Va accettata l'informativa privacy") boolean privacy) {
}
