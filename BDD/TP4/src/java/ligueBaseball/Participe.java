package ligueBaseball;
import java.sql.*;

/**
 * Gère les requêtes SQL vers la classe Participe
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
    * Retourne la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
     * Methode qui verifie la participation d'un joueur a un match
     * @param idJoueur l'id du joueur
     * @param idMatch l'id du match
     * @return vrai si la participation existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idJoueur, int idMatch ) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(2,idMatch);
        boolean participeExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
        	participeExiste = rset.next();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de la participation du joueur " +idJoueur+
                    " au match " + idMatch + ".");
        }
        return participeExiste;
    }
    
    /**
     * Verifie si un joueur est present dans la table participe
      * @param idJoueur L'id du joueur dont on veut verifier la presence
      * @return vrai si le joueur existe, faux sinon
      * @throws java.sql.SQLException 
     */
     public boolean existeJoueur(int idJoueur) throws SQLException {
         stmtExisteJoueur.setInt(1,idJoueur);
         boolean joueurExiste = false;
         try (ResultSet rset = stmtExisteJoueur.executeQuery()) {
        	 joueurExiste = rset.next();
         }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance du joueur " +idJoueur+
                    " dans la table participe.");
        }
         return joueurExiste;
     }

    /**
     * Methode qui permet de recuperer les donnees de participation d'un joueur lors d'un match
     * @param idJoueur L'id du joueur
     * @param idMatch L'id du match
     * @return le tupleParticipe correspondant a la participation ayant les ids en parametres, null sinon
     * @throws SQLException 
     */
    public TupleParticipe getParticipe(int idJoueur, int idMatch) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(2,idMatch);
        try(ResultSet rset = stmtExiste.executeQuery()){
            if (rset.next()) {
                    TupleParticipe tupleParticipe;
                    tupleParticipe = new TupleParticipe();
                    tupleParticipe.joueurid = idJoueur;
                    tupleParticipe.matchid = idMatch;
                    tupleParticipe.commentaireperformance = rset.getString(3);
                rset.close();
                return tupleParticipe;
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation des donnes de participation du joueur " + 
                    idJoueur + " au match " + idMatch +".");
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
        try{
            stmtInsert.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout de la participation du joueur " + idJoueur +
                    " au match " + idMatch + ".");
        }
    }

    /**
     * Suppression d'une participation
     * @param idJoueur
     * @param idMatch
     * @return -1 si la suppression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idJoueur, int idMatch) throws SQLException {
        stmtDelete.setInt(1,idJoueur);
        stmtDelete.setInt(2,idMatch);
        try{
            return stmtDelete.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la suppression de la participation du joueur " + idJoueur +
                    " au match " + idMatch + ".");
        }
        return -1;
    }
    
    /**
     * Suppression des participations d'un joueur
     * @param idJoueur
     * @return -1 si la suppression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idJoueur) throws SQLException {
        stmtDeleteJoueur.setInt(1,idJoueur);
        try{
            return stmtDeleteJoueur.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la suppression des participations du joueur " + idJoueur +
                    ".");
        }
        return -1;
    }
}
