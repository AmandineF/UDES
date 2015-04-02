package ligueBaseball;

/**
 * Gestion du tuple Equipe
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleEquipe {
    public int idEquipe;
    public int idTerrain;
    public String nom;
    
    /**
     * Methode pour afficher les renseignements d'une equipe
     * @return La chaine de caracteres a afficher
    */
    @Override
    public String toString() {
        return "Equipe " + nom + " - " +
                "Terrain " + idTerrain + 
                " - id " + idEquipe;
    }
    
    /**
     * Methode pour afficher les renseignements d'une equipe sans son terrain
     * @return La chaine de caracteres a afficher
     */
    public String toStringWT() {
        return "Equipe " + nom +
                " - id " + idEquipe;
    }
}
