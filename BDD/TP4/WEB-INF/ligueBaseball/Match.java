package ligueBaseball;
import java.sql.*;
import java.util.Vector;

/**
 * Gère les requêtes SQL vers la classe Match
 * @author Amandine Fouillet - Frank Chassing
 */
public class Match {
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtId;
    private final PreparedStatement stmtAll;
    private final PreparedStatement stmtNom;
    private final PreparedStatement stmtDate;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtInsertPoints;;
    private final Connexion cx;

    /**
     * Constructeur de la classe Arbitre
     * @param cx La connexion
     * @throws SQLException 
     */
    public Match(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtAll = cx.getConnection().prepareStatement("select * from match order by matchdate");
        stmtNom = cx.getConnection().prepareStatement("select * from match where equipelocal = ? or equipevisiteur = ? order by matchdate");
        stmtDate = cx.getConnection().prepareStatement("select * from match where matchdate >= ? order by matchdate");
        stmtId = cx.getConnection().prepareStatement("select matchid from match where equipelocal = ? and equipevisiteur = ? and matchdate = ? and matchheure = ?");
        stmtExiste = cx.getConnection().prepareStatement("select * from match where matchid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into match (matchid, equipelocal, equipevisiteur,terrainid,matchdate,matchheure,pointslocal,pointsvisiteur) " + "values (?,?,?,?,?,?,?,?)");
        stmtInsertPoints = cx.getConnection().prepareStatement("update match set pointslocal = ?, pointsvisiteur = ? where matchid = ?");
        stmtDelete = cx.getConnection().prepareStatement("delete from match where matchid = ?");
        
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
    * Verifie si un match existe.
     * @param idMatch L'id du match
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idMatch) throws SQLException {
        stmtExiste.setInt(1,idMatch);
        boolean matchExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            matchExiste = rset.next();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance du match " + idMatch + ".");
        }
        return matchExiste;
    }
    
    /**
     * Methode qui permet de recuperer le tuple de donnees du match idMatch
     * @param idMatch L'id du match dont on veut les donnees
     * @return Les donnees du match
     * @throws SQLException 
     */
    public TupleMatch getMatch(int idMatch) throws SQLException {
        stmtExiste.setInt(1,idMatch);
        try(ResultSet rset = stmtExiste.executeQuery()){
            if (rset.next()) {
                TupleMatch tupleMatch;
                tupleMatch = new TupleMatch();
                tupleMatch.idMatch = idMatch;
                tupleMatch.equipelocal = rset.getInt(2);
                tupleMatch.equipevisiteur = rset.getInt(3);
                tupleMatch.terrainid = rset.getInt(4);
                tupleMatch.matchdate = rset.getDate(5);
                tupleMatch.matchheure = rset.getTime(6);
                tupleMatch.pointslocal = rset.getInt(7);
                tupleMatch.pointsvisiteur = rset.getInt(8);           
                rset.close();
                return tupleMatch;
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation des donnees du match " + idMatch + ".");
        }
        return null;
    }
    
    /**
     * Methode qui permet de recuperer tous les tuples de donnees des matchs de la table match
     * @return Un vector contenant les tuples des matchs
     * @throws SQLException 
     */
    public Vector<TupleMatch> getMatch() throws SQLException {
        Vector<TupleMatch> res = new  Vector<TupleMatch>();
        try (ResultSet rset = stmtAll.executeQuery()) {
            while(rset.next()) {
                TupleMatch tupleMatch = new TupleMatch();
                tupleMatch.idMatch = rset.getInt(1);
                tupleMatch.equipelocal = rset.getInt(2);
                tupleMatch.equipevisiteur = rset.getInt(3);
                tupleMatch.terrainid = rset.getInt(4);
                tupleMatch.matchdate = rset.getDate(5);
                tupleMatch.matchheure = rset.getTime(6);
                tupleMatch.pointslocal = rset.getInt(7);
                tupleMatch.pointsvisiteur = rset.getInt(8);           
                res.addElement(tupleMatch);
            }  
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation des donnees des matchs.");
        }
        return res;
    }

    /**
     * Methode qui permet de recuperer toutes les donnees des matchs apres une certaine date
     * @param date La date apres laquelle on veut recuperer les donnees
     * @return Un vector contenant les tuples des matchs qui se sont deroules apres la date
     * @throws SQLException 
     */
    public Vector<TupleMatch> getMatchApresDate(Date date) throws SQLException {
         Vector<TupleMatch> res = new  Vector<TupleMatch>();
         stmtDate.setDate(1, date);
         try (ResultSet rset = stmtDate.executeQuery()) {
            while(rset.next()) {
                TupleMatch m = new TupleMatch();
                m.idMatch = rset.getInt(1);
                m.equipelocal = rset.getInt(2);
                m.equipevisiteur = rset.getInt(3);
                m.terrainid = rset.getInt(4);
                m.matchdate = rset.getDate(5);           
                m.matchheure=rset.getTime(6);
                m.pointslocal = rset.getInt(7);
                m.pointsvisiteur = rset.getInt(8);     
                res.addElement(m);
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation des donnees des matchs apres la date "+date.toString() +".");
        }
         return res;
    }
    /**
     * Methode qui permet de recuperer toutes les donnees des matchs joues par une equipe
     * @param idEquipe l'equipe dont on veut les matchs
     * @return Un vector contenant les tuples des matchs qui se sont deroules avec l'equipe
     * @throws SQLException 
     */
    public Vector<TupleMatch> getMatchEquipe(int idEquipe) throws SQLException {
         Vector<TupleMatch> res = new  Vector<TupleMatch>();
         stmtNom.setInt(1, idEquipe);
         stmtNom.setInt(2, idEquipe);
         try (ResultSet rset = stmtNom.executeQuery()) {
            while(rset.next()) {
                TupleMatch m = new TupleMatch();
                m.idMatch = rset.getInt(1);
                m.equipelocal = rset.getInt(2);
                m.equipevisiteur = rset.getInt(3);
                m.terrainid = rset.getInt(4);
                m.matchdate = rset.getDate(5);           
                m.matchheure=rset.getTime(6);
                m.pointslocal = rset.getInt(7);
                m.pointsvisiteur = rset.getInt(8);     
                res.addElement(m);
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation des donnees des matchs de l'equipe "+idEquipe +".");
        }
        return res;
    }
    
    /**
     * Ajout d'un nouveau match dans la base de donnees.
     * @param idMatch L'id du match
     * @param equipelocal L'equipe locale
     * @param equipevisiteur L'equipe visiteur
     * @param terrainid L'id du terrain
     * @param matchdate La date du match
     * @param matchheure L'heure du match
     * @param pointslocal Les points de l'equipe locale
     * @param pointsvisiteur Les points de l'equipe visiteur
     * @throws SQLException 
     */
    public void ajout(int idMatch, int equipelocal, int equipevisiteur, int terrainid,Date matchdate, Time matchheure, int pointslocal, int pointsvisiteur) throws SQLException {
        stmtInsert.setInt(1,idMatch);
        stmtInsert.setInt(2,equipelocal);
        stmtInsert.setInt(3,equipevisiteur);
        stmtInsert.setInt(4,terrainid);
        stmtInsert.setDate(5,matchdate);
        stmtInsert.setTime(6,matchheure);
        if(!(pointslocal == 0)) {
           stmtInsert.setInt(7,pointslocal);
        } else {
            stmtInsert.setNull(7, java.sql.Types.INTEGER);
        }
        if(!(pointslocal == 0)) {
           stmtInsert.setInt(8,pointsvisiteur);
        } else {
            stmtInsert.setNull(8, java.sql.Types.INTEGER);
        }
        try {
            stmtInsert.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout du match "+ equipelocal+"-"+equipevisiteur +".");
        }
    }

    /**
     * Suppression d'un match
     * @param idMatch L'id du match que l'on veut supprimer
     * @return -1 si la suppression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idMatch) throws SQLException {
        stmtDelete.setInt(1,idMatch);
        try{
            return stmtDelete.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la suppression du match "+ idMatch +".");
        }
        return -1;
    }
    
    /**
     * Methode retournant l'id d'un match d'apres plusieurs de ses informations
     * @param matchDate La date du match
     * @param matchHeure L'heure du match
     * @param idLocaux L'equipe locale du match
     * @param idVisiteurs L'equipe visiteur du match
     * @return L'id du match recherche
     * @throws SQLException 
     */
    public int getId(Date matchDate, Time matchHeure, int idLocaux, int idVisiteurs) throws SQLException  {
        stmtId.setDate(3,matchDate);
        stmtId.setTime(4,matchHeure);
        stmtId.setInt(1,idLocaux);
        stmtId.setInt(2,idVisiteurs);
        int res = -1;
        try (ResultSet rset = stmtId.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation de l'id du match "+ idLocaux+"-"+idVisiteurs +".");
        }
        return res;
    }
    
    /**
     * Methode permettant d'enregistrer ou de modifier les points d'un match
     * @param idMatch L'id du match a modifier
     * @param pointsLocal Les nouveaux points de l'equipe locale
     * @param pointsVisiteur Les nouveaux points de l'equipe visiteur
     * @throws SQLException 
     */
    public void setPoints(int idMatch, int pointsLocal, int pointsVisiteur) throws SQLException {
        stmtInsertPoints.setInt(1, pointsLocal);
        stmtInsertPoints.setInt(2, pointsVisiteur);
        stmtInsertPoints.setInt(3, idMatch);
        try{
            stmtInsertPoints.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la modification des points du match "+ idMatch +".");
        }
    }
    
}
