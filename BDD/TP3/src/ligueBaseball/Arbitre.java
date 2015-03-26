package ligueBaseball;
import java.sql.*;
import java.util.Vector;

/**
 * Gère les requêtes SQL vers la classe Arbitre
 * @author Amandine Fouillet - Frank Chassing
 */
public class Arbitre {
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtAffiche;
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
        stmtAffiche = cx.getConnection().prepareStatement("select arbitreid, arbitrenom, arbitreprenom from arbitre order by arbitrenom");
        stmtExisteHomonyme = cx.getConnection().prepareStatement("select arbitreid from arbitre where arbitrenom = ? and arbitreprenom = ?");
        stmtExiste = cx.getConnection().prepareStatement("select arbitreid, arbitrenom, arbitreprenom from arbitre where arbitreid = ?");
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
     * Methode permettant l'affichage des arbitres de la table 
     * @return Un vector contenant les arbitres a afficher
     * @throws SQLException 
     */
    public Vector<TupleArbitre> afficher() throws SQLException {
        Vector<TupleArbitre> res = new Vector<TupleArbitre>();
        try (ResultSet rset = stmtAffiche.executeQuery()) {
            while(rset.next()){
                TupleArbitre tupleArbitre = new TupleArbitre();
                tupleArbitre.idArbitre = rset.getInt(1);
                tupleArbitre.nom = rset.getString(2);
                tupleArbitre.prenom = rset.getString(3);
                res.add(tupleArbitre);
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de l'affichage des arbitres.");
        }
        return res;
    }
    
    /**
    * Verifie si un arbitre existe.
     * @param idArbitre L'id de l'arbitre dont on veut verifier l'existance
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idArbitre) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        boolean arbitreExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            arbitreExiste = rset.next();
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance de l'arbitre " +idArbitre +".");
        } 
        return arbitreExiste;
    }
    
    /**
     * Méthode permettant de savoir s'il existe un arbitre avec ce nom dans la base
     * @param nom Le nom de l'arbitre
     * @param prenom Le prenom de l'arbitre
     * @return l'id si l'homonyme existe, -1 sinon
     * @throws SQLException 
     */
    public int existeHomonyme(String nom, String prenom)throws SQLException {
        stmtExisteHomonyme.setString(1,nom);
        stmtExisteHomonyme.setString(2,prenom);
        int res = -1;
        try (ResultSet rset = stmtExisteHomonyme.executeQuery()) {
            if(rset.next()){
                res = rset.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance de l'arbitre " +prenom + " " +nom +".");
        } 
        return res;
    }
    
    /**
     * Methode qui retourne le tuple d'un arbitre
     * @param idArbitre L'idee de l'arbitre dont on veut le tuple
     * @return Le tuple contenant les donnees de l'arbitre
     * @throws SQLException 
     */
    public TupleArbitre getArbitre(int idArbitre) throws SQLException {
        stmtExiste.setInt(1,idArbitre);
        try(ResultSet rset = stmtExiste.executeQuery()){
        if (rset.next()) {
            TupleArbitre tupleArbitre;
            tupleArbitre = new TupleArbitre();
            tupleArbitre.idArbitre = idArbitre;
            tupleArbitre.nom = rset.getString(2);
            tupleArbitre.prenom = rset.getString(3);
            rset.close();
            return tupleArbitre;
        }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation des donnees de l'arbitre "+ idArbitre+".");
        }
        return null;
    }

    /**
     * Ajout d'un nouvel arbitre dans la base de donnees.
     * @param idArbitre L'id de l'arbitre
     * @param nom Le nom de l'arbitre
     * @param prenom Le prenom de l'arbitre
     * @throws SQLException 
     */
    public void ajout(int idArbitre, String nom, String prenom) throws SQLException {
        stmtInsert.setInt(1,idArbitre);
        stmtInsert.setString(2,nom);
        stmtInsert.setString(3,prenom);
        try{
            stmtInsert.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout de l'arbitre "+prenom+" "+nom+".");
        }
    }

    /**
     * Suppression d'un arbitre
     * @param idArbitre L'id de l'arbitre
     * @return -1 si la suppression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idArbitre) throws SQLException {
        stmtDelete.setInt(1,idArbitre);
        try{
            return stmtDelete.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout de l'arbitre "+idArbitre+".");
        }
        return -1;
    }

}
