package ligueBaseball;

/**
 * Gestion du tuple Arbitrer
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleArbitrer {
    public int idArbitre;
    public int idMatch;
    
    /**
     * Méthode pour afficher les renseignements d'un arbitrage
     * @return La chaine de caracteres a afficher
    */
    @Override
    public String toString() {
        return "L'arbitre "+ idArbitre + "arbitre le match " + idMatch + ".";
    }
}
