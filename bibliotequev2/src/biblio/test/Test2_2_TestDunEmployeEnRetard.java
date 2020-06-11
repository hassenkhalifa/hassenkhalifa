package biblio.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import Connection.ConnectionFactory;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Employe;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.exception.BiblioException;

public class Test2_2_TestDunEmployeEnRetard {
	/**
	 * @author hassenkhalifa
	 */
	public static void main(String[] args) throws SQLException, BiblioException {
		ConnectionFactory cn = new ConnectionFactory();
		Connection connection = cn.getConnection("jdbc_biblio@localhost.properties");
		UtilisateursDao ud = new UtilisateursDao(connection);	
		ExemplaireDao ed = new ExemplaireDao(connection);
		//EmpruntEnCoursDao emc = new EmpruntEnCoursDao(connection);
		
		Employe employe = (Employe) ud.findByKey(2);
		
		Exemplaire exemplaire = ed.findByKey(4);
		Exemplaire exemplaire2 = ed.findByKey(2);		
		
		System.out.println("Status des exemplaires:");
		System.out.println(exemplaire.getStatus());
		System.out.println(exemplaire2.getStatus());
		
		// Creation d'un emprunt > 15 jours
		
		EmpruntEnCoursDB emprunt = new  EmpruntEnCoursDB(LocalDate.of(2018, Month.JANUARY, 12),exemplaire, employe, 
				exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
		employe.addEmpruntEnCours(emprunt);		
		
		System.out.println("Emprunt en cours de l'employe:");			
		System.out.println(employe.getEmpruntEnCours());	
		
		System.out.println("nombre d'emprunts:" + employe.getEmpruntEnCours().size());
		
		EmpruntEnCoursDB emprunt2 = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire2, employe, 
				exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
		employe.addEmpruntEnCours(emprunt2);	
		
		System.out.println("nombre d'emprunts apres ajout:" +employe.getEmpruntEnCours().size());	
		System.out.println("Status des exemplaires apres création de l'emprunt:");
		System.out.println(exemplaire.getStatus());
		System.out.println(exemplaire2.getStatus());
	}
}
