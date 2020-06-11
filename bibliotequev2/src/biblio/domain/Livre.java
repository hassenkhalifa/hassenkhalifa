package biblio.domain;

import java.util.Date;
public class Livre {

	private String isbn;
	private String titre;
	private Date parution;
	private int nbPages;

	public Livre(String isbn, String titre, Date parution, int nbPages) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.parution = parution;
		this.nbPages = nbPages;
	}
	
	

}
