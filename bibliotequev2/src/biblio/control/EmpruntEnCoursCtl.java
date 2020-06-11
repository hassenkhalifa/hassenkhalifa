package biblio.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import biblio.domain.EmpruntEnCours;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;
import biblio.exception.BiblioException;


public interface EmpruntEnCoursCtl {

	boolean insertEmpruntEnCours(EmpruntEnCours empruntEnCours)
			throws SQLException;

	EmpruntEnCoursDB findByKey(int idExemplaire)
			throws SQLException, BiblioException, IOException;

	ArrayList<EmpruntEnCoursDB> findAll()
			throws SQLException, BiblioException, IOException;

	ArrayList<Integer> ListAllEmpruntEnCours()
			throws SQLException, BiblioException, IOException;

	ArrayList<EmpruntEnCoursDB> findByUtilisateur(Utilisateur user)
			throws SQLException, BiblioException, IOException;

	boolean deleteEmpruntEnCours(int idExemplaire) throws SQLException;

}