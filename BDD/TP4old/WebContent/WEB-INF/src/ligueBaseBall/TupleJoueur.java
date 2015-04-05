package ligueBaseball;

/**
 * Gestion du tuple Joueur
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleJoueur {
    public int idJoueur;
    public String nom;
    public String prenom;
    
        
    /**
     * Methode pour afficher les renseignements d'un joueur
     * @return La chaine de caracteres a afficher
     */
    @Override
    public String toString(){
        return "Joueur " + idJoueur + 
                " : " + prenom + " " + nom + ".";
    }
    
    /**
     * Methode pour afficher l'identite d'un joueur
     * @return La chaine de caracteres a afficher
     */
    public String toStringIdentite(){
        return prenom + " " + nom;
    }
}
