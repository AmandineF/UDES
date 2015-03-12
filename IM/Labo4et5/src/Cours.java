
public class Cours {

	private String nom;
	private String identifiant;

	public Cours(String nom, String identifiant) {
		super();
		this.nom = nom;
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
}
