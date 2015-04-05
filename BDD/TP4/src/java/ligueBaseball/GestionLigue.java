package ligueBaseball;
import java.sql.SQLException;


public class GestionLigue {
    public Connexion cx;
    public GestionArbitre gestionArbitre;
    public GestionArbitrer gestionArbitrer;
    public GestionEquipe gestionEquipe;
    public GestionJoueur gestionJoueur;
    public GestionMatch gestionMatch;

    public GestionLigue(String bd, String user, String password) throws SQLException {
        // allocation des objets pour le traitement des transactions
        cx = new Connexion(bd, user, password);
        gestionArbitre = new GestionArbitre(cx);
        gestionArbitrer = new GestionArbitrer(cx);
        gestionEquipe = new GestionEquipe(cx);
        gestionJoueur = new GestionJoueur(cx);
        gestionMatch = new GestionMatch(cx);
    }

    public void fermer() throws SQLException {
        //Fermeture de la connexion
        cx.fermer();
    }
}