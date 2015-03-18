package ligueBaseball;

import java.sql.*;

/**
 * Gestion de la table arbitre
 * @author Amandine Fouillet - Frank Chassing
 */
public class Arbitre {
    private final PreparedStatement stmtExiste;
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
        stmtExiste = cx.getConnection().prepareStatement("select arbitreid, arbitrenom, arbitreprenom from arbitre where idarbitre = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into arbitre (idAbitre, nom, prenom) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from arbitre where idArbitre = ?");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
    * Verifie si un arbire existe.
     * @param idArbitre
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idArbitre) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        boolean arbitreeExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            arbitreeExiste = rset.next();
        }
        return arbitreeExiste;
    }

    /**
     * 
     * @param idArbitre
     * @return
     * @throws SQLException 
     */
    public TupleArbitre getLivre(int idArbitre) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleArbitre tupleArbitre = new TupleArbitre();
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
    public void acquerir(int idArbitre, String nom, String prenom) throws SQLException {
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
