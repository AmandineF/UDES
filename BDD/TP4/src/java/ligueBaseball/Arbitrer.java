package ligueBaseball;
import java.sql.*;
import java.util.Vector;

/**
 * Gère les requêtes SQL vers la classe Arbitrer
 * @author Amandine Fouillet - Frank Chassing
 */
public class Arbitrer {
    private final PreparedStatement stmtExisteMatch;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtAffiche;
    private final Connexion cx;

    /**
     * Constructeur de la classe Arbitrer
     * @param cx La connexion
     * @throws SQLException 
     */
    public Arbitrer(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtAffiche = cx.getConnection().prepareStatement("select * from arbitrer");
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

    public Vector<TupleArbitrer> affiche() {
        Vector<TupleArbitrer> res = new Vector<TupleArbitrer>();
        try (ResultSet rset = stmtAffiche.executeQuery()) {
            while(rset.next()){
                TupleArbitrer ta  = new TupleArbitrer();
                ta.idArbitre = rset.getInt(1);
                ta.idMatch = rset.getInt(2);
                res.add(ta);
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme dans l'affichage des arbitrages.");
        }
        return res;
    }
    /**
    * Verifie si un lien entre un matchid et un arbitreid a deja ete realise.
     * @param idArbitre
     * @param idMatch
     * @return vrai si le lien existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idArbitre, int idMatch) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        stmtExiste.setInt(2,idMatch);
        boolean arbitrerExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            arbitrerExiste = rset.next();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme dans la verification de l'existance du match " +idMatch
                    + " arbitre par " + idArbitre+".");
        }
        return arbitrerExiste;
    }

    /**
     * Verifie si il existe le match idMatch dans la table arbitrer
     * @param idMatch L'id du match a retrouver dans la table
     * @return vrai si ce match existe, faux sinon
     * @throws SQLException 
     */
    public boolean existeMatch(int idMatch) throws SQLException {
        stmtExisteMatch.setInt(1,idMatch);
        boolean arbitrerExiste = false;
        try (ResultSet rset = stmtExisteMatch.executeQuery()) {
            arbitrerExiste = rset.next();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme dans la verification de l'existance du match " +idMatch
                    + " dans la table arbitrer.");
        }
        return arbitrerExiste;
    }
    
    /**
     * Methode retournant l'arbitre du match idMatch
     * @param idMatch Le match dont on veut savoir l'arbitre
     * @return l'id de l'arbitre du match
     * @throws SQLException 
     */
    public int getArbitre(int idMatch) throws SQLException {
        stmtExisteMatch.setInt(1,idMatch);
        int res = -1;
        try (ResultSet rset = stmtExisteMatch.executeQuery()) {
            if(rset.next()){
                res = rset.getInt(1);
                return res;
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme dans la recuperation de l'id de l'arbitre du match " + idMatch + ".");
        }
        return -1;
    }
    
    /**
     * Methode retournant le couple arbitrage, s'il existe
     * @param idArbitre L'id de l'arbitre
     * @param idMatch L'id du match
     * @return
     * @throws SQLException 
     */
    public TupleArbitrer getArbitrage(int idArbitre, int idMatch) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        stmtExiste.setInt(2,idMatch);
        try(ResultSet rset  = stmtExiste.executeQuery()){
            if (rset.next()) {
                TupleArbitrer tupleArbitrer;
                tupleArbitrer = new TupleArbitrer();
                tupleArbitrer.idArbitre = idArbitre;
                tupleArbitrer.idMatch = idMatch;
                rset.close();
                return tupleArbitrer;
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme dans la recuperation de l'arbitrage du match " + idMatch 
                    + " par l'abitre " + idArbitre +".");
        }
        return null;
    }

    /**
     * Ajout d'un nouvel arbitre dans la base de donnees.
     * @param idArbitre L'id de l'arbitre a ajouter
     * @param idMatch L'id du match a ajouter
     * @throws SQLException 
     */
    public void ajout(int idArbitre, int idMatch) throws SQLException {
        stmtInsert.setInt(1,idArbitre);
        stmtInsert.setInt(2,idMatch);
        try{
            stmtInsert.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout de l'arbitrage du match " + idMatch + 
                    " par l'abitre " + idArbitre +".");
        }
    }

    /**
     * Suppression d'un arbitre
     * @param idArbitre
     * @param idMatch
     * @return -1 si la suppression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idArbitre, int idMatch) throws SQLException {
        stmtDelete.setInt(1,idArbitre);
        stmtDelete.setInt(1,idMatch);
        try {
            return stmtDelete.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la suppression de l'arbitrage du match " + idMatch + 
                    " par l'abitre " + idArbitre +".");
        }
        return -1;
    }

}
