package ligueBaseball;
import java.sql.*;

/**
 * Gestion de la table Arbitre
 * @author Amandine Fouillet - Frank Chassing
 */
public class Arbitre {
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtExisteHomonyme;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final Connexion cx;

    /**
     * Constructeur de la classe Arbitre
     * @param cx La connexion
     * @throws SQLException 
     */
    public Arbitre(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtExisteHomonyme = cx.getConnection().prepareStatement("select arbitreid from arbitre where arbitrenom = ? and arbitreprenom = ?");
        stmtExiste = cx.getConnection().prepareStatement("select arbitreid, arbitrenom, arbitreprenom from arbitre where arbitreid = ? orderby arbitrenom");
        stmtInsert = cx.getConnection().prepareStatement("insert into arbitre (arbitreid, arbitrenom, arbitreprenom) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from arbitre where arbitreid = ?");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
    * Verifie si un arbitre existe.
     * @param idArbitre
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idArbitre) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        boolean arbitreExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            arbitreExiste = rset.next();
        }
        return arbitreExiste;
    }
    
    /**
     * Méthode permettant de savoir s'il existe un arbitre avec ce nom dans la base
     * @param nom
     * @param prenom
     * @return l'id si l'homonyme existe, -1 sinon
     * @throws SQLException 
     */
    public int existeHomonyme(String nom, String prenom)throws SQLException {
        stmtExisteHomonyme.setString(1,nom);
        stmtExisteHomonyme.setString(1,prenom);
        int res = -1;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            if(rset.next()){
                res = rset.getInt(1);
            }
        }
        return res;
    }
    
    /**
     * 
     * @param idArbitre
     * @return
     * @throws SQLException 
     */
    public TupleArbitre getArbitre(int idArbitre) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleArbitre tupleArbitre;
            tupleArbitre = new TupleArbitre();
            tupleArbitre.idArbitre = idArbitre;
            tupleArbitre.nom = rset.getString(2);
            tupleArbitre.prenom = rset.getString(3);
            rset.close();
            return tupleArbitre;
        }
        return null;
    }

    
    /**
     * Ajout d'un nouvel arbitre dans la base de donnees.
     * @param idArbitre
     * @param nom
     * @param prenom
     * @throws SQLException 
     */
    public void ajout(int idArbitre, String nom, String prenom) throws SQLException {
        stmtInsert.setInt(1,idArbitre);
        stmtInsert.setString(2,nom);
        stmtInsert.setString(3,prenom);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un arbitre
     * @param idArbitre
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idArbitre) throws SQLException {
        stmtDelete.setInt(1,idArbitre);
        return stmtDelete.executeUpdate();
    }

}
