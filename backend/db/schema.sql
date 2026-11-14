-- Database del sito Gielle (MySQL 8).
-- Da eseguire una volta, ad esempio da MySQL Workbench o dalla riga di comando:
--   mysql -u root -p < db/schema.sql
-- Cambia la password qui e in src/main/resources/application.properties.

CREATE DATABASE IF NOT EXISTS gielle CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'gielle'@'localhost' IDENTIFIED BY 'cambia-questa-password';
GRANT SELECT, INSERT, UPDATE, DELETE ON gielle.* TO 'gielle'@'localhost';

USE gielle;

-- Richieste dal modulo della pagina Contatti
CREATE TABLE IF NOT EXISTS richieste_contatto (
  id                BIGINT       NOT NULL AUTO_INCREMENT,
  nome              VARCHAR(120) NOT NULL,
  azienda           VARCHAR(160),
  email             VARCHAR(200) NOT NULL,
  telefono          VARCHAR(40),
  oggetto           VARCHAR(80),
  messaggio         TEXT         NOT NULL,
  allegato_nome     VARCHAR(255),
  allegato_percorso VARCHAR(500),
  privacy_accettata BIT(1)       NOT NULL,
  ricevuta_il       DATETIME(6)  NOT NULL,
  PRIMARY KEY (id),
  KEY idx_contatto_ricevuta (ricevuta_il)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Candidature dal modulo "Lavora con noi" (il CV è un file nella cartella allegati/cv)
CREATE TABLE IF NOT EXISTS candidature (
  id                BIGINT       NOT NULL AUTO_INCREMENT,
  nome              VARCHAR(80)  NOT NULL,
  cognome           VARCHAR(80)  NOT NULL,
  email             VARCHAR(200) NOT NULL,
  telefono          VARCHAR(40)  NOT NULL,
  posizione         VARCHAR(80),
  messaggio         TEXT,
  cv_nome           VARCHAR(255) NOT NULL,
  cv_percorso       VARCHAR(500) NOT NULL,
  privacy_accettata BIT(1)       NOT NULL,
  ricevuta_il       DATETIME(6)  NOT NULL,
  PRIMARY KEY (id),
  KEY idx_candidatura_ricevuta (ricevuta_il)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
