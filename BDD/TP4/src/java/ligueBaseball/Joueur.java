package ligueBaseball;
import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Gère les requêtes SQL vers la classe Joueur
 * @author Amandine Fouillet - Frank Chassing
 */
public class Joueur {
    private final PreparedStatement stmtId;
    private final PreparedStatement stmtExiste;
    private final PreparedStatement stmtExisteHomonyme;
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
        stmtExisteHomonyme = cx.getConnection().prepareStatement("select * from joueur where joueurnom = ? and joueurprenom = ?");
        stmtId = cx.getConnection().prepareStatement("select joueurid from joueur where joueurnom = ? and joueurprenom = ?");
        stmtExiste = cx.getConnection().prepareStatement("select * from joueur where joueurid = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into joueur (joueurid, joueurnom, joueurprenom) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from joueur where joueurid = ? ");
        stmtAffiche = cx.getConnection().prepareStatement("select * from joueur");
        		//+ "from joueur as j, faitpartie as f, equipe as e "
        		//+ "where j.joueurid = f.joueurid and f.equipeid = e.equipeid");
        		//+ "order by joueurid");
        stmtAfficheEquipe = cx.getConnection().prepareStatement("select j.joueurid, j.joueurnom, j.joueurprenom "
        		+ "from joueur as j, faitpartie as f, equipe as e "
        		+ "where j.joueurid = f.joueurid and f.equipeid = e.equipeid and e.equipenom = ?");
        		//+ "order by joueurid");
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
        }catch (Exception ex){
           System.out.println("SYSERREUR - Probleme lors de la recuperation de l'id du joueur " + prenom + " " + nom + ".");
        }
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
        boolean joueurExiste = false;
        try (ResultSet rset = stmtExiste.executeQuery()) {
            joueurExiste = rset.next();
        }catch (Exception ex){
           System.out.println("SYSERREUR - Probleme lors de la verification de l'existance du joueur " + idJoueur + ".");
        }
        stmtExiste.close();
        return joueurExiste;
    }

    /**
     * Methode permettant de determiner si un joueur homonyme existe dans la base
     * @param nom Le nom du joueur
     * @param prenom Le prenom du joueur
     * @return vrai si le joueur existe deja, faux sinon
     * @throws SQLException 
     */
    public boolean existe(String nom, String prenom) throws SQLException {
        stmtExisteHomonyme.setString(1,nom);
        stmtExisteHomonyme.setString(2,prenom);
        boolean joueurExiste = false;
        try (ResultSet rset = stmtExisteHomonyme.executeQuery()) {
            joueurExiste = rset.next();  
        }catch (Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la verification de l'existance du joueur " + prenom + " "+ nom + ".");
        }
        return joueurExiste;
    }
    
    /**
     * Methode permettant de recuperer les donnees d'un joueur a partir de son id
     * @param idJoueur L'id du joueur a recuperer 
     * @return le tupleJoueur correspondant au joueur ayant l'id "idJoueur", null sinon
     * @throws SQLException 
     */
    public TupleJoueur getJoueur(int idJoueur) throws SQLException {
        stmtExiste.setInt(1,idJoueur);
        try(ResultSet rset = stmtExiste.executeQuery()) {
            if (rset.next()) {
                TupleJoueur tupleJoueur;
                tupleJoueur = new TupleJoueur();
                tupleJoueur.idJoueur = idJoueur;
                tupleJoueur.nom = rset.getString(2);
                tupleJoueur.prenom = rset.getString(3);
                rset.close();
                return tupleJoueur;
            }
        }catch (Exception ex){
            System.out.println("SYSERREUR - Probleme lors de la recuperation du joueur " + idJoueur + ".");
        }
        return null;
    }

    /**
     * Ajout d'un nouveau joueur dans la base de donnees.
     * @param idJoueur L'id du joueur a ajouter
     * @param nom Le nom du joueur a ajouter
     * @param prenom Le prenom du joueur a ajouter
     * @throws SQLException 
     */
    public void ajout(int idJoueur, String nom, String prenom) throws SQLException {
        stmtInsert.setInt(1,idJoueur);
        stmtInsert.setString(2,nom);
        stmtInsert.setString(3,prenom);
        try{
            stmtInsert.executeUpdate();
        }catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de l'ajout du joueur " + prenom + " " + nom + ".");
        }
    }

    /**
     * Suppression d'un joueur
     * @param idJoueur L'id du joueur a supprimer
     * @return -1 si la suppression s'est mal passee
     * @throws SQLException 
     */
    public int suppression(int idJoueur) throws SQLException {
        stmtDelete.setInt(1,idJoueur);
        try{
            return stmtDelete.executeUpdate();
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de la suppression du joueur " + idJoueur +".");
        }
        return -1;
    }
    
    /**
     * Affiche la liste des joueurs avec leur equipe
     * @return la liste des joueurs
     * @throws SQLException 
     */
    public LinkedList<String> affiche() throws SQLException{
    	LinkedList<String> listeJoueur = new LinkedList<String>();
    	try(ResultSet rset = stmtAffiche.executeQuery()){
            while(rset.next()){
                    int id = rset.getInt(1);
                    String nomJoueur = rset.getString(2);
                    String nomEquipe = rset.getString(3);
                    listeJoueur.add(""+id+" - "+nomJoueur+" - "+nomEquipe+"\n");
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de l'affichage des joueurs.");
        }
    	return listeJoueur;
    }
    
    /**
     * Affiche la liste des joueurs avec leur equipe
     * @return la liste des joueurs
     * @throws SQLException 
     */
    public Vector<TupleJoueur> afficheVector() throws SQLException{
    	Vector<TupleJoueur> listeJoueur = new Vector<TupleJoueur>();
    	try(ResultSet rset = stmtAffiche.executeQuery()){
            while(rset.next()){
                TupleJoueur tj = new TupleJoueur();
                tj.idJoueur = rset.getInt(1);
                tj.nom = rset.getString(2);
                tj.prenom = rset.getString(3);
                listeJoueur.add(tj);
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de l'affichage des joueurs.");
        }
    	return listeJoueur;
    }
        /**
     * Affiche la liste des joueurs avec leur equipe
     * @param equipenom
     * @return la liste des joueurs
     * @throws SQLException 
     */
    public Vector<TupleJoueur> afficheVector(String equipenom) throws SQLException{
    	Vector<TupleJoueur> listeJoueur = new Vector<TupleJoueur>();
        stmtAfficheEquipe.setString(1, equipenom);
    	try (ResultSet rset = stmtAfficheEquipe.executeQuery()){
            while(rset.next()){
                TupleJoueur tj = new TupleJoueur();
                tj.idJoueur = rset.getInt(1);
                tj.nom = rset.getString(2);
                tj.prenom = rset.getString(3);
                listeJoueur.add(tj);
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de l'affichage des joueurs de l'equipe "+ equipenom + ".");
        }
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
    	try (ResultSet rset = stmtAfficheEquipe.executeQuery()){
            while(rset.next()){
                    int id = rset.getInt(1);
                    String nomJoueur = rset.getString(2);
                    listeJoueur.add(""+id+" - "+nomJoueur+"\n");
            }
        } catch (Exception ex) {
            System.out.println("SYSERREUR - Probleme lors de l'affichage des joueurs de l'equipe "+ equipenom + ".");
        }
    	return listeJoueur;
    }
    
}
