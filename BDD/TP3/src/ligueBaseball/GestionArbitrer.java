package ligueBaseball;
import java.sql.*;
/**
 * Gestion des demandes utilisateurs vers la table arbitrer
 * @author Amandine Fouillet - Frank Chassing
 */

public class GestionArbitrer {
    private final Arbitrer arbitrerTable;
    private final Arbitre arbitreTable;
    private final Match matchTable;
    private final Equipe equipeTable;
    private final Connexion cx;
    
    /**
     * Constructeur de la classe GestionArbitrer
     * @param co La connexion
     * @throws SQLException
     */
    public GestionArbitrer(Connexion co) throws SQLException {
        this.cx = co;
        this.arbitreTable = new Arbitre(this.cx);
        this.arbitrerTable = new Arbitrer(this.cx);
        this.matchTable = new Match(this.cx);
        this.equipeTable = new Equipe(this.cx);
    }
    
    /**
     * Methode permettant de rajouter un arbitrage dans la table arbitrer
     * @param matchDate La date du match
     * @param matchHeure L'heure du match
     * @param nomEquipeLocale Le nom de l'equipe locale
     * @param nomEquipeVisiteur Le nom de l'equipe visiteur
     * @param arbitreNom Le nom de l'arbitre du match
     * @param arbitrePrenom Le prenom de l'arbitre du match
     * @throws SQLException 
     */
    public void arbitrerMatch(Date matchDate, Time matchHeure, String nomEquipeLocale, String nomEquipeVisiteur, String arbitreNom, String arbitrePrenom) throws SQLException  {
        int idLocaux = equipeTable.getId(nomEquipeLocale);
        int idVisiteurs = equipeTable.getId(nomEquipeVisiteur);
        int idArbitre = arbitreTable.existeHomonyme(arbitreNom, arbitrePrenom);
        int idMatch = matchTable.getId(matchDate, matchHeure, idLocaux, idVisiteurs);
        if(idMatch > -1) {
            if(idArbitre > -1) {
                arbitrerTable.ajout(idArbitre, idMatch);
                System.out.println("SUCCES - L'arbitrage a ete rajoute.");
            } else {
                System.out.println("USERERREUR - L'arbitre "+ arbitrePrenom + " "+ arbitreNom+ " n'existe pas.");
            }
        } else {
            System.out.println("USERERREUR - Le match " + nomEquipeLocale+ " - " + nomEquipeVisiteur 
                    + "se deroulant le " + matchDate.toString() + " a "+matchHeure.toString()+ " n'existe pas.");
        }
    }
    
}
