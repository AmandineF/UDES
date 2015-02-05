/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 130 638
 */
import java.util.LinkedList;

public class Enregistrement {
        public static final int MAXCARTES = 20;
        
          /**
     * La liste de carte d'un enregistrement.
     * 
     * @see Enregistrement#getListeCarte()
     */
     private LinkedList<Carte> listeCarte = new LinkedList<Carte>();
        
          /**
     * Le joueur correspondant � l'enregistrement
     * 
     * @see Enregistrement#getJoueur()
     * @see Enregistrement#setJoueur(Joueur)
     */
     private Joueur j = new Joueur();
     
        /**
     * Retourne le joueur correspondant � l'enregistrement.
     * 
     * @return le joueur correspondant � l'enregistrement. 
     */
     public Joueur getJoueur(){
          return this.j;
     }
        
         /**
     * Met � jour le joueur correspondant � l'enregistrement.
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
     
        /**
     * R�initialise la liste de carte de l'enregistrement
     */
        public void reinitialiseListe() {
            this.listeCarte = new LinkedList<Carte>();
        }
        
              /**
     * Donne la longueur de la lsite de carte de l'enregistrement courant
     * @return int la longueur de la liste
     */  
     public int getLongueurListe(){
          return listeCarte.size();
     }
}