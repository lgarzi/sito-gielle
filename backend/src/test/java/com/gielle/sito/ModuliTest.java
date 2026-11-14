package com.gielle.sito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.gielle.sito.candidature.CandidaturaRepository;
import com.gielle.sito.contatti.RichiestaContattoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ModuliTest {

    @Autowired MockMvc mvc;
    @Autowired RichiestaContattoRepository contatti;
    @Autowired CandidaturaRepository candidature;

    @Test
    void salvaUnaRichiestaDiContatto() throws Exception {
        mvc.perform(multipart("/api/contatti")
                .param("nome", "Mario Rossi")
                .param("azienda", "Rossi Srl")
                .param("email", "mario@esempio.it")
                .param("oggetto", "Preventivo")
                .param("messaggio", "Vorrei un preventivo per 200 pezzi.")
                .param("privacy", "true"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
        assertThat(contatti.count()).isEqualTo(1);
    }

    @Test
    void rifiutaContattoSenzaPrivacy() throws Exception {
        mvc.perform(multipart("/api/contatti")
                .param("nome", "Mario")
                .param("email", "non-una-email")
                .param("messaggio", "ciao"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.campi").exists());
    }

    @Test
    void salvaUnaCandidaturaConCv() throws Exception {
        var cv = new MockMultipartFile("curriculum", "cv.pdf", "application/pdf", "%PDF-1.4 test".getBytes());
        mvc.perform(multipart("/api/candidature").file(cv)
                .param("nome", "Anna")
                .param("cognome", "Bianchi")
                .param("email", "anna@esempio.it")
                .param("telefono", "3331234567")
                .param("privacy", "true"))
            .andExpect(status().isCreated());
        assertThat(candidature.count()).isEqualTo(1);
    }

    @Test
    void rifiutaCvConEstensioneNonAmmessa() throws Exception {
        var cv = new MockMultipartFile("curriculum", "cv.exe", "application/octet-stream", "x".getBytes());
        mvc.perform(multipart("/api/candidature").file(cv)
                .param("nome", "Anna")
                .param("cognome", "Bianchi")
                .param("email", "anna@esempio.it")
                .param("telefono", "3331234567")
                .param("privacy", "true"))
            .andExpect(status().isBadRequest());
    }
}
