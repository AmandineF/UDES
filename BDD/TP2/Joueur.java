/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 000 000
 */


public class Joueur {
	/**
     * La cl� d'enregistrement du joueur. Taille max 16.
     * 
     * @see Joueur#getCle()
     * @see Joueur#setCle()
     */
	 private char cle[] = new char[16];
	 /**
     * Le nom du joueur qui appara�t sur la carte.Taille max 128 caract�res.
     * 
     * @see Joueur#getNomJoueur()
     * @see Joueur#setNomJoueur(String)
     */
	 private char nomJoueur[] = new char[128]; 
	 /**
     * Indique combien de cartes de ce joueur il y en a maximum 20.
     * 
     * @see Joueur#getNombreCartes()
     * @see Joueur#setNombreCartes(int)
     */
	 private int nombreCartes; 

	 /**
      * Retourne la cl� d'identification du joueur sous le format String.
      * 
      * @return la cl� d'identification du joueur sous le format String. 
      */
	 public String getCle(){
		 String s = new String(this.cle);
		 return s;
	 }
	 
	 /**
      * Met � jour la cl� d'identification du joueur.
      * 
      * @param clef
      *            La nouvelle cl� d'identification du joueur.
      */
	 public void setCle(String clef){
		 this.cle = clef.toCharArray();
	 }
	 /**
      * Retourne le nom du joueur sous le format String.
      * 
      * @return le nom du joueur sous le format String. 
      */
	 public String getNomJoueur(){
		 String s = new String(this.nomJoueur);
		 return s;
	 }
	 
	 /**
      * Met � jour le nom du joueur.
      * 
      * @param nom
      *            Le nouveau nom du joueur.
      */
	 public void setNomJoueur(String nom){
		 this.nomJoueur = nom.toCharArray();
	 }
	 
	 /**
      * Retourne le nombre de cartes que poss�de le joueur.
      * 
      * @return le nombre de cartes que poss�de le joueur.
      */
	 public int getNombreCartes(){
		 return this.nombreCartes;
	 }
	 
	 /**
      * Met � jour le nombre de cartes que poss�de le joueur.
      * 
      * @param nom
      *            Le nouveau nombre de cartes que poss�de le joueur.
      */
	 public void setNombreCartes(int nb){
		 this.nombreCartes = nb;
	 }
}
