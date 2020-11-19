--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)

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

SET default_table_access_method = heap;

--
-- Name: bibliotheque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bibliotheque (
    id_bibliotheque integer NOT NULL,
    nom character varying NOT NULL,
    telephone character varying,
    mail character varying,
    image character varying,
    numero_voie character varying,
    libelle_voie character varying,
    nom_voie character varying,
    code_postal character varying,
    ville character varying
);


ALTER TABLE public.bibliotheque OWNER TO postgres;

--
-- Name: bibliotheque_id_bibliotheque_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bibliotheque_id_bibliotheque_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bibliotheque_id_bibliotheque_seq OWNER TO postgres;

--
-- Name: bibliotheque_id_bibliotheque_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bibliotheque_id_bibliotheque_seq OWNED BY public.bibliotheque.id_bibliotheque;


--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprunt (
    id_emprunt integer NOT NULL,
    date_emprunt date,
    date_retour date,
    prolongation boolean,
    id_emprunteur integer NOT NULL,
    id_exemplaire integer NOT NULL
);


ALTER TABLE public.emprunt OWNER TO postgres;

--
-- Name: emprunt_id_emprunt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunt_id_emprunt_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunt_id_emprunt_seq OWNER TO postgres;

--
-- Name: emprunt_id_emprunt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprunt_id_emprunt_seq OWNED BY public.emprunt.id_emprunt;


--
-- Name: emprunteur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprunteur (
    id_emprunteur integer NOT NULL,
    nom character varying,
    prenom character varying,
    adresse_mail character varying,
    telephone character varying,
    identifiant character varying NOT NULL,
    mot_de_passe character varying,
    date_inscription date,
    libelle_voie character varying,
    numero_voie character varying,
    nom_voie character varying,
    code_postal character varying,
    ville character varying
);


ALTER TABLE public.emprunteur OWNER TO postgres;

--
-- Name: emprunteur_id_emprunteur_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunteur_id_emprunteur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunteur_id_emprunteur_seq OWNER TO postgres;

--
-- Name: emprunteur_id_emprunteur_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprunteur_id_emprunteur_seq OWNED BY public.emprunteur.id_emprunteur;


--
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exemplaire (
    id_exemplaire integer NOT NULL,
    annee_publication character varying,
    editeur character varying NOT NULL,
    id_bibliotheque integer NOT NULL,
    id_ouvrage integer NOT NULL
);


ALTER TABLE public.exemplaire OWNER TO postgres;

--
-- Name: exemplaire_id_exemplaire_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.exemplaire_id_exemplaire_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exemplaire_id_exemplaire_seq OWNER TO postgres;

--
-- Name: exemplaire_id_exemplaire_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.exemplaire_id_exemplaire_seq OWNED BY public.exemplaire.id_exemplaire;


--
-- Name: ouvrage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ouvrage (
    id_ouvrage integer NOT NULL,
    titre character varying NOT NULL,
    auteur character varying,
    categorie character varying,
    annee_parution character varying,
    disponibilite boolean,
    resume character varying,
    image character varying
);


ALTER TABLE public.ouvrage OWNER TO postgres;

--
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ouvrage_id_ouvrage_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ouvrage_id_ouvrage_seq OWNER TO postgres;

--
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ouvrage_id_ouvrage_seq OWNED BY public.ouvrage.id_ouvrage;


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id_reservation integer NOT NULL,
    date_reservation date,
    date_notification date,
    notification boolean,
    id_emprunteur integer NOT NULL,
    id_ouvrage integer NOT NULL
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: reservation_id_reservation_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_reservation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_reservation_seq OWNER TO postgres;

--
-- Name: reservation_id_reservation_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_reservation_seq OWNED BY public.reservation.id_reservation;


--
-- Name: bibliotheque id_bibliotheque; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliotheque ALTER COLUMN id_bibliotheque SET DEFAULT nextval('public.bibliotheque_id_bibliotheque_seq'::regclass);


--
-- Name: emprunt id_emprunt; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt ALTER COLUMN id_emprunt SET DEFAULT nextval('public.emprunt_id_emprunt_seq'::regclass);


--
-- Name: emprunteur id_emprunteur; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunteur ALTER COLUMN id_emprunteur SET DEFAULT nextval('public.emprunteur_id_emprunteur_seq'::regclass);


--
-- Name: exemplaire id_exemplaire; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire ALTER COLUMN id_exemplaire SET DEFAULT nextval('public.exemplaire_id_exemplaire_seq'::regclass);


--
-- Name: ouvrage id_ouvrage; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ouvrage ALTER COLUMN id_ouvrage SET DEFAULT nextval('public.ouvrage_id_ouvrage_seq'::regclass);


--
-- Name: reservation id_reservation; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id_reservation SET DEFAULT nextval('public.reservation_id_reservation_seq'::regclass);


--
-- Data for Name: bibliotheque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bibliotheque (id_bibliotheque, nom, telephone, mail, image, numero_voie, libelle_voie, nom_voie, code_postal, ville) FROM stdin;
1	Bibliothèque municipale principale	0552636547	bumup@ville.com	https://www.iphonote.com/wp-content/uploads/2019/05/apple-store-carnegie-washington_2.jpg	1	Rue	Jean Jaures	31000	Toulouse
2	Mediathèque AM	0541236585	mediathequeam@ville.com	https://www.aiguillon-construction.fr/wp-content/uploads/2017/09/Pho-AC-ECL-1-600x398.jpg	2	Rue	Perigord	31450	Toulouse
\.


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprunt (id_emprunt, date_emprunt, date_retour, prolongation, id_emprunteur, id_exemplaire) FROM stdin;
1	2020-10-18	2020-10-24	f	3	2
2	2020-10-18	2020-10-24	f	4	3
\.


