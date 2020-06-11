delete from auteur_livre;
delete from EmpruntArchive;
delete from EmpruntEncours;
delete from Adherent;
delete from Employe;
delete from Utilisateur;
delete from Exemplaire;
delete from Livre;
delete from Editeur;
delete from Theme;
delete from Auteur;

insert into adherentgeneral values (3,15);
insert into theme values ('RO','roman',null);

insert into auteur values (seq_auteur.nextval,'KINDRED DICK','Philip');
insert into editeur values (seq_editeur.nextval,'Omnibus','Paris');
insert into theme values ('SF','Science-fiction','RO');
insert into utilisateur values (seq_utilisateur.nextval,'COGAN','Nicolas','101','cognico',to_date('21-03-1987' ,'DD-MM-YYYY' ),'H','ADHERENT');
insert into adherent values (seq_utilisateur.currval,'06 25 45 74 69');
insert into livre values (1520068789,'Siva',seq_editeur.currval,'SF',1973,0);
insert into auteur_livre values (1520068789,seq_auteur.currval,null);
insert into exemplaire values (seq_exemplaire.nextval,to_date('21-03-2001' ,'DD-MM-YYYY' ),'PRETE',1520068789);
insert into empruntencours values (seq_exemplaire.currval,seq_utilisateur.currval,to_date('20-10-2016' ,'DD-MM-YYYY' ));
insert into empruntarchive values (seq_archive.nextval,to_date('10-05-2015' ,'DD-MM-YYYY' ),to_date('18-05-2015' ,'DD-MM-YYYY' ),seq_exemplaire.currval,seq_utilisateur.currval);
insert into utilisateur values (seq_utilisateur.nextval,'DURIEUX','Elocia','100','durelo',to_date('12-05-1990' ,'DD-MM-YYYY' ),'F','EMPLOYE');
insert into employe values (seq_utilisateur.currval,'22','BIBLIOTHECAIRE');

insert into utilisateur values (seq_utilisateur.nextval,'ROUSSEL','Sylvain','122','rousyl',to_date('21-03-1980' ,'DD-MM-YYYY' ),'H','EMPLOYE');
insert into employe values (seq_utilisateur.currval,'19','GESTIONNAIRE');
insert into livre values (2320063289,'Ubik',seq_editeur.currval,'SF',1969,0);
insert into auteur_livre values (2320063289,seq_editeur.currval,null);
insert into exemplaire values (seq_exemplaire.nextval,to_date('19-11-1999' ,'DD-MM-YYYY' ),'DISPONIBLE',2320063289);
insert into empruntarchive values (seq_archive.nextval,to_date('10-03-2015' ,'DD-MM-YYYY' ),to_date('02-03-2015' ,'DD-MM-YYYY' ),seq_exemplaire.currval,seq_utilisateur.currval);

insert into utilisateur values (seq_utilisateur.nextval,'ROVIRAT','Elodie','107','rovelo',to_date('21-03-1991' ,'DD-MM-YYYY' ),'F','ADHERENT');
insert into adherent values (seq_utilisateur.currval,'06 63 56 21 85');
insert into livre values (3000066589,'Radio libre Albemuth',seq_editeur.currval,'SF',1972,0);
insert into auteur_livre values (3000066589,seq_editeur.currval,null);
insert into exemplaire values (seq_exemplaire.nextval,to_date('25-11-2003' ,'DD-MM-YYYY' ),'PRETE',3000066589);
insert into empruntencours values (seq_exemplaire.currval,seq_utilisateur.currval,to_date('22-10-2017' ,'DD-MM-YYYY' ));

insert into auteur values (seq_auteur.nextval,'KING','Stephen');
insert into editeur values (seq_editeur.nextval,'10/18','Paris');
insert into theme values ('HO','Horreur','RO');

insert into utilisateur values (seq_utilisateur.nextval,'BUSSON','Guillaume','102','busgui',to_date('21-02-1988' ,'DD-MM-YYYY' ),'H','ADHERENT');
insert into adherent values (seq_utilisateur.currval,'06 32 46 89 12');

insert into utilisateur values (seq_utilisateur.nextval,'GALLAND','Aurelien','99','galaur',to_date('21-09-1984' ,'DD-MM-YYYY' ),'H','EMPLOYE');
insert into employe values (seq_utilisateur.currval,'23','RESPONSABLE');
insert into livre values (1630014789,'Cujo',seq_editeur.currval,'HO',1979,0);
insert into auteur_livre values (1630014789,seq_auteur.currval,null);
insert into exemplaire values (seq_exemplaire.nextval,to_date('15-04-2000' ,'DD-MM-YYYY' ),'DISPONIBLE',1630014789);
insert into empruntarchive values (seq_archive.nextval,to_date('03-08-2015' ,'DD-MM-YYYY' ),to_date('23-07-2015' ,'DD-MM-YYYY' ),seq_exemplaire.currval,seq_utilisateur.currval);

insert into livre values (3236506659,'Le pistolero',seq_editeur.currval,'HO',1968,0);
insert into auteur_livre values (3236506659,seq_editeur.currval,null);
insert into exemplaire values (seq_exemplaire.nextval,to_date('02-06-2000' ,'DD-MM-YYYY' ),'DISPONIBLE',3236506659);
insert into empruntarchive values (seq_archive.nextval,to_date('03-11-2015' ,'DD-MM-YYYY' ),to_date('25-10-2015' ,'DD-MM-YYYY' ),seq_exemplaire.currval,1);

insert into utilisateur values (seq_utilisateur.nextval,'COCOT','Vincent','108','cocvinc',to_date('03-11-2001' ,'DD-MM-YYYY' ),'H','ADHERENT');
insert into adherent values (seq_utilisateur.currval,'06 69 45 78 22');
insert into livre values (3200066559,'Le fleau',seq_editeur.currval,'HO',1975,0);
insert into auteur_livre values (3200066559,seq_editeur.currval,null);
insert into exemplaire values (seq_exemplaire.nextval,to_date('19-03-2000' ,'DD-MM-YYYY' ),'PRETE',3200066559);
insert into empruntencours values (seq_exemplaire.currval,seq_utilisateur.currval,to_date('25-10-2017' ,'DD-MM-YYYY' ));

insert into auteur values (seq_auteur.nextval,'BUKOWSKI','Charles');

insert into utilisateur values (seq_utilisateur.nextval,'Zuccarelli','Ange','101','angel46',to_date('07-08-1987' ,'DD-MM-YYYY' ),'H','ADHERENT');
insert into adherent values (seq_utilisateur.currval,'06 00000');
insert into exemplaire values (seq_exemplaire.nextval,to_date('21-03-2001' ,'DD-MM-YYYY' ),'DISPONIBLE',1520068789);

insert into utilisateur values (seq_utilisateur.nextval,'Retard','enretard','101','retend',to_date('07-08-1987' ,'DD-MM-YYYY' ),'H','ADHERENT');
insert into adherent values (seq_utilisateur.currval,'06 00000');

insert into exemplaire values (seq_exemplaire.nextval,to_date('21-03-2001' ,'DD-MM-YYYY' ),'PRETE',3200066559);
insert into empruntencours values (seq_exemplaire.currval,seq_utilisateur.currval,to_date('22-10-2017' ,'DD-MM-YYYY' ));

commit;
