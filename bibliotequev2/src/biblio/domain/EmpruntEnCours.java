package biblio.domain;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import biblio.exception.BiblioException;

public class EmpruntEnCours {
	private LocalDate dateEmprunt;
	private Exemplaire exemp = null;
	private Utilisateur emprunteur;

	 //CONSTRUCTEURS
	public EmpruntEnCours(Utilisateur ut,  Exemplaire exemplaire ,LocalDate dateEmprunt) throws BiblioException {				
		
		if(exemplaire.getStatus() == EnumStatusExemplaire.DISPONIBLE && ut.isConditionsPretAcceptees()){
			this.emprunteur = ut;
			this.exemp = exemplaire;	
			exemplaire.setStatus(EnumStatusExemplaire.PRETE);
			this.dateEmprunt = dateEmprunt;	
		}			
	}
	
	public EmpruntEnCours() {
		super();
	}

	public EmpruntEnCours(LocalDate dateEmprunt) {
		this.dateEmprunt=dateEmprunt;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
		}

	public Exemplaire getExemplaire() {
		return exemp;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
		}
	
	public void setUtilisateur(Utilisateur u) throws BiblioException{		
		if(u != null){
			if(u.isConditionsPretAcceptees()){
				this.emprunteur = u;
				this.emprunteur.addEmpruntEnCours(this);
			}else{
				try {
					throw new BiblioException("Conditions de prêt non acceptés");
				} catch (BiblioException e) {
					JOptionPane.showMessageDialog(null, "Conditions de prêt non acceptés");				
		}		
	}}}
	
	/*public void setExemplaire(Exemplaire exemplaire)  throws BiblioException{
		if(exemplaire.getEmpruntEnCours()==null && exemplaire.getStatus()== EnumStatusExemplaire.DISPONIBLE){
			this.exemp = exemplaire;
			this.getExemplaire().setEmpruntEnCours(this);
		}
	else{
		try {
			throw new BiblioException("exemplaire indisponible");
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, "exemplaire indisponible");
		}		
		throw new BiblioException("Cet livre est indisponible à l'emprunt.");
	}
	}	*/
	
	public void setExemplaire(Exemplaire ex) throws BiblioException{
		if(ex != null){
			if(ex.getEmpruntEnCours() == null 
					|| ex.getStatus() == EnumStatusExemplaire.DISPONIBLE){
				this.exemp = ex;
				this.exemp.setEmpruntEnCours(this);
			}else{
				throw new BiblioException("Ce Livre n'est pas disponible à l'emprunt.");
			}
		}
	}

	
	public void setDateEmprunt(LocalDate dateEmprunt) {
	this.dateEmprunt=dateEmprunt;
	}

	@Override
	public String toString() {
		return "dateEmprunt:" + dateEmprunt;
	}
	
}
