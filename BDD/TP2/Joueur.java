
public class Joueur {
	 private char cle[] = new char[16]; /* cl� d'enregistrement du joueur. Taille max 16*/
	 private char nomJoueur[] = new char[128];    /* nom du joueur qui appara�t sur la carte.Taille max 128 caract�res*/
	 private int nombreCartes; /* Indique combien de cartes de ce joueur il y en a maximum 20 */

	 
	 public String getCle(){
		 String s = new String(this.cle);
		 return s;
	 }
	 public void setCle(String clef){
		 this.cle = clef.toCharArray();
	 }
	 
	 public String getNomJoueur(){
		 String s = new String(this.nomJoueur);
		 return s;
	 }
	 public void setNomJoueur(String nom){
		 this.nomJoueur = nom.toCharArray();
	 }
	 public int getNombreCartes(){
		 return this.nombreCartes;
	 }
	 public void setNombreCartes(int nb){
		 this.nombreCartes = nb;
	 }
}
