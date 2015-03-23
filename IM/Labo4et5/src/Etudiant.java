/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.util.ArrayList;

public class Etudiant implements Observable {

	private String pseudo;

	private String prenom;
	private String nom;

	private String motDePasse;

	private Boolean estHomme;

	private Programme programme;

	private ArrayList<Cours> cours;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public Etudiant(String pseudo, String prenom, String nom,
			String motDePasse, Boolean estHomme, Programme programme) {
		super();
		this.pseudo = pseudo;
		this.prenom = prenom;
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.estHomme = estHomme;
		this.programme = programme;

		this.cours = new ArrayList<Cours>();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
		notifyObserver(nom);
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
		notifyObserver(prenom);
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
		}

	public Boolean getEstHomme() {
		return estHomme;
	}

	public void setEstHomme(Boolean estHomme) {
		this.estHomme = estHomme;
	}

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public ArrayList<Cours> getCours() {
		return cours;
	}

	public void setCours(ArrayList<Cours> cours) {
		this.cours = cours;
	}

	public void addCours(Cours cours) {
		this.cours.add(cours);
	}

	public void removeCours(Cours cours) {
		this.cours.remove(cours);
	}
	//Implémentation du pattern observer
	  public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	  }

	  public void notifyObserver(String str) {
	    for(Observer obs : listObserver)
	      obs.update(str);
	  }

	  public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }  
}
