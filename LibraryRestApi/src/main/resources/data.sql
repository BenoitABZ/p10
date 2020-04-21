/*INSERT INTO BIBLIOTHEQUE (id_bibliotheque, code_postal, libelle_voie, nom_voie, numero_voie, ville, nom, telephone, mail, image) VALUES (1, '31000', 'Rue', 'Jean Jaures', '1', 'Toulouse', 'Bibliothèque municipale principale', '0552636547', 'bumup@ville.com','https://www.iphonote.com/wp-content/uploads/2019/05/apple-store-carnegie-washington_2.jpg');
INSERT INTO BIBLIOTHEQUE (id_bibliotheque, code_postal, libelle_voie, nom_voie, numero_voie, ville, nom, telephone, mail, image) VALUES (2, '31450', 'Rue', 'Perigord', '2', 'Toulouse', 'Mediathèque AM', '0541236585', 'mediathequeam@ville.com','https://www.aiguillon-construction.fr/wp-content/uploads/2017/09/Pho-AC-ECL-1-600x398.jpg');

INSERT INTO EMPRUNTEUR (id_emprunteur, code_postal, libelle_voie, nom_voie, numero_voie, ville, date_inscription, identifiant, adresse_mail, mot_de_passe, nom, prenom, telephone) VALUES (1,'31400', 'Rue', 'Bertholot', '25', 'Toulouse', '2020-03-08', 'babouzeid','benoit.abouzeid@gmail.com','poiuytreza31', 'Abouzeid','Benoit','0612332837');
INSERT INTO EMPRUNTEUR (id_emprunteur, code_postal, libelle_voie, nom_voie, numero_voie, ville, date_inscription, identifiant, adresse_mail, mot_de_passe, nom, prenom, telephone) VALUES (2,'31300', 'Rue', 'Jean Moulin', '17', 'Toulouse', '2020-03-08', 'mrais','meryem.rais@gmail.com','poiuytreza33', 'Rais','Meryem','0699150101');

INSERT INTO OUVRAGE (id_ouvrage, auteur, categorie, annee_parution, resume, titre, disponibilite, image) VALUES (1, 'Emile Zola', 'Biographie', '1898', 'Affaire Dreyfus', 'J''accuse', true, 'https://static.fnac-static.com/multimedia/FR/Images_Produits/FR/fnac.com/Visual_Principal_340/8/9/1/9782910233198/tsp20120925075349/J-accuse.jpg');
INSERT INTO OUVRAGE (id_ouvrage, auteur, categorie, annee_parution, resume, titre, disponibilite, image) VALUES (2, 'Jean Baptiste Molière', 'Comédie', '1973', 'Histoire d''un medecin amoureux', 'L''Amour medecin', true, 'https://static.fnac-static.com/multimedia/Images/FR/NR/85/58/26/2513029/1540-1/tsp20150210124012/L-amour-medecin-Le-sicilien-ou-L-amour-peintre.jpg');

INSERT INTO EXEMPLAIRE (id_exemplaire, annee_publication, editeur, id_bibliotheque, id_ouvrage) VALUES (1, 2015,'Hachette', 1, 2);
INSERT INTO EXEMPLAIRE (id_exemplaire, annee_publication, editeur, id_bibliotheque, id_ouvrage) VALUES (2, 2013,'Editions Confluences', 2, 1); 

INSERT INTO EMPRUNT (id_emprunt, date_emprunt, date_retour, prolongation, id_emprunteur, id_exemplaire) VALUES (1, '2020-01-05', '2020-03-05', false, 1, 1);
INSERT INTO EMPRUNT (id_emprunt, date_emprunt, date_retour, prolongation, id_emprunteur, id_exemplaire) VALUES (2, '2020-03-24', '2020-04-07', false, 1, 2); */


