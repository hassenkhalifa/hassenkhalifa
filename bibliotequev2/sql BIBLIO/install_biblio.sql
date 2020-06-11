--/*==============================================================*/
--/* Nom de la base :  BIBLIO                                      */
--/* Nom du fichier : install_biblio.sql                            */
--/* Nom de SGBD :  ORACLE                              */
--/*==============================================================*/

create sequence seq_auteur start with 1 increment by 1;
create sequence seq_exemplaire start with 1 increment by 1;
create sequence seq_editeur start with 1 increment by 1;
create sequence seq_archive start with 1 increment by 1;
create sequence seq_utilisateur start with 1 increment by 1;

CREATE TABLE Auteur_livre (
	isbn VARCHAR2 ( 16 ) NOT NULL,
	idAuteur Number ( 6 ) NOT NULL,
  ordreAuteur Number (2),
  CONSTRAINT CK_ordreAuteur CHECK (ordreAuteur > 0),
	CONSTRAINT PK_Ecrire50 PRIMARY KEY (idAuteur, isbn)
	);
CREATE TABLE Theme (
	codeTheme VARCHAR2 ( 2 ) NOT NULL,
	libelle VARCHAR2 ( 40 ) NOT NULL,
	theme_parent VARCHAR2 ( 2 ) ,
	CONSTRAINT PK_Theme42 PRIMARY KEY (codeTheme)
	);
CREATE TABLE Adherent (
	idUtilisateur NUMBER ( 6 ) NOT NULL,
	telephone VARCHAR2 ( 16 ) NOT NULL,
	CONSTRAINT PK_Adherent46 PRIMARY KEY (idUtilisateur)
	);
CREATE TABLE Editeur (
	idEditeur NUMBER ( 5 ) NOT NULL,
	nomEditeur VARCHAR2 ( 40 ) NOT NULL,
	ville VARCHAR2 ( 40 ) NOT NULL,
	CONSTRAINT PK_Editeur32 PRIMARY KEY (idEditeur)
	);
CREATE TABLE AdherentGeneral (
	nbMaxPrets NUMBER ( 1 ) NOT NULL,
	dureeMaxPrets NUMBER ( 2 ) NOT NULL
	);
CREATE TABLE Exemplaire (
	idExemplaire NUMBER ( 6 ) NOT NULL,
	dateAchat DATE NOT NULL,
	status VARCHAR2 ( 15 ) NOT NULL,
	isbn VARCHAR2 ( 16 ) NOT NULL,
	CONSTRAINT PK_Exemplaire53 PRIMARY KEY (idExemplaire),
	CONSTRAINT CK_status CHECK (status in ('PRETE' , 'DISPONIBLE', 'SUPPRIME'))
	);
CREATE TABLE Auteur (
	idAuteur NUMBER ( 6 ) NOT NULL,
	nom VARCHAR2 ( 40 ) NOT NULL,
	prenom VARCHAR2 ( 40 ) NOT NULL,
	CONSTRAINT PK_Auteur52 PRIMARY KEY (idAuteur)
	);
CREATE TABLE Employe (
	idUtilisateur NUMBER ( 6 ) NOT NULL,
	codeMatricule VARCHAR2 ( 15 ) NOT NULL,
	categorieEmploye VARCHAR2 ( 15 ) NOT NULL,
	CONSTRAINT PK_Employe33 PRIMARY KEY (idUtilisateur),
	CONSTRAINT TC_Employe147 UNIQUE (codeMatricule),
	CONSTRAINT CK_categorie_employe CHECK (categorieEmploye in ('BIBLIOTHECAIRE' , 'RESPONSABLE' , 'GESTIONNAIRE'))
	);
CREATE TABLE EmpruntArchive (
	idEmpruntArchive NUMBER ( 6 ) NOT NULL,
	dateEmprunt DATE NOT NULL,
	dateRestitutionEff DATE NOT NULL,
	idExemplaire NUMBER ( 6 ) NOT NULL,
	idUtilisateur NUMBER ( 6 ) NOT NULL,
	CONSTRAINT PK_EmpruntArchive51 PRIMARY KEY (idEmpruntArchive)
	);
