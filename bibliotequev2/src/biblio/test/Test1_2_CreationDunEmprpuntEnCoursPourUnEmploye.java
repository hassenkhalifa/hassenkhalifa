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

public class Test1_2_CreationDunEmprpuntEnCoursPourUnEmploye {
	/**
	 * @author hassenkhalifa
	 */
	public static void main(String[] args) throws SQLException, BiblioException {
		ConnectionFactory cn = new ConnectionFactory();
		Connection connection = cn.getConnection("jdbc_biblio@localhost.properties");
		UtilisateursDao ud = new UtilisateursDao(connection);	
		ExemplaireDao ed = new ExemplaireDao(connection);
		
		Employe employe = (Employe) ud.findByKey(2);
		Exemplaire exemplaire = ed.findByKey(2);	
		//Ajout d'un exemplaire non disponible
	   //Exemplaire exemplaire = ed.findByKey(28);
		EmpruntEnCoursDB emprunt = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire, employe, 
		exemplaire.getIdExemplaire(), employe.getIdUtilisateur());	
		employe.addEmpruntEnCours(emprunt);		
		System.out.println("Emprunt en cours de l'employé:");			
		System.out.println(employe.getEmpruntEnCours());		
		
	}
}
