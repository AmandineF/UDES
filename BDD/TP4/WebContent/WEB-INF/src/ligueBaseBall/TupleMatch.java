package ligueBaseball;

import java.sql.*;

/**
 * Gestion du tuple Match
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleMatch {
    public int idMatch;
    public int equipelocal;
    public int equipevisiteur;
    public int terrainid;
    public Date matchdate;
    public Time matchheure;
    public int pointslocal;
    public int pointsvisiteur;
    
    /**
     * Methode pour afficher les resultats d'un match
     * @param nomArbitre Le nom de l'arbitre du match
     * @return La chaine de caracteres a afficher
     */
    public String toStringResultat(String nomArbitre) {
        String s =  "Match " + idMatch + " : Points local : " + pointslocal 
                    + " - Points visiteurs : " + pointsvisiteur;
        if(!nomArbitre.equals("")) {
                s+= " - Arbitre : " + nomArbitre;
            }
        return s;
    }
    
    /**
     * Methode pour afficher les renseignements d'un match
     * @return La chaine de caracteres a afficher
     */
    @Override
    public String toString(){
        return "Match " + idMatch + 
                " - Equipe locale : " + equipelocal +
                " - Equipe visiteur : " + equipevisiteur +
                " - Terrain id : " + terrainid +
                " - Date : " + matchdate.toString() + 
                " - Heure : " + matchheure.toString() +
                " - Points local : " + pointslocal + 
                " - Points visiteurs : " + pointsvisiteur;
    }
}