--
-- Data for Name: emprunteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprunteur (id_emprunteur, nom, prenom, adresse_mail, telephone, identifiant, mot_de_passe, date_inscription, libelle_voie, numero_voie, nom_voie, code_postal, ville) FROM stdin;
1	Abouzeid	Benoit	benoit.abouzeid@gmail.com	0612332837	babouzeid	$2a$10$BVxfR6fjlVDJ/zkaoTmmV.SXYKMd3bsFz2Z8d7Ukzn.wf4izIgNoS	2020-03-08	Rue	25	Bertholot	31400	Toulouse
2	Rais	Meryem1	meryem.rais@gmail.com	0699150101	mrais1	$2a$10$UW8gRSRzyb1U.5c.ijb1Remio4gLp1mMkzqnQO5rH6/0DF1TGlLF6	2020-03-08	Rue	17	Jean Moulin	31300	Toulouse
3	Rais	Meryem2	meryem.rais2@gmail.com	0699150101	mrais2	mdp	2020-03-08	Rue	17	Jean Moulin	31300	Toulouse
4	Rais	Meryem3	meryem.rais3@gmail.com	0699150101	mrais3	mdp	2020-03-08	Rue	17	Jean Moulin	31300	Toulouse
5	Rais	Meryem4	meryem.rais4@gmail.com	0699150101	mrais4	mdp	2020-03-08	Rue	17	Jean Moulin	31300	Toulouse
\.


--
-- Data for Name: exemplaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exemplaire (id_exemplaire, annee_publication, editeur, id_bibliotheque, id_ouvrage) FROM stdin;
1	2015	Hachette	1	2
2	2013	Editions Confluences	2	1
3	2012	Pearson	2	1
\.


--
-- Data for Name: ouvrage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ouvrage (id_ouvrage, titre, auteur, categorie, annee_parution, disponibilite, resume, image) FROM stdin;
1	J'accuse	Emile Zola	Biographie	1898	f	Affaire Dreyfus	https://static.fnac-static.com/multimedia/FR/Images_Produits/FR/fnac.com/Visual_Principal_340/8/9/1/9782910233198/tsp20120925075349/J-accuse.jpg
2	L'Amour medecin	Jean Baptiste Molière	Comédie	1973	t	Histoire d'un medecin amoureux	https://static.fnac-static.com/multimedia/Images/FR/NR/85/58/26/2513029/1540-1/tsp20150210124012/L-amour-medecin-Le-sicilien-ou-L-amour-peintre.jpg
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id_reservation, date_reservation, date_notification, notification, id_emprunteur, id_ouvrage) FROM stdin;
3	2020-11-14	\N	f	1	1
\.


--
-- Name: bibliotheque_id_bibliotheque_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bibliotheque_id_bibliotheque_seq', 1, true);


--
-- Name: emprunt_id_emprunt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunt_id_emprunt_seq', 1, false);


--
-- Name: emprunteur_id_emprunteur_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunteur_id_emprunteur_seq', 1, true);


--
-- Name: exemplaire_id_exemplaire_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exemplaire_id_exemplaire_seq', 1, true);


--
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ouvrage_id_ouvrage_seq', 1, true);


--
-- Name: reservation_id_reservation_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_reservation_seq', 3, true);


--
-- Name: bibliotheque id_bibliotheque; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT id_bibliotheque PRIMARY KEY (id_bibliotheque);


--
-- Name: emprunt id_emprunt; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT id_emprunt PRIMARY KEY (id_emprunt);


--
-- Name: emprunteur id_emprunteur; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunteur
    ADD CONSTRAINT id_emprunteur PRIMARY KEY (id_emprunteur);


--
-- Name: exemplaire id_exemplaire; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT id_exemplaire PRIMARY KEY (id_exemplaire);


--
-- Name: ouvrage id_ouvrage; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ouvrage
    ADD CONSTRAINT id_ouvrage PRIMARY KEY (id_ouvrage);


--
-- Name: reservation id_reservation; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT id_reservation PRIMARY KEY (id_reservation);


--
-- Name: exemplaire bibliotheque_exemplaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT bibliotheque_exemplaire_fk FOREIGN KEY (id_bibliotheque) REFERENCES public.bibliotheque(id_bibliotheque);


--
-- Name: emprunt emprunt_exemplaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_exemplaire_fk FOREIGN KEY (id_exemplaire) REFERENCES public.exemplaire(id_exemplaire);


--
-- Name: emprunt emprunteur_emprunt_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunteur_emprunt_fk FOREIGN KEY (id_emprunteur) REFERENCES public.emprunteur(id_emprunteur);


--
-- Name: reservation emprunteur_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT emprunteur_reservation_fk FOREIGN KEY (id_emprunteur) REFERENCES public.emprunteur(id_emprunteur);


--
-- Name: exemplaire ouvrage_exemplaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT ouvrage_exemplaire_fk FOREIGN KEY (id_ouvrage) REFERENCES public.ouvrage(id_ouvrage);


--
-- Name: reservation ouvrage_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT ouvrage_reservation_fk FOREIGN KEY (id_ouvrage) REFERENCES public.ouvrage(id_ouvrage);


--
-- PostgreSQL database dump complete
--

