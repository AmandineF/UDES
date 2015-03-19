package ligueBaseball;
import java.sql.*;
import java.util.Vector;

/**
 *
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
        stmtDate = cx.getConnection().prepareStatement("select * from match where matchdate > ? order by matchdate");
        stmtId = cx.getConnection().prepareStatement("select matchid from match where equipelocal = ? and equipevisiteur = ? and matchdate = ? and matchheure = ?");
        stmtExiste = cx.getConnection().prepareStatement("select * from match where matchid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into match (matchid, equipelocal, equipevisiteur,terrainid,matchdate,matchheure,pointslocal,pointsvisiteur) " + "values (?,?,?,?,?,?,?,?)");
        stmtInsertPoints = cx.getConnection().prepareStatement("insert into match (pointslocal,pointsvisiteur) values (?,?) where matchid = ?");
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
     * @param idMatch
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idMatch) throws SQLException {
        stmtExiste.setInt(1,idMatch);
        boolean matchExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            matchExiste = rset.next();
        }
        return matchExiste;
    }
    
    /**
     * 
     * @param idMatch
     * @return
     * @throws SQLException 
     */
    public TupleMatch getMatch(int idMatch) throws SQLException {
        stmtExiste.setInt(1,idMatch);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
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
        return null;
    }
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
        }
        return res;
    }

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
        }
         return res;
    }
    /**
     * 
     * @param idEquipe
     * @return
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
        }
         return res;
    }
    
    /**
     * Ajout d'un nouveau match dans la base de donnees.
     * @param idMatch
     * @param equipelocal
     * @param equipevisiteur
     * @param terrainid
     * @param matchdate
     * @param matchheure
     * @param pointslocal
     * @param pointsvisiteur
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
        }
        if(!(pointslocal == 0)) {
           stmtInsert.setInt(8,pointsvisiteur);
        }
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un match
     * @param idMatch
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idMatch) throws SQLException {
        stmtDelete.setInt(1,idMatch);
        return stmtDelete.executeUpdate();
    }
    public int getId(Date matchDate, Time matchHeure, int idLocaux, int idVisiteurs) throws SQLException  {
        stmtId.setDate(1,matchDate);
        stmtId.setTime(2,matchHeure);
        stmtId.setInt(3,idLocaux);
        stmtId.setInt(4,idVisiteurs);
        int res = -1;
        try (ResultSet rset = stmtId.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }
        return res;
    }
    
    /**
     * 
     * @param idMatch
     * @param pointsLocal
     * @param pointsVisiteur
     * @throws SQLException 
     */
    public void setPoints(int idMatch, int pointsLocal, int pointsVisiteur) throws SQLException {
        stmtInsertPoints.setInt(1, pointsLocal);
        stmtInsertPoints.setInt(2, pointsVisiteur);
        stmtInsertPoints.setInt(3, idMatch);
        stmtInsertPoints.executeUpdate();
    }
    
}
