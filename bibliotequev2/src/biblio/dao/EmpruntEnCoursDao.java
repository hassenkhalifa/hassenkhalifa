package biblio.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import biblio.domain.EmpruntEnCoursDB;
import biblio.exception.BiblioException;

public class EmpruntEnCoursDao {
	private Connection connection;

	public EmpruntEnCoursDao(Connection connection) {
		this.connection = connection; 
	}

	public EmpruntEnCoursDB findByKey(Integer idExemplaire) throws SQLException {
		Statement statement = connection.createStatement();
		@SuppressWarnings("unused")
		ResultSet resultSet = statement
				.executeQuery("SELECT * from empruntencours " + "where idexemplaire = " + idExemplaire);
		return null;
	}

	public ArrayList<EmpruntEnCoursDB> findAllByUtilisateur(Integer idUtilisateur) throws SQLException {
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT idexemplaire, idutilisateur, "
				+ "dateemprunt from EMPRUNTENCOURS where idutilisateur =" + idUtilisateur);
		ArrayList<EmpruntEnCoursDB> empruntEnCoursDB = new ArrayList<EmpruntEnCoursDB>();
		while (resultSet.next()) {
			LocalDate date = resultSet.getDate(3).toLocalDate();
			empruntEnCoursDB.add(new EmpruntEnCoursDB(resultSet.getInt(1), resultSet.getInt(2), date));
		}
		statement.close();
		return empruntEnCoursDB;
	}

	public void insertEmpruntEnCours(EmpruntEnCoursDB empruntEncours) throws SQLException {
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		Integer idexemplaire = empruntEncours.getIdExemplaire();
		Integer idutilisateur = empruntEncours.getIdUtilisateur();
		LocalDate dateemprunt = empruntEncours.getDateEmprunt();
		String date = dateemprunt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		statement.executeUpdate("INSERT INTO EMPRUNTENCOURS (idexemplaire, idutilisateur, dateemprunt)  VALUES " + "("
				+ idexemplaire + "," + idutilisateur + ", to_date('" + date + "' ,'DD-MM-YYYY'" + ")" + ")");
		statement.executeUpdate("update exemplaire set status='PRETE' "
				+ "where idexemplaire = " + idexemplaire);
		connection.commit();
	}


	public boolean deleteEmpruntEnCours(int idExemplaire) throws SQLException, BiblioException, IOException{		
		int sql =0;
		int idUtilisateur = 0;
		Statement stm = connection.createStatement();
		java.sql.Date dateEmprunt = null;
		ResultSet resultSet = stm.executeQuery("SELECT * from empruntencours "
				+ "where idexemplaire = " + idExemplaire);
		if (resultSet.next()) {
			dateEmprunt = resultSet.getDate("dateemprunt");
			idUtilisateur = resultSet.getInt("idutilisateur");
		}
		
		String query = "SELECT idexemplaire, idutilisateur, dateemprunt"
				+ " FROM empruntencours WHERE idexemplaire = " + idExemplaire;
		try {
			
			ResultSet result = stm.executeQuery(query);
			
			if (result.next()) {
				query = "DELETE FROM empruntencours WHERE idexemplaire = " + idExemplaire;
				stm.executeUpdate(query);	
				sql = stm.executeUpdate(query);
				stm.executeUpdate("update exemplaire set status='DISPONIBLE' "
						+ "where idexemplaire = " + idExemplaire);
				stm.executeUpdate( "insert into empruntarchive "
						+ "values (seq_archive.nextval, to_date('" + dateEmprunt + "','YYYY-MM-DD'), "
								+ "sysdate, " + idExemplaire + ", " + idUtilisateur + ")");
				connection.commit();
			}					
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sql == 1 ? true : false;
	}
	

}
