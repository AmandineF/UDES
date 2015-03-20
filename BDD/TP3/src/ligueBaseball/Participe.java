package ligueBaseball;
import java.sql.*;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class Participe {
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtDeleteJoueur;
    private final PreparedStatement stmtExisteJoueur;
    private final Connexion cx;

    /**
     * Constructeur de la classe Participe
     * @param cx La connexion
     * @throws SQLException 
     */
    public Participe(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement("select * from participe where joueurid = ? and matchid = ?");
        stmtExisteJoueur = cx.getConnection().prepareStatement("select * from participe where joueurid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into participe (joueurid, matchid, commentaireperformance) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from participe where joueurid = ? and matchid = ?");
        stmtDeleteJoueur = cx.getConnection().prepareStatement("delete from participe where joueurid = ?");
    }

    /**
    * Retourner la connexion a la base de donnÃ©e
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
    *
     * @param idJoueur l'id du terrain
     * @param idMatch l'id du match
     * @return vrai si la participation existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idJoueur, int idMatch ) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(2,idMatch);
        boolean participeExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
        	participeExiste = rset.next();
        }
        return participeExiste;
    }
    
    /**
     * Verifie si un joueur existe
      * @param idJoueur
      * @return vrai si le joueur existe, faux sinon
      * @throws java.sql.SQLException 
     */
     public boolean existeJoueur(int idJoueur) throws SQLException {
         stmtExisteJoueur.setInt(1,idJoueur);
         boolean joueurExiste;
         try (ResultSet rset = stmtExisteJoueur.executeQuery()) {
        	 joueurExiste = rset.next();
         }
         stmtExisteJoueur.close();
         return joueurExiste;
     }

    /**
     * 
     * @param idJoueur
     * @param idMatch
     * @return le tupleParticipe correspondant a la participation ayant les ids en paramètres, null sinon
     * @throws SQLException 
     */
    public TupleParticipe getParticipe(int idJoueur, int idMatch) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(2,idMatch);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
        	TupleParticipe tupleParticipe;
        	tupleParticipe = new TupleParticipe();
        	tupleParticipe.joueurid = idJoueur;
        	tupleParticipe.matchid = idMatch;
        	tupleParticipe.commentaireperformance = rset.getString(3);
            rset.close();
            return tupleParticipe;
        }
        return null;
    }

    /**
     * Ajout d'une nouvelle participation dans la base de donnees.
     * @param idJoueur
     * @param idMatch
     * @param commentaire
     * @throws SQLException 
     */
    public void ajout(int idJoueur, int idMatch, String commentaire) throws SQLException {
        stmtInsert.setInt(1,idJoueur);
        stmtInsert.setInt(2,idMatch);
        stmtInsert.setString(3,commentaire);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'une participation
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idJoueur, int idMatch) throws SQLException {
        stmtDelete.setInt(1,idJoueur);
        stmtDelete.setInt(2,idMatch);
        return stmtDelete.executeUpdate();
    }
    
    /**
     * Suppression des participations d'un joueur
     * @param idJoueur
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idJoueur) throws SQLException {
        stmtDeleteJoueur.setInt(1,idJoueur);
        return stmtDeleteJoueur.executeUpdate();
    }
}
