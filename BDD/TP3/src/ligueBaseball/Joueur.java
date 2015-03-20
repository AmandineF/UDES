package ligueBaseball;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class Joueur {
    private final PreparedStatement stmtId;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtDelete;
    private final PreparedStatement stmtAffiche;
    private final PreparedStatement stmtAfficheEquipe;
    private final Connexion cx;

    /**
     * Constructeur de la classe Joueur
     * @param cx La connexion
     * @throws SQLException 
     */
    public Joueur(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtId = cx.getConnection().prepareStatement("select joueurid from joueur where joueurnom = ? and joueurprenom = ?");
        stmtExiste = cx.getConnection().prepareStatement("select * from joueur where joueurid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into joueur (joueurid, joueurnom, joueurprenom) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from joueur where joueurid = ?");
        stmtAffiche = cx.getConnection().prepareStatement("select j.joueurid, j.joueurnom, e.equipenom "
        		+ "											from joueur as j, faitpartie as f, equipe as e "
        		+ "											where j.joueurid = f.joueurid and f.equipeid = e.equipeid"
        		+ "											order by joueurid");
        stmtAfficheEquipe = cx.getConnection().prepareStatement("select j.joueurid, j.joueurnom "
        		+ "												from joueur as j, faitpartie as f, equipe as e "
        		+ "												where j.joueurid = f.joueurid and f.equipeid = e.equipeid and e.equipenom = ?"
        		+ "												order by joueurid");
    }

    /**
    * Retourner la connexion a la base de donnée
    * @return La connexion
    */
    public Connexion getConnexion() {
        return cx;
    }

    /**
     * Retourne l'id d'un joueur
     * @param nom Le nom du joueur
     * @param prenom Le prenom du joueur
     * @return l'id du joueur correspondant
     * @throws SQLException 
     */
    public int getId(String nom, String prenom) throws SQLException {
        stmtId.setString(1,nom);
        stmtId.setString(2,prenom);
        int res = -1;
        try (ResultSet rset = stmtId.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }
        stmtId.close();
        return res;
    }
    /**
    * Verifie si un joueur existe
     * @param idJoueur
     * @return vrai si le joueur existe, faux sinon
     * @throws java.sql.SQLException 
    */
    public boolean existe(int idJoueur) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        boolean joueurExiste;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            joueurExiste = rset.next();
        }
        stmtExiste.close();
        return joueurExiste;
    }

    /**
     * 
     * @param idJoueur
     * @return le tupleJoueur correspondant au joueur ayant l'id "idJoueur", null sinon
     * @throws SQLException 
     */
    public TupleJoueur getJoueur(int idJoueur) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        ResultSet rset;
        rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleJoueur tupleJoueur;
            tupleJoueur = new TupleJoueur();
            tupleJoueur.idJoueur = idJoueur;
            tupleJoueur.nom = rset.getString(2);
            tupleJoueur.prenom = rset.getString(3);
            rset.close();
            return tupleJoueur;
        }
        return null;
    }

    /**
     * Ajout d'un nouveau joueur dans la base de donnees.
     * @param idEquipe
     * @param nom
     * @param idTerrain
     * @throws SQLException 
     */
    public void ajout(int idJoueur, String nom, String prenom) throws SQLException {
        stmtInsert.setInt(1,idJoueur);
        stmtInsert.setString(2,nom);
        stmtInsert.setString(3,prenom);
        stmtInsert.executeUpdate();
        stmtInsert.close();
    }

    /**
     * Suppression d'un joueur
     * @return 
     * @throws SQLException 
     */
    public int suppression(int idJoueur) throws SQLException {
        stmtDelete.setInt(1,idJoueur);
        stmtDelete.close();
        return stmtDelete.executeUpdate();
    }
    
    /**
     * Affiche la liste des joueurs avec leur equipe
     * @return la liste des joueurs
     * @throws SQLException 
     */
    public LinkedList<String> affiche() throws SQLException{
    	LinkedList<String> listeJoueur = new LinkedList<String>();
    	ResultSet rset = stmtAffiche.executeQuery();
    	while(rset.next()){
    		int id = rset.getInt(1);
    		String nomJoueur = rset.getString(2);
    		String nomEquipe = rset.getString(3);
    		listeJoueur.add(""+id+" - "+nomJoueur+" - "+nomEquipe+"\n");
    	}
    	stmtAffiche.close();
    	return listeJoueur;
    }
    
    /**
     * Affiche la liste des joueurs de l'equipe en parametre
     * @param equipenom Le nom de l'equipe
     * @return la liste des joueurs
     * @throws SQLException 
     */
    public LinkedList<String> affiche(String equipenom) throws SQLException{
    	LinkedList<String> listeJoueur = new LinkedList<String>();
    	stmtAfficheEquipe.setString(1, equipenom);
    	ResultSet rset = stmtAfficheEquipe.executeQuery();
    	while(rset.next()){
    		int id = rset.getInt(1);
    		String nomJoueur = rset.getString(2);
    		listeJoueur.add(""+id+" - "+nomJoueur+"\n");
    	}
    	stmtAfficheEquipe.close();
    	return listeJoueur;
    }
    
    
    
    
    
    
    
    
}
