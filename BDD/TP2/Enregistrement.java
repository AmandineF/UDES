import java.util.LinkedList;

public class Enregistrement {
	private LinkedList<Carte> listeCarte = new LinkedList<Carte>();
	private Joueur j = new Joueur();
	
	public Joueur getJoueur(){
		return this.j;
	}
	public void setJoueur(Joueur joueur){
		this.j = joueur;
	}
	public LinkedList<Carte> getListeCarte(){
		return this.listeCarte;
	}
}
