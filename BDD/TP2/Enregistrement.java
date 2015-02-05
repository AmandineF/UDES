/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 000 000
 */

import java.util.LinkedList;

public class Enregistrement {
	/**
     * La liste de carte d'un enregistrement.
     * 
     * @see Enregistrement#getListeCarte()
     */
	private LinkedList<Carte> listeCarte = new LinkedList<Carte>();
	/**
     * Le joueur correspondant à l'enregistrement
     * 
     * @see Enregistrement#getJoueur()
     * @see Enregistrement#setJoueur(Joueur)
     */
	private Joueur j = new Joueur();
	
	 /**
     * Retourne le joueur correspondant à l'enregistrement.
     * 
     * @return le joueur correspondant à l'enregistrement. 
     */
	public Joueur getJoueur(){
		return this.j;
	}
	
	 /**
     * Met à jour le joueur correspondant à l'enregistrement.
     * 
     * @param joueur
     *            Le nouveau joueur.
     */
	public void setJoueur(Joueur joueur){
		this.j = joueur;
	}
	
	/**
     * Retourne la liste de carte de l'enregistrement courant.
     * 
     * @return la liste de carte de l'enregistrement courant.
     */
	public LinkedList<Carte> getListeCarte(){
		return this.listeCarte;
	}
	
}
