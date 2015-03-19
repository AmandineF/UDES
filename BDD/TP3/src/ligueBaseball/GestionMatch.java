package ligueBaseball;
import java.sql.*;
/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionMatch {
    private Match matchTable;
    private Equipe equipeTable;
    
    /**
     * 
     */
    public GestionMatch() {}
    
    /**
     * 
     * @param matchDate
     * @param matchHeure
     * @param nomEquipeLocale
     * @param nomEquipeVisiteur
     * @param pointsLocal
     * @param pointsVisiteur
     * @throws SQLException 
     */
    public void entrerResultat(Date matchDate, Time matchHeure, String nomEquipeLocale, String nomEquipeVisiteur, int pointsLocal, int pointsVisiteur)throws SQLException  {
        if(pointsLocal < 0 || pointsVisiteur < 0) {
            System.out.println("Veuillez rentrer des points supérieurs ou égaux à zéro.");
        } else {
            int idLocaux = equipeTable.getId(nomEquipeLocale);
            int idVisiteurs = equipeTable.getId(nomEquipeVisiteur);
            int idMatch = matchTable.getId(matchDate, matchHeure, idLocaux, idVisiteurs);
            matchTable.setPoints(idMatch, pointsLocal, pointsVisiteur);
        }
    }
}
