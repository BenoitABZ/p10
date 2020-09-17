--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5 (Ubuntu 11.5-0ubuntu0.19.04.1)
-- Dumped by pg_dump version 11.5 (Ubuntu 11.5-0ubuntu0.19.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bibliotheque; Type: TABLE; Schema: public; Owner: benoit_abouzeid
--

CREATE TABLE public.bibliotheque (
    id_bibliotheque integer NOT NULL,
    nom character varying NOT NULL,
    telephone character varying NOT NULL,
    mail character varying NOT NULL,
    image character varying NOT NULL,
    numero_voie character varying NOT NULL,
    libelle_voie character varying NOT NULL,
    nom_voie character varying NOT NULL,
    code_postal character varying NOT NULL,
    ville character varying NOT NULL
);


ALTER TABLE public.bibliotheque OWNER TO benoit_abouzeid;

--
-- Name: bibliotheque_bibliotheque_id_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.bibliotheque_bibliotheque_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bibliotheque_bibliotheque_id_seq OWNER TO benoit_abouzeid;

--
-- Name: bibliotheque_id_bibliotheque_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.bibliotheque_id_bibliotheque_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bibliotheque_id_bibliotheque_seq OWNER TO benoit_abouzeid;

--
-- Name: bibliotheque_id_bibliotheque_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: benoit_abouzeid
--

ALTER SEQUENCE public.bibliotheque_id_bibliotheque_seq OWNED BY public.bibliotheque.id_bibliotheque;


--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: benoit_abouzeid
--

CREATE TABLE public.emprunt (
    id_emprunt integer NOT NULL,
    date_emprunt date NOT NULL,
    date_retour date NOT NULL,
    prolongation boolean NOT NULL,
    id_exemplaire integer NOT NULL,
    id_emprunteur integer NOT NULL
);


ALTER TABLE public.emprunt OWNER TO benoit_abouzeid;

--
-- Name: emprunt_emprunt_id_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.emprunt_emprunt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunt_emprunt_id_seq OWNER TO benoit_abouzeid;

--
-- Name: emprunteur; Type: TABLE; Schema: public; Owner: benoit_abouzeid
--

CREATE TABLE public.emprunteur (
    id_emprunteur integer NOT NULL,
    nom character varying NOT NULL,
    prenom character varying NOT NULL,
    adresse_mail character varying NOT NULL,
    telephone character varying NOT NULL,
    identifiant character varying NOT NULL,
    mot_de_passe character varying NOT NULL,
    date_inscription date NOT NULL,
    libelle_voie character varying NOT NULL,
    numero_voie character varying NOT NULL,
    nom_voie character varying NOT NULL,
    code_postal character varying NOT NULL,
    ville character varying NOT NULL
);


ALTER TABLE public.emprunteur OWNER TO benoit_abouzeid;

--
-- Name: emprunteur_emprunteur_id_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.emprunteur_emprunteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunteur_emprunteur_id_seq OWNER TO benoit_abouzeid;

--
-- Name: emprunteur_id_emprunteur_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.emprunteur_id_emprunteur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunteur_id_emprunteur_seq OWNER TO benoit_abouzeid;

--
-- Name: emprunteur_id_emprunteur_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: benoit_abouzeid
--

ALTER SEQUENCE public.emprunteur_id_emprunteur_seq OWNED BY public.emprunteur.id_emprunteur;


--
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: benoit_abouzeid
--

CREATE TABLE public.exemplaire (
    id_exemplaire integer NOT NULL,
    annee_publication character varying NOT NULL,
    editeur character varying NOT NULL,
    id_ouvrage integer NOT NULL,
    id_bibliotheque integer NOT NULL
);


ALTER TABLE public.exemplaire OWNER TO benoit_abouzeid;

--
-- Name: exemplaire_exemplaire_id_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.exemplaire_exemplaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exemplaire_exemplaire_id_seq OWNER TO benoit_abouzeid;

--
-- Name: exemplaire_id_exemplaire_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.exemplaire_id_exemplaire_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exemplaire_id_exemplaire_seq OWNER TO benoit_abouzeid;

--
-- Name: exemplaire_id_exemplaire_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: benoit_abouzeid
--

ALTER SEQUENCE public.exemplaire_id_exemplaire_seq OWNED BY public.exemplaire.id_exemplaire;


--
-- Name: ouvrage; Type: TABLE; Schema: public; Owner: benoit_abouzeid
--

CREATE TABLE public.ouvrage (
    id_ouvrage integer NOT NULL,
    titre character varying NOT NULL,
    auteur character varying NOT NULL,
    categorie character varying NOT NULL,
    resume character varying NOT NULL,
    annee_parution character varying NOT NULL,
    disponibilite character varying NOT NULL,
    image character varying NOT NULL
);


ALTER TABLE public.ouvrage OWNER TO benoit_abouzeid;

--
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.ouvrage_id_ouvrage_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ouvrage_id_ouvrage_seq OWNER TO benoit_abouzeid;

--
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: benoit_abouzeid
--

ALTER SEQUENCE public.ouvrage_id_ouvrage_seq OWNED BY public.ouvrage.id_ouvrage;


--
-- Name: ouvrage_ouvrage_id_seq; Type: SEQUENCE; Schema: public; Owner: benoit_abouzeid
--

CREATE SEQUENCE public.ouvrage_ouvrage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ouvrage_ouvrage_id_seq OWNER TO benoit_abouzeid;

--
-- Name: bibliotheque id_bibliotheque; Type: DEFAULT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.bibliotheque ALTER COLUMN id_bibliotheque SET DEFAULT nextval('public.bibliotheque_id_bibliotheque_seq'::regclass);


--
-- Name: emprunteur id_emprunteur; Type: DEFAULT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.emprunteur ALTER COLUMN id_emprunteur SET DEFAULT nextval('public.emprunteur_id_emprunteur_seq'::regclass);


--
-- Name: exemplaire id_exemplaire; Type: DEFAULT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.exemplaire ALTER COLUMN id_exemplaire SET DEFAULT nextval('public.exemplaire_id_exemplaire_seq'::regclass);


--
-- Name: ouvrage id_ouvrage; Type: DEFAULT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.ouvrage ALTER COLUMN id_ouvrage SET DEFAULT nextval('public.ouvrage_id_ouvrage_seq'::regclass);


--
-- Data for Name: bibliotheque; Type: TABLE DATA; Schema: public; Owner: benoit_abouzeid
--

COPY public.bibliotheque (id_bibliotheque, nom, telephone, mail, image, numero_voie, libelle_voie, nom_voie, code_postal, ville) FROM stdin;
9	Bibliothèque municipale principale	0552636547	bumup@ville.com	https://www.iphonote.com/wp-content/uploads/2019/05/apple-store-carnegie-washington_2.jpg	1	Rue	Jean Jaures	31000	Toulouse
10	Mediathèque AM	0541236585	mediathequeam@ville.com	https://www.aiguillon-construction.fr/wp-content/uploads/2017/09/Pho-AC-ECL-1-600x398.jpg	2	Rue	Perigord	31450	Toulouse
\.


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: benoit_abouzeid
--

COPY public.emprunt (id_emprunt, date_emprunt, date_retour, prolongation, id_exemplaire, id_emprunteur) FROM stdin;
3	2020-04-21	2020-05-19	f	8	9
4	2020-04-21	2020-05-19	f	9	9
\.


--
-- Data for Name: emprunteur; Type: TABLE DATA; Schema: public; Owner: benoit_abouzeid
--

COPY public.emprunteur (id_emprunteur, nom, prenom, adresse_mail, telephone, identifiant, mot_de_passe, date_inscription, libelle_voie, numero_voie, nom_voie, code_postal, ville) FROM stdin;
9	Abouzeid	Benoit	benoit.abouzeid@gmail.com	0612332837	babouzeid	$2a$10$p/NkQtC2VfRKQoWudVBpGeemVxNj07LPXQ9uQPyl6v0idwRZbvsAi	2020-03-08	Rue	25	Bertholot	31400	Toulouse
10	Rais	Meryem	meryem.rais@gmail.com	0699150101	mrais	$2a$10$/WMFYzHwfoJP/xTnktoJROBQ4ARxgVI6ufZtwr5vNz8CSi.dlMu/u	2020-03-08	Rue	17	Jean Moulin	31300	Toulouse
\.


--
-- Data for Name: exemplaire; Type: TABLE DATA; Schema: public; Owner: benoit_abouzeid
--

COPY public.exemplaire (id_exemplaire, annee_publication, editeur, id_ouvrage, id_bibliotheque) FROM stdin;
8	2015	Hachette	10	9
9	2013	Editions confluences	9	10
\.


--
-- Data for Name: ouvrage; Type: TABLE DATA; Schema: public; Owner: benoit_abouzeid
--

COPY public.ouvrage (id_ouvrage, titre, auteur, categorie, resume, annee_parution, disponibilite, image) FROM stdin;
9	J'accuse	Emile Zola	Biographie	Affaire Dreyfus	1898	false	https://static.fnac-static.com/multimedia/FR/Images_Produits/FR/fnac.com/Visual_Principal_340/8/9/1/9782910233198/tsp20120925075349/J-accuse.jpg
10	L'Amour medecin	Jean Baptiste Molière	Comédie	Histoire d'un medecin amoureux	1973	false	https://static.fnac-static.com/multimedia/Images/FR/NR/85/58/26/2513029/1540-1/tsp20150210124012/L-amour-medecin-Le-sicilien-ou-L-amour-peintre.jpg
\.


--
-- Name: bibliotheque_bibliotheque_id_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.bibliotheque_bibliotheque_id_seq', 10, true);


--
-- Name: bibliotheque_id_bibliotheque_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.bibliotheque_id_bibliotheque_seq', 1, false);


--
-- Name: emprunt_emprunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.emprunt_emprunt_id_seq', 4, true);


--
-- Name: emprunteur_emprunteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.emprunteur_emprunteur_id_seq', 10, true);


--
-- Name: emprunteur_id_emprunteur_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.emprunteur_id_emprunteur_seq', 1, false);


--
-- Name: exemplaire_exemplaire_id_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.exemplaire_exemplaire_id_seq', 9, true);


--
-- Name: exemplaire_id_exemplaire_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.exemplaire_id_exemplaire_seq', 1, false);


--
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.ouvrage_id_ouvrage_seq', 1, false);


--
-- Name: ouvrage_ouvrage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: benoit_abouzeid
--

SELECT pg_catalog.setval('public.ouvrage_ouvrage_id_seq', 10, true);


--
-- Name: bibliotheque bibliotheque_pk; Type: CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT bibliotheque_pk PRIMARY KEY (id_bibliotheque);


--
-- Name: emprunt emprunt_pk; Type: CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pk PRIMARY KEY (id_emprunt);


--
-- Name: emprunteur emprunteur_pk; Type: CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.emprunteur
    ADD CONSTRAINT emprunteur_pk PRIMARY KEY (id_emprunteur);


--
-- Name: exemplaire exemplaire_pk; Type: CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT exemplaire_pk PRIMARY KEY (id_exemplaire);


--
-- Name: ouvrage ouvrage_pk; Type: CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.ouvrage
    ADD CONSTRAINT ouvrage_pk PRIMARY KEY (id_ouvrage);


--
-- Name: exemplaire bibliotheque_exemplaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT bibliotheque_exemplaire_fk FOREIGN KEY (id_bibliotheque) REFERENCES public.bibliotheque(id_bibliotheque);


--
-- Name: emprunt emprunteur_emprunt_fk; Type: FK CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunteur_emprunt_fk FOREIGN KEY (id_emprunteur) REFERENCES public.emprunteur(id_emprunteur);


--
-- Name: emprunt exemplaire_emprunt_fk; Type: FK CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT exemplaire_emprunt_fk FOREIGN KEY (id_exemplaire) REFERENCES public.exemplaire(id_exemplaire);


--
-- Name: exemplaire ouvrage_exemplaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: benoit_abouzeid
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT ouvrage_exemplaire_fk FOREIGN KEY (id_ouvrage) REFERENCES public.ouvrage(id_ouvrage);


--
-- PostgreSQL database dump complete
--

