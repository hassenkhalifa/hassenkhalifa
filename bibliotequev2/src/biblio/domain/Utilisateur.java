package biblio.domain;

import java.time.LocalDate;
import java.util.ArrayList;

import biblio.exception.BiblioException;

public class Utilisateur extends Personne {

	private Integer idUtilisateur;
	private String pwd;
	private String pseudonyme;
	protected ArrayList<EmpruntEnCours> empruntEnCours = new ArrayList<EmpruntEnCours>();

	// CONSTRUCTEURS
	public Utilisateur(String nom, String prenom, LocalDate dateNaissance, String sexe, Integer idUtilisateur,
			String pwd, String pseudonyme, ArrayList<EmpruntEnCours> empruntEnCours) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
		this.empruntEnCours = empruntEnCours;
	}

	public Utilisateur(String nom, String prenom, LocalDate dateNaissance, String sexe, Integer idUtilisateur,
			String pwd, String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	}
	
	public Utilisateur(String nom, String prenom, Integer idUtilisateur,
			String pwd) {
		super(nom, prenom);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
	}

	
	public Utilisateur() {
		super();
	}

	// GETTERS
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public String getPwd() {
		return pwd;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	// SETTERS
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public void setEmpruntEnCours(ArrayList<EmpruntEnCours> empruntEnCours) {
		this.empruntEnCours = empruntEnCours;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public void addEmpruntEnCours(EmpruntEnCours ep) throws BiblioException {
		this.empruntEnCours.add(ep);
	}

	public ArrayList<EmpruntEnCours> getEmpruntEnCours() {
		return this.empruntEnCours;
	}

	public Integer getNbEmpruntsEnCours() {
		return this.empruntEnCours.size();
	}

	public boolean isConditionsPretAcceptees() throws BiblioException {
		return true;
	}	

	@Override
	public String toString() {
		return super.toString() +"[idUtilisateur=" + idUtilisateur + "]";
	}
}
