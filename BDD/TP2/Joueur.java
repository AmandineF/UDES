/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 130 638
 */
public class Joueur {
    /**
     * La taille maximale de la cl�
     */
    private static final int MAXCLE = 16;
    
    /**
     * La taille maximale du nom du joueur
     */
    private static final int MAXNOM = 128;
     
    /**
     * La cl� d'enregistrement du joueur. 
     * 
     * @see Joueur#getCle()
     * @see Joueur#setCle()
    */
    private char cle[] = new char[MAXCLE]; 
         
    /**
     * Le nom du joueur qui appara�t sur la carte.Taille max 128 caract�res.
     * 
     * @see Joueur#getNomJoueur()
     * @see Joueur#setNomJoueur(String)
    */
    private char nomJoueur[] = new char[MAXNOM];
         
    /**
     * Indique combien de cartes de ce joueur il y en a maximum 20.
     * 
     * @see Joueur#getNombreCartes()
     * @see Joueur#setNombreCartes(int)
    */
    private int nombreCartes;

    /**
     * Retourne la taille maximale de la cl� d'un joueur.
     * 
     * @return la taille maximale de la cl� du joueur 
    */
    public static int getMaxCle() {
        return Joueur.MAXCLE;
    }
    
    /**
     * Retourne la taille maximale de la cl� d'un joueur.
     * 
     * @return la taille maximale de la cl� du joueur 
    */
    public static int getMaxNom() {
        return Joueur.MAXNOM;
    }
    
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
     * @param nb
     *            Le nouveau nombre de cartes que poss�de le joueur.
    */
    public void setNombreCartes(int nb){
	this.nombreCartes = nb;
    }
}