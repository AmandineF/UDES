package ligueBaseball;
import java.sql.*;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class FaitPartie {
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final Connexion cx;

    /**
     * Constructeur de la classe Arbitre
     * @param cx La connexion
     * @throws SQLException 
     */
    public FaitPartie(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement("select joueurid, equipeid, arbitreprenom from faitpartie where joueurid = ? and equipeid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into faitpartie (joueurid, equipeid, numero, datedebut, datefin) " + "values (?,?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from faitpartie where joueurid = ? and equipeid = ?");
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
     * @param idJoueur
     * @param idEquipe
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idJoueur, int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(1,idEquipe);
        boolean faitpartieExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            faitpartieExiste = rset.next();
        }
        return faitpartieExiste;
    }

    /**
     * 
     * @param idJoueur
     * @param idEquipe
     * @return
     * @throws SQLException 
     */
    public TupleFaitPartie getArbitre(int idJoueur, int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(2,idEquipe);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleFaitPartie tupleFaitPartie;
            tupleFaitPartie = new TupleFaitPartie();
            tupleFaitPartie.idJoueur = idJoueur;
            tupleFaitPartie.idEquipe = idEquipe;
            tupleFaitPartie.numero = rset.getInt(3);
            tupleFaitPartie.datedebut = rset.getDate(4);
            tupleFaitPartie.datefin = rset.getDate(5);
            rset.close();
            return tupleFaitPartie;
        }
        return null;
    }

    /**
     * Ajout d'un nouvel arbitre dans la base de donnees.
     * @param idJoueur
     * @param numero
     * @param idEquipe
     * @param datedebut
     * @param datefin
     * @throws SQLException 
     */
    public void ajout(int idJoueur, int idEquipe, int numero, Date datedebut, Date datefin) throws SQLException {
        stmtInsert.setInt(1,idJoueur);
        stmtInsert.setInt(2,idEquipe);
        stmtInsert.setInt(3,numero);
        stmtInsert.setDate(4,datedebut);
        stmtInsert.setDate(5,datefin);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un arbitre
     * @param idJoueur
     * @param idEquipe
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idJoueur, int idEquipe) throws SQLException {
        stmtDelete.setInt(1,idJoueur);
        stmtDelete.setInt(2,idEquipe);
        return stmtDelete.executeUpdate();
    }
}
