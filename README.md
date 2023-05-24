# ProgettoEDOK3

questa repo contiene l'ultima parte del progetto per EDOK.

Il progetto consiste nel utilizzare il database creato in precedenza e rendere disponibile le operazioni scritte 
in passato come API.

per gestire l'api l'applicazione utilizza spark

per impostazione di default, 
dopo che hai avviato l'applicazione, se lavori in localhost puoi accedere al seguente link
per provare l'applicazione sul web

http://localhost:4567/

---
##Build dell'applicazione
Sono necessarie le seguenti librerie:
<a href="https://jdbc.postgresql.org/download/postgresql-42.6.0.jar">postgres jdbc</a>
e
<a href="https://repo1.maven.org/maven2/com/sparkjava/spark-core/2.9.4/spark-core-2.9.4.jar">spark</a>

---
## CREAZIONE DEL DATABASE

Inserisci le tables nel database

CREATE TABLE IF NOT EXISTS fornitore
(
id_fornitore integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
nome character varying(50) NOT NULL,
CONSTRAINT fornitore_pkey PRIMARY KEY (id_fornitore)
);


CREATE TABLE IF NOT EXISTS prodotto
(
id_prodotto integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
nome character varying(50) NOT NULL,
prezzo integer NOT NULL,
id_fornitore integer,
CONSTRAINT prodotto_pkey PRIMARY KEY (id_prodotto),
CONSTRAINT prodotto_id_fornitore_fkey FOREIGN KEY (id_fornitore)
REFERENCES fornitore (id_fornitore) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE CASCADE
);