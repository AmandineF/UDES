package ligueBaseball;
import java.sql.*;

/**
 * Gestion de la table Arbitrer
 * @author Amandine Fouillet - Frank Chassing
 */
public class Arbitrer {
    private final PreparedStatement stmtExisteMatch;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtTaille;
    private final Connexion cx;

    /**
     * Constructeur de la classe Arbitrer
     * @param cx La connexion
     * @throws SQLException 
     */
    public Arbitrer(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtTaille = cx.getConnection().prepareStatement("select count(matchid) from arbitrer");
        stmtExisteMatch = cx.getConnection().prepareStatement("select arbitreid, matchid from arbitrer where  matchid = ?");
        stmtExiste = cx.getConnection().prepareStatement("select arbitreid, matchid from arbitrer where arbitreid = ? and matchid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into arbitrer (arbitreid, matchid) " + "values (?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from arbitre where arbitreid = ? and matchid = ?");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
     * Connaitre la taille de la table arbitrer
     * @return la taille de la table
     * @throws SQLException 
     */
    public int taille() throws SQLException {
        int res = -1;
        try (ResultSet rset = stmtTaille.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }
        return res;
    }
    /**
    * Verifie si un lien entre un matchid et un arbitreid a déjà été réalisé.
     * @param idArbitre
     * @param idMatch
     * @return vrai si le lien existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idArbitre, int idMatch) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        stmtExiste.setInt(2,idMatch);
        boolean arbitrerExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            arbitrerExiste = rset.next();
        }
        return arbitrerExiste;
    }

    /**
     * 
     * @param idMatch
     * @return
     * @throws SQLException 
     */
    public boolean existeMatch(int idMatch) throws SQLException {
        stmtExiste.setInt(1,idMatch);
        boolean arbitrerExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            arbitrerExiste = rset.next();
        }
        return arbitrerExiste;
    }
    
    public int getArbitre(int idMatch) throws SQLException {
        stmtExiste.setInt(1,idMatch);
        int res = -1;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            if(rset.next()){
                res = rset.getInt(1);
                return res;
            }
        }
        return -1;
    }
    
    /**
     * 
     * @param idArbitre
     * @param idMatch
     * @return
     * @throws SQLException 
     */
    public TupleArbitrer getArbitrage(int idArbitre, int idMatch) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        stmtExiste.setInt(2,idMatch);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleArbitrer tupleArbitrer;
            tupleArbitrer = new TupleArbitrer();
            tupleArbitrer.idArbitre = idArbitre;
            tupleArbitrer.idMatch = idMatch;
            rset.close();
            return tupleArbitrer;
        }
        return null;
    }

    /**
     * Ajout d'un nouvel arbitre dans la base de donnees.
     * @param idArbitre
     * @param idMatch
     * @throws SQLException 
     */
    public void ajout(int idArbitre, int idMatch) throws SQLException {
        stmtInsert.setInt(1,idArbitre);
        stmtInsert.setInt(2,idMatch);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un arbitre
     * @param idArbitre
     * @param idMatch
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idArbitre, int idMatch) throws SQLException {
        stmtDelete.setInt(1,idArbitre);
        stmtDelete.setInt(1,idMatch);
        return stmtDelete.executeUpdate();
    }

}
