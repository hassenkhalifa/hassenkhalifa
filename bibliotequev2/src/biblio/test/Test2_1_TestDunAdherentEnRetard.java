package biblio.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import Connection.ConnectionFactory;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.exception.BiblioException;

public class Test2_1_TestDunAdherentEnRetard {
	/**
	 * @author hassenkhalifa
	 */
	public static void main(String[] args) throws SQLException, BiblioException {
		ConnectionFactory cn = new ConnectionFactory();
		Connection connection = cn.getConnection("jdbc_biblio@localhost.properties");
		UtilisateursDao ud = new UtilisateursDao(connection);	
		ExemplaireDao ed = new ExemplaireDao(connection);
		//EmpruntEnCoursDao emc = new EmpruntEnCoursDao(connection);
		
		Adherent adherent = (Adherent) ud.findByKey(1);
		Exemplaire exemplaire = ed.findByKey(2);
		Exemplaire exemplaire2 = ed.findByKey(4);		
		
		System.out.println("Status des exemplaires:");
		System.out.println(exemplaire.getStatus());
		System.out.println(exemplaire2.getStatus());
		
		// Creation d'un emprunt > 15 jours
		EmpruntEnCoursDB emprunt = new  EmpruntEnCoursDB(LocalDate.of(2020, Month.FEBRUARY, 17),exemplaire, adherent, 
				exemplaire.getIdExemplaire(), adherent.getIdUtilisateur());	
				adherent.addEmpruntEnCours(emprunt);		
		System.out.println("Emprunt en cours de l'adhérent:");			
		System.out.println(adherent.getEmpruntEnCours());	
		
		EmpruntEnCoursDB emprunt2 = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire2, adherent, 
				exemplaire.getIdExemplaire(), adherent.getIdUtilisateur());	
				adherent.addEmpruntEnCours(emprunt2);	
			//	System.out.println(adherent.getEmpruntEnCours());					
				
				System.out.println("Status des exemplaires apres création de l'emprunt:");
				System.out.println(exemplaire.getStatus());
				System.out.println(exemplaire2.getStatus());
	}
}
