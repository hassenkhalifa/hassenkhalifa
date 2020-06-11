package biblio.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import Connection.ConnectionFactory;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.exception.BiblioException;

public class Test1_3_CreationDunEmprpuntEnCoursPourUnAdherent {
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
		//Ajout d'un exemplaire non disponible
		//Exemplaire exemplaire = ed.findByKey(26);
		EmpruntEnCoursDB emprunt = new  EmpruntEnCoursDB(LocalDate.now(),exemplaire, adherent, 
		exemplaire.getIdExemplaire(), adherent.getIdUtilisateur());	
		adherent.addEmpruntEnCours(emprunt);
		
		System.out.println("Emprunt en cours de l'adhérent:");			
		System.out.println(adherent.getEmpruntEnCours());		
		
	}
}
