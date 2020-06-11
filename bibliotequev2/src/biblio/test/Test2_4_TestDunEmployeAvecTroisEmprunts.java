package biblio.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import Connection.ConnectionFactory;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Employe;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.exception.BiblioException;

public class Test2_4_TestDunEmployeAvecTroisEmprunts {
	/**
	 * @author hassenkhalifa
	 */
	public static void main(String[] args) throws SQLException, BiblioException {
		ConnectionFactory cn = new ConnectionFactory();
		Connection connection = cn.getConnection("jdbc_biblio@localhost.properties");
		UtilisateursDao ud = new UtilisateursDao(connection);	
		ExemplaireDao ed = new ExemplaireDao(connection);
		
		Employe employe = (Employe) ud.findByKey(2);
		Exemplaire exemplaire = ed.findByKey(4);
		Exemplaire exemplaire2 = ed.findByKey(2);		
		Exemplaire exemplaire3 = ed.findByKey(5);
		Exemplaire exemplaire4 = ed.findByKey(7);
		System.out.println("Status des exemplaires:");
		System.out.println(exemplaire.getStatus());
		System.out.println(exemplaire2.getStatus());
		System.out.println(exemplaire3.getStatus());
		System.out.println(exemplaire4.getStatus());
		
		// Creation d'un emprunt > 15 jours
		EmpruntEnCoursDB emprunt = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire, employe, 
				exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
				employe.addEmpruntEnCours(emprunt);		
		System.out.println("Emprunt en cours de l'employe:");			
		System.out.println(employe.getEmpruntEnCours());	
		
		EmpruntEnCoursDB emprunt2 = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire2, employe, 
				exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
				employe.addEmpruntEnCours(emprunt2);	
				
		EmpruntEnCoursDB emprunt3 = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire3, employe, 
				exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
				employe.addEmpruntEnCours(emprunt3);	
						
				EmpruntEnCoursDB emprunt4 = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire4, employe, 
						exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
						employe.addEmpruntEnCours(emprunt4);				
				
				System.out.println("Status des exemplaires apres création de l'emprunt:");
				System.out.println(exemplaire.getStatus());
				System.out.println(exemplaire2.getStatus());
				System.out.println(exemplaire3.getStatus());
				System.out.println(exemplaire4.getStatus());
				System.out.println("nombre d'emprunt de l'employe: " + employe.getEmpruntEnCours().size());
	}
}
