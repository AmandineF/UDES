package ligueBaseball;
import java.sql.*;

/**
 * Gère les requêtes SQL vers la classe FaitPartie
 * @author Amandine Fouillet - Frank Chassing
 */
public class FaitPartie {
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtDeleteJoueur;
    private final PreparedStatement stmtEquipe;
    private final PreparedStatement stmtExisteJoueur;
    private final PreparedStatement stmtNumeroJoueur;
    private final PreparedStatement stmtDateJoueur;
    private final Connexion cx;

    /**
     * Constructeur de la classe Arbitre
     * @param cx La connexion
     * @throws SQLException 
     */
    public FaitPartie(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement("select joueurid, equipeid, arbitreprenom from faitpartie where joueurid = ? and equipeid = ?");
        stmtExisteJoueur = cx.getConnection().prepareStatement("select joueurid, equipeid from faitpartie where joueurid = ?");
        stmtNumeroJoueur = cx.getConnection().prepareStatement("select numero from faitpartie where joueurid = ?");
        stmtDateJoueur = cx.getConnection().prepareStatement("select datedebut from faitpartie where joueurid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into faitpartie (joueurid, equipeid, numero, datedebut, datefin) " + "values (?,?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from faitpartie where joueurid = ? and equipeid = ?");
        stmtDeleteJoueur = cx.getConnection().prepareStatement("delete from faitpartie where joueurid = ? ");
        stmtEquipe = cx.getConnection().prepareStatement("select count(*) from faitpartie where equipeid = ?");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    public int getNumeroJoueur(int idJoueur) throws SQLException {
        stmtNumeroJoueur.setInt(1, idJoueur);
        try (ResultSet rset = stmtNumeroJoueur.executeQuery()) {
            if(rset.next()) {
                return rset.getInt(1);
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation du numero du joueur.");
        }
        return -1;
    } 
    
    public Date getDateJoueur(int idJoueur) throws SQLException {
        stmtDateJoueur.setInt(1, idJoueur);
        try (ResultSet rset = stmtDateJoueur.executeQuery()) {
            if(rset.next()) {
                return rset.getDate(1);
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation du numero du joueur.");
        }
        return null;
    } 
    /**
    * Verifie si une relation joueur equipe existe
     * @param idJoueur L'id du joueur
     * @param idEquipe L'id de l'equipe
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idJoueur, int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(1,idEquipe);
        boolean faitpartieExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            faitpartieExiste = rset.next();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance du joueur " + idJoueur 
                    + " dans l'equipe " + idEquipe + ".");
        }
        return faitpartieExiste;
    }
    
    /**
     * Verifie si un joueur existe dans la table faitpartie
      * @param idJoueur
      * @return vrai si le joueur existe, faux sinon
      * @throws java.sql.SQLException 
     */
     public boolean existeJoueur(int idJoueur) throws SQLException {
         stmtExisteJoueur.setInt(1,idJoueur);
         boolean joueurExiste = false;
         try (ResultSet rset = stmtExisteJoueur.executeQuery()) {
        	 joueurExiste = rset.next();
         }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance du joueur " + idJoueur 
                    + " dans la table faitpartie.");
        }
         return joueurExiste;
     }

    /**
     * Methode qui retourne un tuple de donnee sur une relation joueur equipe
     * @param idJoueur L'id du joueur
     * @param idEquipe L'id de l'equipe
     * @return le tuple des donnees
     * @throws SQLException 
     */
    public TupleFaitPartie getFaitPartie(int idJoueur, int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        stmtExiste.setInt(2,idEquipe);
        try(ResultSet rset = stmtExiste.executeQuery()){
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
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation du tuple de donnees du joueur " +idJoueur
            + " dans l'equipe " + idEquipe + ".");
        }
        return null;
    }

    /**
     * Ajout d'une nouvel relation joueur equipe dans la base de donnees.
     * @param idJoueur L'id du joueur
     * @param numero Le numero du joueur dans l'equipe
     * @param idEquipe L'id de l'equipe
     * @param datedebut La date d'entree dans l'equipe du joueur
     * @param datefin La date de sortie de l'equipe du joueur
     * @throws SQLException 
     */
    public void ajout(int idJoueur, int idEquipe, int numero, Date datedebut, Date datefin) throws SQLException {
        stmtInsert.setInt(1,idJoueur);
        stmtInsert.setInt(2,idEquipe);
        stmtInsert.setInt(3,numero);
        stmtInsert.setDate(4,datedebut);
        stmtInsert.setDate(5,datefin);
        try{
            stmtInsert.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout du joueur " +idJoueur
            + " dans l'equipe " + idEquipe + ".");
        }
    }

    /**
     * Suppression d'une relation joueur equipe
     * @param idJoueur
     * @param idEquipe
     * @return -1 si la suppression s'est mal passee.
     * @throws SQLException 
     */
    public int suppression(int idJoueur, int idEquipe) throws SQLException {
        stmtDelete.setInt(1,idJoueur);
        stmtDelete.setInt(2,idEquipe);
        try{
            return stmtDelete.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la suppression du joueur " +idJoueur
            + " dans l'equipe " + idEquipe + ".");
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
            System.out.println("SYSERREUR - Probleme lors de la suppression des participations joueur " +idJoueur
             + ".");
        }
        return -1;
    }

    /**
     * Rend le nombre de joueurs dans l'equipe en parametre
     * @param idEquipe L'id de l'equipe dont on veut le nombre de joueur
     * @return Le nombre de joueurs
     * @throws SQLException 
     */
    public int getNbJoueur(int idEquipe) throws SQLException{
    	stmtEquipe.setInt(1, idEquipe);
        int result = -1;
    	try (ResultSet rset = stmtEquipe.executeQuery()) { 
            if (rset.next()) {
                result = rset.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de la recuperation du nombre de joueurs de l'equipe "+idEquipe+".");
        }
    	return result;
    }
}
