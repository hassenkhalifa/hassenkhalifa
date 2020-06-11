package biblio.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import biblio.exception.BiblioException;

public class Adherent extends Utilisateur {

	private String telephone;
	private static Integer nbMaxPrets = 3;
	private static Integer dureeMaxPrets = 15;

	public Adherent(String nom, String prenom, java.time.LocalDate dateNaissance, String sexe, Integer idUtilisateur,
			String pwd, String pseudonyme, ArrayList<EmpruntEnCours> empruntEnCours, String telephone) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, empruntEnCours);
		this.telephone = telephone;
	}

	public Adherent(String nom, String prenom, LocalDate dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme, String telephone) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.telephone = telephone;
	}

	public Adherent(String nom, String prenom, LocalDate dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
	}
	
	//new Adherent(nom, prenom, id, pwd, tel);
	public Adherent(String nom, String prenom,Integer idUtilisateur, String pwd, String telephone) {
		super(nom, prenom, idUtilisateur, pwd);
		telephone = this.telephone;
	}

	public Adherent() {
		super();
	}

	// GETTERS SETTERS
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public static Integer getNbMaxPrets() {
		return nbMaxPrets;
	}

	public static void setNbMaxPrets(Integer nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public static Integer getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public static void setDureeMaxPrets(Integer dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}

	public int getNbRetards() {
		int nbRetards = 0;
		for (EmpruntEnCours ep : this.getEmpruntEnCours()) {
			int duree = (int) ChronoUnit.DAYS.between(ep.getDateEmprunt(), LocalDate.now());
			if (duree > 15) {
				nbRetards++;
			}
		}
		return nbRetards;
	}

	@Override
	public boolean isConditionsPretAcceptees() throws BiblioException {
		if (this.getNbRetards() == 0 && this.getNbEmpruntsEnCours() < 3) {
			return true;
		} else {
			try {
				throw new BiblioException("pret non accordé");
			} catch (BiblioException e) {
				JOptionPane.showMessageDialog(null, "Conditions de prêt non acceptés");
			}
			return false;
		}
	}

	@Override
	public void addEmpruntEnCours(EmpruntEnCours EmpruntEnCours) throws BiblioException {
		if (isConditionsPretAcceptees()) {
			this.empruntEnCours.add(EmpruntEnCours);
		}
	}

	@Override
	public String toString() {
		return  super.toString();
	}


}