CREATE TABLE Utilisateur (
	idUtilisateur NUMBER ( 6 ) NOT NULL,
	nom VARCHAR2 ( 40 ) NOT NULL,
	prenom VARCHAR2 ( 40 ) NOT NULL,
	pwd VARCHAR2 ( 15 ) NOT NULL,
	pseudonyme varchar2 (20) not null,
	datenaissance date not null,
	sexe varchar(1) not null,
	categorieutilisateur VARCHAR2 ( 15 ) NOT NULL,
	CONSTRAINT PK_Utilisateur44 PRIMARY KEY (idUtilisateur),
	CONSTRAINT CK_catgorie_utilisateur CHECK (categorieutilisateur in ('EMPLOYE' , 'ADHERENT')),
	CONSTRAINT CK_sexe CHECK (sexe in ('H' , 'F')),
	CONSTRAINT TC_pseudo UNIQUE (pseudonyme)
	);
CREATE TABLE Livre (
	isbn VARCHAR2 ( 16 ) NOT NULL,
	titre VARCHAR2 ( 40 ) NOT NULL,
	idEditeur NUMBER ( 5 ) NOT NULL,
	codeTheme VARCHAR2 ( 2 ) NOT NULL,
	anneeParution NUMBER ( 4 ) NOT NULL,
	nbPages NUMBER(5) NOT NULL,
	CONSTRAINT PK_Livre40 PRIMARY KEY (isbn),
	CONSTRAINT CK_annee_parution CHECK (anneeParution > 0),
	CONSTRAINT CK_Nb_Pages CHECK (nbPages >=0)
	);
CREATE TABLE EmpruntEncours (
	idExemplaire NUMBER ( 6 ) NOT NULL,
	idUtilisateur NUMBER ( 6 ) NOT NULL,
	dateEmprunt DATE NOT NULL,
	CONSTRAINT PK_EmpruntEncours54 PRIMARY KEY (idExemplaire)
	);
ALTER TABLE Auteur_livre ADD ( CONSTRAINT FK_Ecrire43 FOREIGN KEY (isbn) REFERENCES Livre (isbn));
ALTER TABLE Auteur_livre ADD ( CONSTRAINT FK_Auteur_livre46 FOREIGN KEY (idAuteur) REFERENCES Auteur (idAuteur));
ALTER TABLE Theme ADD ( CONSTRAINT FK_Theme45 FOREIGN KEY (theme_parent) REFERENCES Theme (codeTheme));
ALTER TABLE Adherent ADD ( CONSTRAINT FK_Adherent30 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
ALTER TABLE Exemplaire ADD ( CONSTRAINT FK_Exemplaire24 FOREIGN KEY (isbn) REFERENCES Livre (isbn));
ALTER TABLE Employe ADD ( CONSTRAINT FK_Employe33 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
ALTER TABLE EmpruntArchive ADD ( CONSTRAINT FK_EmpruntArchive44 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
ALTER TABLE EmpruntArchive ADD ( CONSTRAINT FK_EmpruntArchive48 FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire));
ALTER TABLE Livre ADD ( CONSTRAINT FK_Livre19 FOREIGN KEY (idEditeur) REFERENCES Editeur (idEditeur));
ALTER TABLE Livre ADD ( CONSTRAINT FK_Livre25 FOREIGN KEY (codeTheme) REFERENCES Theme (codeTheme));
ALTER TABLE EmpruntEncours ADD ( CONSTRAINT FK_EmpruntEncours47 FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire));
ALTER TABLE EmpruntEncours ADD ( CONSTRAINT FK_EmpruntEncours29 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));

prompt
prompt
prompt
Prompt ******************************
prompt Recapitulatif des Objets créés
Prompt ******************************
select * from user_catalog;
prompt
prompt
prompt
prompt ************************************
prompt Recapitulatif des contraintes pos?es
prompt ************************************

column constraint_name format A20
column type format A3
column table_name format A15
column SEARCH_CONDITION format A30

break on type skip 
compute count of constraint_name on type report
select  constraint_name, Constraint_type as type,
        table_name , SEARCH_CONDITION, DELETE_RULE, STATUS
from user_constraints
order by type, table_name;


clear columns
clear breaks
clear computes
