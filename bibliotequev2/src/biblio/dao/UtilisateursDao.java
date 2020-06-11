package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import biblio.domain.Adherent;
import biblio.domain.Employe;
import biblio.domain.EnumCategorieEmploye;
import biblio.domain.Utilisateur;

public class UtilisateursDao {

	private Connection connection;
	
	public UtilisateursDao(Connection connection) {
		this.connection = connection;
	}

	public Utilisateur findByKey(Integer idutilisateur) {
		PreparedStatement pstm;
		Utilisateur user = null;
		Integer id;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try { 
			pstm = connection					
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, "
							+ "utilisateur.prenom, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
		   	pstm.setInt(1, idutilisateur);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id, pwd, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}
			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public String findcatbykey(Integer idutilisateur) {
		PreparedStatement pstm;
		@SuppressWarnings("unused")
		Utilisateur user = null;
		Integer id;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try { 
			pstm = connection					
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, "
							+ "utilisateur.prenom, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
		   	pstm.setInt(1, idutilisateur);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id, pwd, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}
				if (cat.equals("GESTIONNAIRE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}
			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	
}
