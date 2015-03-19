package ligueBaseball;
import java.sql.*;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class Equipe {
    private final PreparedStatement stmtId;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final Connexion cx;

    /**
     * Constructeur de la classe Équipe
     * @param cx La connexion
     * @throws SQLException 
     */
    public Equipe(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtId = cx.getConnection().prepareStatement("select equipeid from equipe where equipenom = ?");
        stmtExiste = cx.getConnection().prepareStatement("select equipeid, terrainid, equipenom from equipe where equipeid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into equipe (equipeid, terrainid, equipenom) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from equipe where equipeid = ?");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
     * Retourne l'id d'une équipe
     * @param nom Le nom de l'équipe
     * @return l'id de l'équipe
     * @throws SQLException 
     */
    public int getId(String nom) throws SQLException {
        stmtId.setString(1,nom);
        int res = -1;
        try (ResultSet rset = stmtId.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }
        stmtId.close();
        return res;
    }
    /**
    * Verifie si une équipe existe.
     * @param idEquipe
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idEquipe);
        boolean equipeExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            equipeExiste = rset.next();
        }
        stmtExiste.close();
        return equipeExiste;
    }

    /**
     * Donne le tuple de l'équipe ayant pour id idEquipe
     * @param idEquipe L'id de l'équipe dont on veut le tuple
     * @return Le Tuple résultat
     * @throws SQLException 
     */
    public TupleEquipe getEquipe(int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idEquipe);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleEquipe tupleEquipe;
            tupleEquipe = new TupleEquipe();
            tupleEquipe.idEquipe = idEquipe;
            tupleEquipe.idTerrain = rset.getInt(2);
            tupleEquipe.nom = rset.getString(3);
            rset.close();
            stmtExiste.close();
            return tupleEquipe;
        }
        stmtExiste.close();
        return null;
    }

    /**
     * Ajout d'une nouvelle équipe dans la base de donnees.
     * @param idEquipe L'id de la nouvelle équipe
     * @param nom Le nom de la nouvelle équipe
     * @param idTerrain L'id du terrain de la nouvelle équipe
     * @throws SQLException 
     */
    public void ajout(int idEquipe, int idTerrain, String nom) throws SQLException {
        stmtInsert.setInt(1,idEquipe);
        stmtInsert.setInt(2,idTerrain);
        stmtInsert.setString(3,nom);
        stmtInsert.executeUpdate();
        stmtInsert.close();
    }

    /**
     * Suppression d'une équipe
     * @param idEquipe
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idEquipe) throws SQLException {
        stmtDelete.setInt(1,idEquipe);
        int res = stmtDelete.executeUpdate();
        stmtDelete.close();
        return res;
    }
}