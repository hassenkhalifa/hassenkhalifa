package biblio.test;

import java.sql.Connection;
import java.sql.SQLException;
import Connection.ConnectionFactory;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;

public class Test1_1_DemandeDesObjetsAuxDAO {
	/**
	 * @author hassenkhalifa
	 */
	public static void main(String[] args) throws SQLException {
		ConnectionFactory cn = new ConnectionFactory();
		Connection connection = cn.getConnection("jdbc_biblio@localhost.properties");
		UtilisateursDao ud = new UtilisateursDao(connection);	
		ExemplaireDao ed = new ExemplaireDao(connection);
		
		//Adherent a1 = (Adherent) ecd.findByKey(21);
		
		System.out.println("Demande de deux exemplaires par leur id aux Dao:");	
		System.out.println(ed.findByKey(1)+ "\n" + ed.findByKey(2) );	
		System.out.println("Demande d'un adhérent par son id aux DAO:");	
		System.out.println(ud.findByKey(1) +"\n");	
		System.out.println("Demande d'un employé par son id aux DAO:");	
		System.out.println(ud.findByKey(2));		
		
	}
}
