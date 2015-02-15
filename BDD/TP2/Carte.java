package bdd2;
/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 130 638
*/
public class Carte {
    
    /**
     * La nombre maximal de carte
    */
    private static final int MAXTITRE = 50;
    
    /**
     * La taille maximale du nom d'équipe
    */
    private static final int MAXNOMEQUIPE = 50;

    /**
     * Le titre de la carte
     * 
     * @see Carte#getTitreCarte()
     * @see Carte#setTitreCarte(String)
    */
    private char titreCarte [] = new char[MAXTITRE];
    
    /**
     * Le nom de l’équipe à laquelle le joueur appartient sur cette carte.
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
     *            Le nom de l'équipe.
     * @param annee
     *            L'annee de parution.
    */
    public Carte(String titre, String nom, int annee){
        char[] tmp = titre.toCharArray();
        this.titreCarte = tmp;
        tmp = nom.toCharArray();
        this.nomEquipe = tmp;
        this.anneeSortie = annee;
    }

    /**
     * Retourne le nombre maximal de cartes.
     * 
     * @return le nombre maximale de carte.
    */
    public static int getMaxTitre(){
        return Carte.MAXTITRE;
    }
    
    /**
     * Retourne la taille maximale du nom de l'équipe.
     * 
     * @return la taille maximal du nom de l'équipe.
    */
    public static int getMaxNomEquipe(){
        return Carte.MAXNOMEQUIPE;
    }
    
    /**
     * Retourne le titre de la carte.
     * 
     * @return le titre de la carte.
    */
    public String getTitreCarte(){
        return new String(this.titreCarte);
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
        return new String(this.nomEquipe);
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
     * @param nb
     *            La nouvelle annee de parution.
    */
    public void setAnneeSortie(int nb){
        this.anneeSortie = nb;
    }

    /**
     * Affiche les caractéristiques d'une carte.
     * 
     * @return le String d'affichage des caractéristiques de la carte.
    */
    public String toString(){
        return "Titre : "+ this.getTitreCarte()+ "\nEquipe : " + this.getNomEquipe() + "\nAnnée de parution : " + this.getAnneeSortie()+"\n";
    }
}