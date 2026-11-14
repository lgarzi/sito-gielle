package com.gielle.sito.contatti;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

/** I campi del modulo Contatti, con gli stessi nomi usati in contatti.html. */
public record ModuloContatto(
        @NotBlank @Size(max = 120) String nome,
        @Size(max = 160) String azienda,
        @NotBlank @Email @Size(max = 200) String email,
        @Size(max = 40) String telefono,
        @Size(max = 80) String oggetto,
        @NotBlank @Size(max = 5000) String messaggio,
        MultipartFile disegno,
        @AssertTrue(message = "Va accettata l'informativa privacy") boolean privacy) {
}
