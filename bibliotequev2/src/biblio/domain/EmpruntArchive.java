package biblio.domain;
import java.time.LocalDate;

public class EmpruntArchive 
{
	private LocalDate dateEmprunt;
	private LocalDate dateRestitutionEff;
	private Utilisateur emprunteur;
	private Exemplaire exemplaire;

	public EmpruntArchive(LocalDate dateRestitutionEff, LocalDate dateEmprunt, Utilisateur emprunteur, Exemplaire exemplaire) {
		setDateRestitution(dateRestitutionEff);
		setDateEmprunt(dateEmprunt);
		setEmprunteur(emprunteur);
		setExemplaire(exemplaire);
	}

	public EmpruntArchive(LocalDate dateRestitutionEff, LocalDate dateEmprunt, Utilisateur emprunteur) {
		this(dateRestitutionEff, dateEmprunt, emprunteur, null);
	}


	public EmpruntArchive() {
		super();
	}

	public LocalDate getDateRestitution() {
		return dateRestitutionEff;
	}

	public void setDateRestitution(LocalDate dateRestitutionEff) {
		this.dateRestitutionEff = dateRestitutionEff;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
		}
	
	public Exemplaire getExemplaire() {
		return exemplaire;
		}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
		}

	@Override
	public String toString() {
		return "EmpruntArchive [dateEmprunt=" + dateEmprunt + ", dateRestitutionEff=" + dateRestitutionEff + ", emprunteur="
				+ emprunteur + ", exemplaire=" + exemplaire + "]";
	}

}
