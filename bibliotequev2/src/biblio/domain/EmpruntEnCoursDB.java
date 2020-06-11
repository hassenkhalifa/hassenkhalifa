package biblio.domain;

import java.time.LocalDate;

import biblio.exception.BiblioException;

public class EmpruntEnCoursDB extends EmpruntEnCours {
	@Override
	public String toString() {
		 return super.toString() + " idExemplaire: " + idExemplaire + ",  idUtilisateur: " + idUtilisateur;
	}

	private Integer idExemplaire;
	private Integer idUtilisateur;

	public EmpruntEnCoursDB(LocalDate dateEmprunt, Exemplaire exemp, Utilisateur ut, Integer idExemplaire,
			Integer idUtilisateur) throws BiblioException {
		super(ut, exemp, dateEmprunt);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}

	public EmpruntEnCoursDB(Integer idUtilisateur, Integer idExemplaire, LocalDate dateEmprunt) {
		super(dateEmprunt);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
}
