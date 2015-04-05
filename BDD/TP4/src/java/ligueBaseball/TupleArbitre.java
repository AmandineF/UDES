package ligueBaseball;

/**
 * Gestion du tuple Arbitre
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleArbitre {
    public int    idArbitre;
    public String nom;
    public String prenom;
    
    /**
     * Méthode pour afficher les renseignements d'un arbitre
     * @return La chaine de caracteres a afficher
     */
    @Override
    public String toString() {
        return "Nom : " + nom + " - Prénom : " + prenom + " - ID : " + idArbitre;
    }
}
