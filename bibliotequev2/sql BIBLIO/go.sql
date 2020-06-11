--S'il y a un paramètre à ce fichier, alors il contient le chemin absolu
--
-- Redirection de la sortie vers un fichier de log
SPOOL biblio.log
-- echo des requêtes dans SQL*Plus
SET ECHO ON
-- pas d'affichage des substitutions de variable
SET VERIFY OFF
-- sortir en cas d'erreur
@install_biblio
@install_data_biblio
PROMPT     FIN NORMALE DU SCRIPT
-- fermeture du fichier log
spool off
set echo off