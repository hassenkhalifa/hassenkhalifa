/*==============================================================*/
/* Nom de la base :  biblio                                      */
/* Nom de SGBD :  ORACLE                              */
/*==============================================================*/

-- Comportement par défaut: ne pas sortir en cas d'erreur SQL
WHENEVER SQLERROR continue none

Prompt ****************************************
Prompt SUPPRESSION DES TABLES, VUES , SEQUENCES
Prompt ****************************************

DROP sequence seq_auteur;
DROP sequence seq_utilisateur;
DROP sequence seq_editeur;
DROP sequence seq_archive;
DROP sequence seq_exemplaire;

DROP TABLE Auteur_livre cascade constraint;
DROP TABLE EmpruntArchive cascade constraint;
DROP TABLE EmpruntEncours cascade constraint;
DROP TABLE Adherent cascade constraint;
DROP TABLE Editeur cascade constraint;
DROP TABLE Livre cascade constraint;
DROP TABLE Employe cascade constraint;
DROP TABLE Exemplaire cascade constraint;
DROP TABLE Utilisateur cascade constraint;
DROP TABLE Theme cascade constraint;
DROP TABLE Auteur cascade constraint;
DROP TABLE AdherentGeneral cascade constraint;

purge recyclebin;

prompt
prompt
prompt
prompt ***********************
prompt Reste-t-il des objets ?
prompt ***********************
select * from cat;

