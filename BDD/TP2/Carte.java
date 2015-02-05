
public class Carte {
	 private char titreCarte [] = new char[50];/* titre de la carte. Taille max 50*/
	 private char nomEquipe [] = new char[30]; /* Le nom de l’équipe à laquelle le jouer appartientsur cette carte */
	 private int anneeSortie; /* annee de sortie de la carte*/
	 
	 public Carte(String titre, String nom, int annee){
		 char[] tmp = titre.toCharArray();
		 this.titreCarte = tmp;
		 tmp = nom.toCharArray();
		 this.nomEquipe = tmp;
		 this.anneeSortie = annee;
	 }
	 
	 public String getTitreCarte(){
		 String s = new String(this.titreCarte);
		 return s;
	 }
	 public void setTitreCarte(String titre){
		 this.titreCarte = titre.toCharArray();
	 }
	 public String getNomEquipe(){
		 String s = new String(this.nomEquipe);
		 return s;
	 }
	 public void setNomEquipe(String nom){
		 this.nomEquipe = nom.toCharArray();
	 }
	 public int getAnneeSortie(){
		 return this.anneeSortie;
	 }
	 public void setAnneeSortie(int nb){
		 this.anneeSortie = nb;
	 }
	 
	 public String toString(){
		 String s = "Titre : "+ this.getTitreCarte()+ "\nEquipe : " + this.getNomEquipe() + "\nAnnée de parution : " + this.getAnneeSortie()+"\n";
		 return s;
	 }
}
