
CREATE SEQUENCE public.ouvrage_id_ouvrage_seq;

CREATE TABLE public.Ouvrage (
                id_ouvrage INTEGER NOT NULL DEFAULT nextval('public.ouvrage_id_ouvrage_seq'),
                titre VARCHAR NOT NULL,
                auteur VARCHAR,
                categorie VARCHAR,
                annee_parution VARCHAR,
                disponibilite BOOLEAN,
                resume VARCHAR,
                image VARCHAR,
                CONSTRAINT id_ouvrage PRIMARY KEY (id_ouvrage)
);


ALTER SEQUENCE public.ouvrage_id_ouvrage_seq OWNED BY public.Ouvrage.id_ouvrage;

CREATE SEQUENCE public.bibliotheque_id_bibliotheque_seq;

CREATE TABLE public.Bibliotheque (
                id_bibliotheque INTEGER NOT NULL DEFAULT nextval('public.bibliotheque_id_bibliotheque_seq'),
                nom VARCHAR NOT NULL,
                telephone VARCHAR,
                mail VARCHAR,
                image VARCHAR,
                numero_voie VARCHAR,
                libelle_voie VARCHAR,
                nom_voie VARCHAR,
                code_postal VARCHAR,
                ville VARCHAR,
                CONSTRAINT id_bibliotheque PRIMARY KEY (id_bibliotheque)
);


ALTER SEQUENCE public.bibliotheque_id_bibliotheque_seq OWNED BY public.Bibliotheque.id_bibliotheque;

CREATE SEQUENCE public.exemplaire_id_exemplaire_seq;

CREATE TABLE public.Exemplaire (
                id_exemplaire INTEGER NOT NULL DEFAULT nextval('public.exemplaire_id_exemplaire_seq'),
                annee_publication VARCHAR,
                editeur VARCHAR NOT NULL,
                id_bibliotheque INTEGER NOT NULL,
                id_ouvrage INTEGER NOT NULL,
                CONSTRAINT id_exemplaire PRIMARY KEY (id_exemplaire)
);


ALTER SEQUENCE public.exemplaire_id_exemplaire_seq OWNED BY public.Exemplaire.id_exemplaire;

CREATE SEQUENCE public.emprunteur_id_emprunteur_seq;

CREATE TABLE public.Emprunteur (
                id_emprunteur INTEGER NOT NULL DEFAULT nextval('public.emprunteur_id_emprunteur_seq'),
                nom VARCHAR,
                prenom VARCHAR,
                adresse_mail VARCHAR,
                telephone VARCHAR,
                identifiant VARCHAR NOT NULL,
                mot_de_passe VARCHAR,
                date_inscription DATE,
                libelle_voie VARCHAR,
                numero_voie VARCHAR,
                nom_voie VARCHAR,
                code_postal VARCHAR,
                ville VARCHAR,
                CONSTRAINT id_emprunteur PRIMARY KEY (id_emprunteur)
);


ALTER SEQUENCE public.emprunteur_id_emprunteur_seq OWNED BY public.Emprunteur.id_emprunteur;

CREATE SEQUENCE public.reservation_id_reservation_seq;

CREATE TABLE public.Reservation (
                id_reservation INTEGER NOT NULL DEFAULT nextval('public.reservation_id_reservation_seq'),
                date_reservation DATE,
                date_notification DATE,
                notification BOOLEAN,
                id_emprunteur INTEGER NOT NULL,
                id_ouvrage INTEGER NOT NULL,
                CONSTRAINT id_reservation PRIMARY KEY (id_reservation)
);


ALTER SEQUENCE public.reservation_id_reservation_seq OWNED BY public.Reservation.id_reservation;

CREATE SEQUENCE public.emprunt_id_emprunt_seq;

CREATE TABLE public.Emprunt (
                id_emprunt INTEGER NOT NULL DEFAULT nextval('public.emprunt_id_emprunt_seq'),
                date_emprunt DATE,
                date_retour DATE,
                prolongation BOOLEAN,
                id_emprunteur INTEGER NOT NULL,
                id_exemplaire INTEGER NOT NULL,
                CONSTRAINT id_emprunt PRIMARY KEY (id_emprunt)
);


ALTER SEQUENCE public.emprunt_id_emprunt_seq OWNED BY public.Emprunt.id_emprunt;

ALTER TABLE public.Exemplaire ADD CONSTRAINT ouvrage_exemplaire_fk
FOREIGN KEY (id_ouvrage)
REFERENCES public.Ouvrage (id_ouvrage)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Reservation ADD CONSTRAINT ouvrage_reservation_fk
FOREIGN KEY (id_ouvrage)
REFERENCES public.Ouvrage (id_ouvrage)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Exemplaire ADD CONSTRAINT bibliotheque_exemplaire_fk
FOREIGN KEY (id_bibliotheque)
REFERENCES public.Bibliotheque (id_bibliotheque)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Emprunt ADD CONSTRAINT emprunt_exemplaire_fk
FOREIGN KEY (id_exemplaire)
REFERENCES public.Exemplaire (id_exemplaire)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Emprunt ADD CONSTRAINT emprunteur_emprunt_fk
FOREIGN KEY (id_emprunteur)
REFERENCES public.Emprunteur (id_emprunteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Reservation ADD CONSTRAINT emprunteur_reservation_fk
FOREIGN KEY (id_emprunteur)
REFERENCES public.Emprunteur (id_emprunteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
