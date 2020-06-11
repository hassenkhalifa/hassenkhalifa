package biblio.domain;

import java.time.LocalDate;
import java.util.ArrayList;

import biblio.exception.BiblioException;

public class Employe extends Utilisateur {	
	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	public String getCodeMatricule() {
		return codeMatricule;
	}
	
	// GETTERS SETTERS
	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}
	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}
	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}
	@Override
	public String toString() {
		return super.toString() + "Employe [codeMatricule=" + codeMatricule + ", categorieEmploye=" + categorieEmploye + "]";
	}
	
	//CONSTRUCTEURS
	
	public Employe(String nom, String prenom, LocalDate dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme, String codeMatricule,		EnumCategorieEmploye categorieEmploye) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;
	}	
	
	// (nom, prenom, id, pwd, code, cat2);	
	
	public Employe(String nom, String prenom, Integer idUtilisateur, 
			String pwd, String codeMatricule,EnumCategorieEmploye categorieEmploye) {	
		super(nom, prenom,  idUtilisateur, pwd);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;
	}	
	
	public Employe(String nom, String prenom, LocalDate dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme, ArrayList<EmpruntEnCours> empruntEnCours, String codeMatricule,
			EnumCategorieEmploye categorieEmploye) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, empruntEnCours);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;
	}		
	
	public Employe() {
		super();
	}
	
	public boolean isConditionsPretAcceptees() throws BiblioException{
	return true;
	}
	
	@Override
	public void addEmpruntEnCours(EmpruntEnCours EmpruntEnCours) {
		 this.empruntEnCours.add(EmpruntEnCours);
}	

}
