package ligueBaseball;

/**
 * Gestion du tuple Terrain
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleTerrain {
    public int idTerrain;
    public String terrainnom;
    public String terrainadresse;
    
    /**
     * Methode pour afficher les renseignements d'un terrain
     * @return La chaine de caracteres a afficher
     */
    @Override
    public String toString() {
        return "Terrain " + terrainnom + " : " +
                " - " + terrainadresse +
                " - " + idTerrain;       
    }
}
