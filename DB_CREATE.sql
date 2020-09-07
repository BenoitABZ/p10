
CREATE SEQUENCE public.ouvrage_id_ouvrage_seq;

CREATE TABLE public.Ouvrage (
                id_ouvrage INTEGER NOT NULL DEFAULT nextval('public.ouvrage_id_ouvrage_seq'),
                titre VARCHAR NOT NULL,
                auteur VARCHAR NOT NULL,
                categorie VARCHAR NOT NULL,
                resume VARCHAR NOT NULL,
                anne_parution VARCHAR NOT NULL,
                disponibilite VARCHAR NOT NULL,
                image VARCHAR NOT NULL,
                CONSTRAINT ouvrage_pk PRIMARY KEY (id_ouvrage)
);


ALTER SEQUENCE public.ouvrage_id_ouvrage_seq OWNED BY public.Ouvrage.id_ouvrage;

CREATE SEQUENCE public.emprunteur_id_emprunteur_seq;

CREATE TABLE public.Emprunteur (
                id_emprunteur INTEGER NOT NULL DEFAULT nextval('public.emprunteur_id_emprunteur_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                adresse_mail VARCHAR NOT NULL,
                telephone VARCHAR NOT NULL,
                identifiant VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                date_inscription DATE NOT NULL,
                libelle_voie VARCHAR NOT NULL,
                numero_voie VARCHAR NOT NULL,
                nom_voie VARCHAR NOT NULL,
                code_postal VARCHAR NOT NULL,
                ville VARCHAR NOT NULL,
                CONSTRAINT emprunteur_pk PRIMARY KEY (id_emprunteur)
);


ALTER SEQUENCE public.emprunteur_id_emprunteur_seq OWNED BY public.Emprunteur.id_emprunteur;

CREATE SEQUENCE public.bibliotheque_id_bibliotheque_seq;

CREATE TABLE public.Bibliotheque (
                id_bibliotheque INTEGER NOT NULL DEFAULT nextval('public.bibliotheque_id_bibliotheque_seq'),
                nom VARCHAR NOT NULL,
                telephone VARCHAR NOT NULL,
                mail VARCHAR NOT NULL,
                image VARCHAR NOT NULL,
                numero_voie VARCHAR NOT NULL,
                libelle_voie VARCHAR NOT NULL,
                nom_voie VARCHAR NOT NULL,
                code_postal VARCHAR NOT NULL,
                ville VARCHAR NOT NULL,
                CONSTRAINT bibliotheque_pk PRIMARY KEY (id_bibliotheque)
);


ALTER SEQUENCE public.bibliotheque_id_bibliotheque_seq OWNED BY public.Bibliotheque.id_bibliotheque;

CREATE SEQUENCE public.exemplaire_id_exemplaire_seq;

CREATE TABLE public.Exemplaire (
                id_exemplaire INTEGER NOT NULL DEFAULT nextval('public.exemplaire_id_exemplaire_seq'),
                annee_publication VARCHAR NOT NULL,
                editeur VARCHAR NOT NULL,
                id_ouvrage INTEGER NOT NULL,
                id_bibliotheque INTEGER NOT NULL,
                CONSTRAINT exemplaire_pk PRIMARY KEY (id_exemplaire)
);


ALTER SEQUENCE public.exemplaire_id_exemplaire_seq OWNED BY public.Exemplaire.id_exemplaire;

CREATE TABLE public.Emprunt (
                id_emprunt INTEGER NOT NULL,
                date_emprunt DATE NOT NULL,
                date_retour DATE NOT NULL,
                prolongation BOOLEAN NOT NULL,
                id_exemplaire INTEGER NOT NULL,
                id_emprunteur INTEGER NOT NULL,
                CONSTRAINT emprunt_pk PRIMARY KEY (id_emprunt)
);


ALTER TABLE public.Exemplaire ADD CONSTRAINT ouvrage_exemplaire_fk
FOREIGN KEY (id_ouvrage)
REFERENCES public.Ouvrage (id_ouvrage)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Emprunt ADD CONSTRAINT emprunteur_emprunt_fk
FOREIGN KEY (id_emprunteur)
REFERENCES public.Emprunteur (id_emprunteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Exemplaire ADD CONSTRAINT bibliotheque_exemplaire_fk
FOREIGN KEY (id_bibliotheque)
REFERENCES public.Bibliotheque (id_bibliotheque)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Emprunt ADD CONSTRAINT exemplaire_emprunt_fk
FOREIGN KEY (id_exemplaire)
REFERENCES public.Exemplaire (id_exemplaire)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
