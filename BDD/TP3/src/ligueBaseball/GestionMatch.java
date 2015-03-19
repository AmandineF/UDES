package ligueBaseball;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionMatch {
    private Match matchTable;
    private Equipe equipeTable;
    private Arbitrer arbitrerTable;
    private Arbitre arbitreTable;
    
    
    /**
     * 
     */
    public GestionMatch() {}
    
    
    public void creerMatch(Date matchDate, Time matchHeure,String nomEquipeLocale, String nomEquipeVisiteur)throws SQLException  {
        int idMatch = 0;
        matchTable.ajout(idMatch, equipeTable.getId(nomEquipeLocale), equipeTable.getId(nomEquipeVisiteur), 
                equipeTable.getEquipe(equipeTable.getId(nomEquipeLocale)).idTerrain,
                matchDate, matchHeure, 0, 0);
    }
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
    
    /**
     * 
     * @param d
     * @throws SQLException 
     */
    public void afficherResultatsDate(Date d) throws SQLException {
        Vector<TupleMatch> res;
        if(d != null) {
            System.out.println("Résultats des matchs après la date " + d.toString() + " :\n");
            res = matchTable.getMatchApresDate(d);
        } else {
            System.out.println("Résultats des matchs :\n");
            res = matchTable.getMatch();
        }
        afficherResultat(res);
    }
    
    /**
     * 
     * @param res
     * @throws SQLException 
     */
    public void afficherResultat(Vector<TupleMatch> res) throws SQLException {
        String nom = "";
        for(int i = 0; i < res.capacity() ; i++){
            if(arbitrerTable.existeMatch(res.elementAt(i).idMatch)) {
                int idArbitre = arbitrerTable.getArbitre(res.elementAt(i).idMatch);
                nom = arbitreTable.getArbitre(idArbitre).nom;
            }
            String s = "Match " + res.elementAt(i).idMatch + " : Points local : " + res.elementAt(i).pointslocal 
                    + " - Points visiteurs : " + res.elementAt(i).pointsvisiteur;
            if(!nom.equals("")) {
                s+= " - Arbitre : " + nom;
            }
            System.out.println(s); 
        }
    }
    
    /**
     * 
     * @param nom
     * @throws SQLException 
     */
    public void afficherResultatEquipe(String nom) throws SQLException {
        Vector<TupleMatch> res;
        if(!nom.equals("")) {
            System.out.println("Résultats des matchs ou l'équipe " + nom + " a participé :\n");
            res = matchTable.getMatchEquipe(equipeTable.getId(nom));
        } else {
            System.out.println("Résultats des matchs :\n");
            res = matchTable.getMatch();
        }
        afficherResultat(res);
    }
}
