package ligueBaseball;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleArbitre {
    public int    idArbitre;
    public String nom;
    public String prenom;
    
    /**
     * Méthode pour afficher les renseignements d'un Arbitre
     * @return la chaine à afficher
     */
    @Override
    public String toString() {
        return "ID : " + idArbitre + " - Nom : " + nom + " - Prénom : " + prenom ;
    }
}
