package ligueBaseball;
import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Gère les requêtes SQL vers la classe Equipe
 * @author Amandine Fouillet - Frank Chassing
 */
public class Equipe {
    private final PreparedStatement stmtId;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtExisteNom ;
    private final PreparedStatement stmtAffiche ;
    private final PreparedStatement stmtTerrain;
    private final Connexion cx;

    /**
     * Constructeur de la classe Équipe
     * @param cx La connexion
     * @throws SQLException 
     */
    public Equipe(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtTerrain = cx.getConnection().prepareStatement("select terrainid from equipe where equipenom = ?");
        stmtId = cx.getConnection().prepareStatement("select equipeid from equipe where equipenom = ?");
        stmtExiste = cx.getConnection().prepareStatement("select equipeid, terrainid, equipenom from equipe where equipeid = ?");
        stmtExisteNom = cx.getConnection().prepareStatement("select equipeid, terrainid, equipenom from equipe where equipenom = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into equipe (equipeid, terrainid, equipenom) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from equipe where equipeid = ?");
        stmtAffiche = cx.getConnection().prepareStatement("select * from equipe order by equipenom ");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return this.cx;
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
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation de l'id de l'equipe " + nom +".");
        }
        return res;
    }
    /**
    * Verifie si une équipe existe.
     * @param idEquipe
     * @return vrai si l'arbitre existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existeId(int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idEquipe);
        boolean equipeExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            equipeExiste = rset.next();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance de l'equipe " + idEquipe +".");
        }
        return equipeExiste;
    }
	
	/**
     * Verifie si le nom d'une equipe existe.
      * @param nomEquipe
      * @return vrai si l'arbitre existe, faux sinon
      * @throws java.sql.SQLException 
     */
     public boolean existeNom(String nomEquipe) throws SQLException {
         stmtExisteNom.setString(1,nomEquipe);
         boolean equipenomExiste = false;
         try (ResultSet rset = stmtExisteNom.executeQuery()) {
        	 equipenomExiste = rset.next();
         }catch(Exception ex){
             System.out.println("SYSERREUR - Probleme lors de la verification de l'existance de l'equipe " + nomEquipe +".");
         }
         return equipenomExiste;
     }

    /**
     * Donne le tuple de l'équipe ayant pour id idEquipe
     * @param idEquipe L'id de l'équipe dont on veut le tuple
     * @return Le Tuple résultat
     * @throws SQLException 
     */
    public TupleEquipe getEquipe(int idEquipe) throws SQLException {
        stmtExiste.setInt(1,idEquipe);
        try(ResultSet rset = stmtExiste.executeQuery()) {
            if (rset.next()) {
                TupleEquipe tupleEquipe;
                tupleEquipe = new TupleEquipe();
                tupleEquipe.idEquipe = idEquipe;
                tupleEquipe.idTerrain = rset.getInt(2);
                tupleEquipe.nom = rset.getString(3);
                rset.close();
                return tupleEquipe;
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation de l'equipe " + idEquipe +".");
        }
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
        if (idTerrain < 0) {
            stmtInsert.setNull(2, java.sql.Types.INTEGER);
        } else {
            stmtInsert.setInt(2, idTerrain);
        }
        stmtInsert.setString(3,nom);
        try{
            stmtInsert.executeUpdate();
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'ajout d'une nouvelle equipe.");
        }
    }

    /**
     * Suppression d'une equipe
     * @param idEquipe
     * @return -1 si la supression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idEquipe) throws SQLException {
        stmtDelete.setInt(1,idEquipe);
        try{
            return stmtDelete.executeUpdate();
        }catch (Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la suppression d'une equipe.");
        }
        return -1;
    }
	
    /**
     * Affichage des equipes
     * @return La liste des equipes
     * @throws SQLException 
     */
    public Vector<TupleEquipe> affiche() throws SQLException{
    	Vector<TupleEquipe>  listeEquipe;
        listeEquipe = new Vector<> ();
    	try(ResultSet rset = stmtAffiche.executeQuery()){
            while(rset.next()){
                TupleEquipe tupleEquipe;
                tupleEquipe = new TupleEquipe();
                tupleEquipe.idEquipe = rset.getInt(1);
                tupleEquipe.idTerrain = rset.getInt(2);
                tupleEquipe.nom = rset.getString(3);
                listeEquipe.add(tupleEquipe);
            }
        }catch (Exception ex){
            System.out.println("SYSERREUR - Probleme lors de l'affichage d'une equipe.");
        }
    	return listeEquipe;
    }

    public int getIDTerrain(String nomEquipe) throws SQLException {
        stmtTerrain.setString(1,nomEquipe);
        try(ResultSet rset = stmtTerrain.executeQuery()) {
            if (rset.next()) {
            	System.out.println(rset.getInt(1));
                return rset.getInt(1);
            }else{
            	System.out.println("hello");
            	return -1;
            }
        }catch(Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation de l'id terrain de l'equipe " + nomEquipe +".");
            return -1;
        }
        
    }
}
