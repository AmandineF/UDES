package ligueBaseball;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionArbitre {
    private Arbitre arbitreTable;
    
    public GestionArbitre(){}
    
    /**
     * 
     * @return le prochain identifiant libre
     * @throws SQLException 
     */
    public int prochainId() throws SQLException {
        int id = 0;
        boolean res = false;
        while (!res) {
            if(!arbitreTable.existe(id)) {
                res = true;
                return id;
            } else {
                id++;
            }
        }
        return -1;
    }
    
    /**
     * Méthode permettant de créer un arbitre
     * @param nom Le nom de l'arbitre
     * @param prenom Le prénom de l'arbitre
     * @throws SQLException 
     */
    public void creerArbitre(String nom, String prenom) throws SQLException {
        if(arbitreTable.existeHomonyme(nom, prenom) > -1) {
            System.out.println("Il existe déjà un arbitre avec le nom " + nom + "et le prénom " + prenom + ".");
        } else {
            int idArbitre = prochainId();
            arbitreTable.ajout(idArbitre, nom, prenom);
        }
    }
    
    /**
     * Méthode qui permet d'afficher la liste des arbitres de la table
     * @throws SQLException 
     */
    public void afficherArbitre() throws SQLException {
        Vector<TupleArbitre> tabArbitre = new Vector<TupleArbitre>();
        
        //Affichage des arbitres triés
        for(int i = 0; i < prochainId() ; i++) {
            System.out.println("Liste des arbitres : \n");
            System.out.println(tabArbitre.elementAt(i).toString());
        }
        
    }
}
