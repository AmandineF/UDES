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
        boolean ok;
        
        for(int i = 0; i < prochainId() ; i++) {
            TupleArbitre next = arbitreTable.getArbitre(i);
            ok = false;
            for(int j = 0; j<tabArbitre.size() - 1; i++) {
                if((tabArbitre.elementAt(i).nom.compareTo(next.nom) > 0) && (tabArbitre.elementAt(i + 1).nom.compareTo(next.nom) < 0)) {     
                    tabArbitre.add(i + 1, next);
                    ok = true;
                    break;  
                } else if (tabArbitre.elementAt(i).nom.compareTo(next.nom) < 0) {
                    //Cas ou l'élément doit être placé en première position
                    tabArbitre.add(0, next);
                    ok = true;
                    break;
                }
            }
            if(!ok) {
                //Cas ou l'élément doit être placé en dernière position
                tabArbitre.add(next);
            }
        }
        //Affichage des arbitres triés
        for(int i = 0; i < prochainId() ; i++) {
            System.out.println("Liste des arbitres : \n");
            System.out.println(tabArbitre.elementAt(i).toString());
        }
        
    }
}
