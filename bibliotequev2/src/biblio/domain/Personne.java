package biblio.domain;
import java.time.LocalDate;

public class Personne {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String sexe;   
    
    // CONSTRUCTEURS
	public Personne(String nom, String prenom, LocalDate dateNaissance, String sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}
	
	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne() {
		super();
	}	

	// GETTER
    public String getNom() {
    	return nom;
    	}
	public String getPrenom() {
		return prenom;
		}
	public LocalDate getDateNaissance() {
		return dateNaissance;
		}
	public String getSexe() {
		return sexe;
		}
	
   //	SETTER
	public void setNom(String nom) {
		this.nom = nom;
		}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
		}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
		} 
	public void setSexe(String sexe) {
		this.sexe = sexe;
		}

		@Override
		public String toString() {
			return "[nom=" + nom + ", prenom=" + prenom + "]";
		}	
}