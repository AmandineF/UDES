package ligueBaseball;
import java.sql.*;
import java.util.Vector;
/**
 * Gestion des demandes utilisateurs vers la table match
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionMatch {
    private final Match matchTable;
    private final Equipe equipeTable;
    private final Sequence sequence;
    private final Arbitrer arbitrerTable;
    private final Arbitre arbitreTable;
    private final Connexion cx;
    
    /**
     * Constructeur de la methode GestionMatch
     * @param co
     * @throws SQLException
     */
    public GestionMatch(Connexion co) throws SQLException {
        this.cx = co;
        this.equipeTable = new Equipe(this.cx);
        this.arbitrerTable = new Arbitrer(this.cx);
        this.arbitreTable = new Arbitre(this.cx);
        this.matchTable = new Match(this.cx);
        this.sequence = new Sequence(this.cx);
    }
    
    /**
     * Methode pour creer un nouveau match
     * @param matchDate La date du match
     * @param matchHeure L'heure du match
     * @param nomEquipeLocale Le nom de l'equipe locale
     * @param nomEquipeVisiteur Le nom de l'equipe visiteur
     * @throws SQLException 
     */
    public int creerMatch(Date matchDate, Time matchHeure,String nomEquipeLocale, String nomEquipeVisiteur)throws SQLException  {
        int idMatch = sequence.getCle("match");
        if(equipeTable.existeNom(nomEquipeLocale)) {
            if(equipeTable.existeNom(nomEquipeVisiteur)) {
                matchTable.ajout(idMatch, equipeTable.getId(nomEquipeLocale), equipeTable.getId(nomEquipeVisiteur), 
                equipeTable.getEquipe(equipeTable.getId(nomEquipeLocale)).idTerrain,
                matchDate, matchHeure, 0, 0);
                sequence.ajout((idMatch+1),"match");
                System.out.println("SUCCES - Le match des "+nomEquipeLocale+ " contre " + nomEquipeVisiteur + " a ete cree.");
                return 0;
            }else{
                System.out.println("USERERREUR - L'equipe visiteur n'existe pas.");
                return 1;
            }
        }else if(equipeTable.existeNom(nomEquipeVisiteur)) {
            System.out.println("USERERREUR - L'equipe locale n'existe pas.");
            return 2;
        }else {
            System.out.println("USERERREUR - Les equipes n'existent pas.");
            return 3;
        }
    }
    /**
     * Methode pour entrer les resultats d'un match
     * @param matchDate La date du match
     * @param matchHeure L'heure du match
     * @param nomEquipeLocale Le nom de l'equipe locale
     * @param nomEquipeVisiteur Le nom de l'equipe visiteur
     * @param pointsLocal Les points de l'equipe locale
     * @param pointsVisiteur Les points de l'equipe visiteur
     * @throws SQLException 
     */
    public int entrerResultat(java.sql.Date matchDate, Time matchHeure, String nomEquipeLocale, String nomEquipeVisiteur, int pointsLocal, int pointsVisiteur)throws SQLException  {
        if(pointsLocal < 0 || pointsVisiteur < 0) {
            return 1;
            //System.out.println("USERERREUR - Veuillez rentrer des points supérieurs ou égaux à zéro.");
        } else {
            int idLocaux = equipeTable.getId(nomEquipeLocale);
            int idVisiteurs = equipeTable.getId(nomEquipeVisiteur);
            int idMatch = matchTable.getId(matchDate, matchHeure, idLocaux, idVisiteurs);
            if(matchTable.existe(idMatch)) {
                matchTable.setPoints(idMatch, pointsLocal, pointsVisiteur);
                return 0;
            }else{
                return 2;
            }
            //System.out.println("SUCCES - Les resultats du match " + nomEquipeLocale + " - " + nomEquipeVisiteur +" ont été enregistres.");
        }
    }
    
    /**
     * Methode permettant d'afficher les resultats des matchs apres une certaine date
     * @param d La date apres laquelle on veut voir les matchs
     * @throws SQLException 
     */
    public void afficherResultatsDate(Date d) throws SQLException {
        Vector<TupleMatch> res;
        if(d != null) {
            System.out.println("Résultats des matchs après la date " + d.toString() + " :\n");
            res = matchTable.getMatchApresDate(d);
        } else {
            System.out.println("Résultats des matchs :\n");
            res = matchTable.getMatch();
        }
        afficherResultat(res);
    }
    /**
     * Methode permettant d'afficher les resultats des matchs apres une certaine date
     * @param d La date apres laquelle on veut voir les matchs
     * @return 
     * @throws SQLException 
     */
    public Vector<TupleMatch> afficherResultatsDateVector(Date d) throws SQLException {
        if(d != null) {
            System.out.println("Résultats des matchs après la date " + d.toString() + " :\n");
            return matchTable.getMatchApresDate(d);
        } else {
            System.out.println("Résultats des matchs :\n");
           return matchTable.getMatch();
        }
        
    }
   
    
    /**
     * Methode generique permettant d'afficher les resultats des matchs
     * @param res Le vector des tuples des matchs dont on veut l'affichage des resultats
     * @throws SQLException 
     */
    public void afficherResultat(Vector<TupleMatch> res) throws SQLException {
        String nomArbitre = "";
        for(int i = 0; i < res.size(); i++){
            if(arbitrerTable.existeMatch(res.elementAt(i).idMatch)) {
                int idArbitre = arbitrerTable.getArbitre(res.elementAt(i).idMatch);
                nomArbitre = arbitreTable.getArbitre(idArbitre).nom;
            }
            System.out.println(res.elementAt(i).toStringResultat(nomArbitre));
        }
    }
    
   
    /**
     * Methode permettant d'afficher les resultats des matchs auxquels une certaine equipe a participe
     * @param nom Le nom de l'equipe dont on veut voir les matchs
     * @throws SQLException 
     */
    public void afficherResultatEquipe(String nom) throws SQLException {
        Vector<TupleMatch> res;
        if(!nom.equals("")) {
            if(equipeTable.existeNom(nom)){
                System.out.println("Résultats des matchs ou l'équipe " + nom + " a participé :\n");
                res = matchTable.getMatchEquipe(equipeTable.getId(nom));
                afficherResultat(res);
            }else{
                System.out.println("USERERREUR - L'equipe n'existe pas, impossible d'afficher les resultats.");
            }
        } else { 
            System.out.println("Résultats des matchs :\n");
            res = matchTable.getMatch();
        }
        
    }
    
    /**
     * Methode permettant d'afficher les resultats des matchs auxquels une certaine equipe a participe
     * @param nom Le nom de l'equipe dont on veut voir les matchs
     * @throws SQLException 
     */
    public Vector<TupleMatch> afficherResultatEquipeVector(String nom) throws SQLException {
        if(!nom.equals("")) {
            if(equipeTable.existeNom(nom)){
                System.out.println("Résultats des matchs ou l'équipe " + nom + " a participé :\n");
                return matchTable.getMatchEquipe(equipeTable.getId(nom));
            }else{
                System.out.println("USERERREUR - L'equipe n'existe pas, impossible d'afficher les resultats.");
            }
        } else { 
            System.out.println("Résultats des matchs :\n");
            return matchTable.getMatch();
        }
        return null;
    }
}
