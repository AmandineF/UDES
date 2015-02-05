/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 130 638
 */
public class Carte {

    public static final int MAXCARTE = 50;
    public static final int MAXNOMEQUIPE = 50;

    /**
    * Le titre de la carte
    * 
    * @see Carte#getTitreCarte()
    * @see Carte#setTitreCarte(String)
    */
    private char titreCarte [] = new char[MAXCARTE];
    
    /**
    * Le nom de l��quipe � laquelle le joueur appartient sur cette carte.
    * 
    * @see Carte#getNomEquipe()
    * @see Carte#setNomEquipe(String)
    */
    private char nomEquipe [] = new char[MAXNOMEQUIPE];
   
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
      *            Le nom de l'�quipe.
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
      * Met � jour le titre de la carte.
      * 
      * @param titre
      *            Le nouveau titre de la carte.
      */
    public void setTitreCarte(String titre){
            this.titreCarte = titre.toCharArray();
    }
    
    /**
      * Retourne le nom de l'�quipe.
      * 
      * @return le nom de l'�quipe.
      */
    public String getNomEquipe(){
            String s = new String(this.nomEquipe);
            return s;
    }
    
    /**
      * Met � jour le nom de l'�quipe.
      * 
      * @param nom
      *            Le nouveau nom de l'�quipe.
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
      * Met � jour l'annee de parution..
      * 
      * @param nb
      *            La nouvelle annee de parution.
      */
    public void setAnneeSortie(int nb){
            this.anneeSortie = nb;
    }

    /**
      * Affiche les caract�ristiques d'une carte.
      * 
      * @return le String d'affichage des caract�ristiques de la carte.
      */
    public String toString(){
            String s = "Titre : "+ this.getTitreCarte()+ "\nEquipe : " + this.getNomEquipe() + "\nAnn�e de parution : " + this.getAnneeSortie()+"\n";
            return s;
    }
}
