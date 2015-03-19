package ligueBaseball;
import java.sql.*;
/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */

public class GestionArbitrer {
    private Arbitrer arbitrerTable;
    private Arbitre arbitreTable;
    private Match matchTable;
    private Equipe equipeTable;
    
    public GestionArbitrer(){}
    
    public int nbArbitresMatch(int idMatch) throws SQLException  {
        int cpt = 0;
        for (int i = 0 ; i < arbitrerTable.taille() ; i++) {
            if(arbitrerTable.existeMatch(idMatch)) {
                cpt++;
            }
        }
        return cpt;
    }
    
    public void arbitrerMatch(Date matchDate, Time matchHeure, String nomEquipeLocale, String nomEquipeVisiteur, String arbitreNom, String arbitrePrenom)throws SQLException  {
        int idLocaux = equipeTable.getId(nomEquipeLocale);
        int idVisiteurs = equipeTable.getId(nomEquipeVisiteur);
        int idArbitre = arbitreTable.existeHomonyme(arbitreNom, arbitrePrenom);
        int idMatch = matchTable.getId(matchDate, matchHeure, idLocaux, idVisiteurs);
        if(idMatch > -1) {
            if(idArbitre > -1) {
                arbitrerTable.ajout(idArbitre, idMatch);
            } else {
                System.out.println("L'arbitre n'existe pas.");
            }
        } else {
            System.out.println("Le match n'existe pas.");
        }
    }
    
}
