/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 000 000
 */
public class Carte {
	/**
     * Le titre de la carte. Taille max 50.
     * 
     * @see Carte#getTitreCarte()
     * @see Carte#setTitreCarte(String)
     */
	 private char titreCarte [] = new char[50];
	 /**
     * Le nom de l’équipe à laquelle le jouer appartientsur cette carte. Taille max 30.
     * 
     * @see Carte#getNomEquipe()
     * @see Carte#setNomEquipe(String)
     */
	 private char nomEquipe [] = new char[30];
	 /**
     * L'annee de sortie de la carte.
     * 
     * @see Carte#getAnneeSortie()
     * @see Carte#setAnneeSortie(int)
     */
	 private int anneeSortie; 
	 
	 /**
      * Constructeur Carte.
      * 
      * @param titre
      *            le titre de la carte.
      * @param nom
      *            Le nom de l'équipe.
      * @param annee
      *            L'annee de parution.
      * @see Zero#titreCarte[]
      * @see Zero#nomEquipe[]
      * @see Zero#anneeSortie
      */
	 public Carte(String titre, String nom, int annee){
		 char[] tmp = titre.toCharArray();
		 this.titreCarte = tmp;
		 tmp = nom.toCharArray();
		 this.nomEquipe = tmp;
		 this.anneeSortie = annee;
	 }
	 
	 /**
      * Retourne le titre de la carte.
      * 
      * @return le titre de la carte.
      */
	 public String getTitreCarte(){
		 String s = new String(this.titreCarte);
		 return s;
	 }
	 
	 /**
      * Met à jour le titre de la carte.
      * 
      * @param titre
      *            Le nouveau titre de la carte.
      */
	 public void setTitreCarte(String titre){
		 this.titreCarte = titre.toCharArray();
	 }
	 
	 /**
      * Retourne le nom de l'équipe.
      * 
      * @return le nom de l'équipe.
      */
	 public String getNomEquipe(){
		 String s = new String(this.nomEquipe);
		 return s;
	 }
	 
	 /**
      * Met à jour le nom de l'équipe.
      * 
      * @param nom
      *            Le nouveau nom de l'équipe.
      */
	 public void setNomEquipe(String nom){
		 this.nomEquipe = nom.toCharArray();
	 }
	 
	 /**
      * Retourne l'annee de parution.
      * 
      * @return l'annee de parution.
      */
	 public int getAnneeSortie(){
		 return this.anneeSortie;
	 }
	 
	 /**
      * Met à jour l'annee de parution..
      * 
      * @param an
      *            La nouvelle annee de parution.
      */
	 public void setAnneeSortie(int an){
		 this.anneeSortie = an;
	 }
	 
	 /**
      * Affiche les caractéristiques d'une carte.
      * 
      * @return le String d'affichage des caractéristiques de la carte.
      */
	 public String toString(){
		 String s = "Titre : "+ this.getTitreCarte()+ "\nEquipe : " + this.getNomEquipe() + "\nAnnée de parution : " + this.getAnneeSortie()+"\n";
		 return s;
	 }
}
