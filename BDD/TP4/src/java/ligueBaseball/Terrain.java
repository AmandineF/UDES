package ligueBaseball;
import java.sql.*;

/**
 * Gère les requêtes SQL vers la classe Terrain
 * @author Amandine Fouillet - Frank Chassing
 */
public class Terrain {
    private final PreparedStatement stmtId;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtExisteNom;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final Connexion cx;

    /**
     * Constructeur de la classe Terrain
     * @param cx La connexion
     * @throws SQLException 
     */
    public Terrain(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtId = cx.getConnection().prepareStatement("select terrainid from terrain where terrainnom = ? and terrainadresse = ?");
        stmtExiste = cx.getConnection().prepareStatement("select * from terrain where terrainid = ?");
        stmtExisteNom = cx.getConnection().prepareStatement("select * from terrain where terrainnom = ? and terrainadresse = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into terrain (terrainid, terrainnom, terrainadresse) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from terrain where terrainid = ?");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
     * Retourne l'id du terrain
     * @param nom Le nom du terrain
     * @param adresse l'adresse du terrrain
     * @return l'id du terrain correspondant
     * @throws SQLException 
     */
    public int getId(String nom, String adresse) throws SQLException {
        stmtId.setString(1,nom);
        stmtId.setString(2,adresse);
        int res = -1;
        try (ResultSet rset = stmtId.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }
        return res;
    }
    /**
    * Verifie si un terrain existe
     * @param idTerrain l'id du terrain
     * @return vrai si le terrain existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idTerrain) throws SQLException {
        stmtExiste.setInt(1,idTerrain);
        boolean terrainExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
        	terrainExiste = rset.next();
        }
        return terrainExiste;
    }
    
    /**
     * Verifie si le nom d'un terrain existe
      * @param nomTerrain l'id du terrain
      * @return vrai si le nom du terrain existe, faux sinon
      * @throws java.sql.SQLException 
     */
     public boolean existeNom(String nomTerrain, String adresseterrain) throws SQLException {
         stmtExisteNom.setString(1,nomTerrain);
         stmtExisteNom.setString(2,adresseterrain);
         boolean terrainExiste;
         try (ResultSet rset = stmtExisteNom.executeQuery()) {
         	terrainExiste = rset.next();
         }
         return terrainExiste;
     }

    /**
     * 
     * @param idTerrain
     * @return le tupleTerrain correspondant au Terrain ayant l'id "idTerrain", null sinon
     * @throws SQLException 
     */
    public TupleTerrain getTerrain(int idTerrain) throws SQLException {
        stmtExiste.setInt(1,idTerrain);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
        	TupleTerrain tupleTerrain;
        	tupleTerrain = new TupleTerrain();
        	tupleTerrain.idTerrain = idTerrain;
        	tupleTerrain.terrainnom = rset.getString(2);
        	tupleTerrain.terrainadresse = rset.getString(3);
            rset.close();
            return tupleTerrain;
        }
        return null;
    }

    /**
     * Ajout d'un nouveau terrain dans la base de donnees.
     * @param idTerrain
     * @param nom
     * @param adresse
     * @throws SQLException 
     */
    public void ajout(int idTerrain, String nom, String adresse) throws SQLException {
        stmtInsert.setInt(1,idTerrain);
        stmtInsert.setString(2,nom);
        stmtInsert.setString(3,adresse);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un terrain
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idTerrain) throws SQLException {
        stmtDelete.setInt(1,idTerrain);
        stmtDelete.close();
        return stmtDelete.executeUpdate();
        
    }
}
