package biblio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;

public class ExemplaireDao {	
	private Connection connection;
	
	public ExemplaireDao(Connection connection) {
		this.connection = connection;
	}
	
	public Exemplaire findByKey(Integer id) throws SQLException {			
			PreparedStatement pstm;
			
			Exemplaire exemplaire = null;
			Integer idExemplaire = null;
			LocalDate dateAchat = null;
			String status = null;
			String Isbn = "";
			String titre="";
			
			try { 
				pstm = connection					
						.prepareStatement("select exemplaire.idexemplaire, exemplaire.dateachat,"
				+ "exemplaire.status, exemplaire.isbn, livre.isbn, livre.titre from exemplaire,"
				+ "livre where exemplaire.idexemplaire=? and exemplaire.isbn = livre.isbn");
				
			  	pstm.setInt(1, id);
				ResultSet result = pstm.executeQuery();
				while (result.next()) {
					idExemplaire = result.getInt(1);
					Date d = result.getDate(2);
					dateAchat = d.toLocalDate();
					status = result.getString(3);
					titre = result.getString(6);
					EnumStatusExemplaire status2 = EnumStatusExemplaire.valueOf(status);	
					Isbn = result.getString(4);
					
				exemplaire = new Exemplaire(idExemplaire, dateAchat, status2, Isbn, null, titre);					
				}
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return exemplaire;
		}	
	
	public ArrayList<Exemplaire> findAllExemplaires() {

		PreparedStatement pstm;
		
		Exemplaire exemplaire = null;
		Integer idExemplaire = null;
		String titre = "";
		String isbn="";
		ArrayList<Exemplaire> Exemplaires = new ArrayList<Exemplaire>();
		
		try { 
			
			pstm = connection					
					.prepareStatement("SELECT exemplaire.idexemplaire,livre.titre, "
							+ "exemplaire.dateachat, exemplaire.isbn, exemplaire.status"
							+ " from exemplaire join livre on exemplaire.isbn = livre.isbn"
							+ " and exemplaire.status = 'DISPONIBLE' ");	
			
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				idExemplaire = result.getInt(1);
				titre = result.getString(2);
				isbn = result.getString(4);
				exemplaire = new Exemplaire(idExemplaire,titre, isbn);
				Exemplaires.add(exemplaire);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Exemplaires;
	}	
	
	public ArrayList<Exemplaire> findAllExemplairesPrete() {

		PreparedStatement pstm;
		String titre = "";
		String isbn="";
		Exemplaire exemplaire = null;
		Integer idExemplaire = null;
		ArrayList<Exemplaire> Exemplaires = new ArrayList<Exemplaire>();
		
try { 
			
			pstm = connection					
					.prepareStatement("SELECT exemplaire.idexemplaire,livre.titre, "
							+ "exemplaire.dateachat, exemplaire.isbn, exemplaire.status"
							+ " from exemplaire join livre on exemplaire.isbn = livre.isbn"
							+ " and exemplaire.status = 'PRETE' ");	
			
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				idExemplaire = result.getInt(1);
				titre = result.getString(2);
				isbn = result.getString(4);
				exemplaire = new Exemplaire(idExemplaire,titre, isbn);
				Exemplaires.add(exemplaire);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Exemplaires;
	}
}
