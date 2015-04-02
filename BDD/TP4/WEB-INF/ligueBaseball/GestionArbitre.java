package ligueBaseball;
import java.sql.*;
import java.util.Vector;

/**
 * Gestion des demandes utilisateurs vers la table arbitre
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionArbitre {
    private final Arbitre arbitreTable;
    private final Sequence sequence;
    private final Connexion cx;
    
    /**
     * Constructeur de la classe GestionArbitre
     * @param co La connexion
     * @throws SQLException
     */
    public GestionArbitre(Connexion co) throws SQLException {
        this.cx = co;
        this.arbitreTable = new Arbitre(this.cx);
        this.sequence = new Sequence(this.cx);
    }
    
    /**
     * Méthode permettant de créer un arbitre
     * @param nom Le nom de l'arbitre
     * @param prenom Le prénom de l'arbitre
     * @throws SQLException 
     */
    public void creerArbitre(String nom, String prenom) throws SQLException {
        if(arbitreTable.existeHomonyme(nom, prenom) > -1) {
            System.out.println("USERERREUR - L'arbitre " + nom + " " + prenom + " existe deja.");
        } else {
            int idArbitre = sequence.getCle("arbitre");
            arbitreTable.ajout(idArbitre, nom, prenom);
            sequence.setCle((idArbitre+1),"arbitre");
            System.out.println("SUCCES - L'arbitre " + nom + " " + prenom + " a ete cree.");
        }
    }
    
    /**
     * Méthode qui permet d'afficher la liste des arbitres de la table
     * @throws SQLException 
     */
    public void afficherArbitre() throws SQLException {
        Vector<TupleArbitre> tabArbitre;
        tabArbitre = new Vector<TupleArbitre>();
        tabArbitre = arbitreTable.afficher();
        
        //Affichage des arbitres triés
        System.out.println("Liste des arbitres : \n");
        for(int i = 0; i < tabArbitre.size() ; i++) {
            System.out.println(tabArbitre.elementAt(i).toString());
        }
        
    }
}
