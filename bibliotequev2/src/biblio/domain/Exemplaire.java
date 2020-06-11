package biblio.domain;
import java.time.LocalDate;

public class Exemplaire 
{
	private Integer idExemplaire;
	private LocalDate dateAchat;
	private EnumStatusExemplaire status=EnumStatusExemplaire.DISPONIBLE;
	private String isbn;
	private String titre;
	public  EmpruntEnCours empruntEnCours=null;

	// CONSTRUCTEURS
	public Exemplaire(Integer idExemplaire, LocalDate dateAchat, EnumStatusExemplaire status, String isbn,EmpruntEnCours empruntEnCours) {
		setIdExemplaire(idExemplaire);
		setDateAchat(dateAchat);
		setStatus(status);
		setIsbn(isbn);
		setEmpruntEnCours(empruntEnCours);
	}
	
	public Exemplaire(Integer idExemplaire, LocalDate dateAchat, EnumStatusExemplaire status) {
		setIdExemplaire(idExemplaire);
		setDateAchat(dateAchat);
		setStatus(status);
		setIsbn(isbn);
		setEmpruntEnCours(empruntEnCours);
	}
	
	public Exemplaire(Integer idExemplaire, LocalDate dateAchat, EnumStatusExemplaire status, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
	}
	
	public Exemplaire(Integer idExemplaire, LocalDate dateAchat, EnumStatusExemplaire status, String isbn
			,EmpruntEnCours empruntEnCours,String titre) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
		setEmpruntEnCours(empruntEnCours);
		this.titre = titre;
	}
	
	
	public Exemplaire(Integer idExemplaire, LocalDate dateAchat, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.isbn = isbn;
	}
	
	public Exemplaire(Integer idExemplaire, String titre, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.titre = titre;
		this.isbn = isbn;
	}

	public Exemplaire() {
		super();
	}	

	
	public Exemplaire(Integer idExemplaire, String titre) {
		super();
		this.idExemplaire = idExemplaire;
	}

	// GETTERS SETTERS
	public Integer getIdExemplaire() {
		return idExemplaire;
	}

public LocalDate getDateAchat() {
	return dateAchat;
}

	public EnumStatusExemplaire getStatus() {
		return status;
	}

	public String getIsbn() {
		return isbn;
	}

	public EmpruntEnCours getEmpruntEnCours() {
		return empruntEnCours;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public void setIdExemplaire(Integer idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public void setStatus(EnumStatusExemplaire status) {
		this.status = status;

	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	} 

	public void setEmpruntEnCours(EmpruntEnCours EmpruntEnCours) {
			this.empruntEnCours = EmpruntEnCours;
			setStatus(EnumStatusExemplaire.PRETE);
	}

	@Override
	public String toString() {
		return "id: " + " "+ idExemplaire  + " " + "  " + "Titre: " + " " + titre + " ISBN: " + isbn ;
	}

	public void removeEmpruntEnCours(EmpruntEnCours empruntencours) {
		empruntencours.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
		empruntencours.getEmprunteur().getEmpruntEnCours().remove(empruntencours);
		new EmpruntArchive (LocalDate.now(),empruntencours.getDateEmprunt(),empruntencours.getEmprunteur(),empruntencours.getExemplaire());
		empruntencours = null;
	}

	public String getTitre() {
		return titre;
	}
}