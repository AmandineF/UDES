package ligueBaseball;
import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
/**
 * Gestion des demandes utilisateurs vers la table joueur
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionJoueur {
    private final Equipe equipeTable;
    private final Sequence sequence;
    private final Joueur joueur;
    private final FaitPartie faitpartie;
    private final Participe participe;
    private final Connexion cx;
    
    /**
     * 
     * @param co
     * @throws SQLException
     */
    public GestionJoueur(Connexion co) throws SQLException {
        this.cx = co;
        this.equipeTable = new Equipe(this.cx);
        this.sequence = new Sequence(this.cx);
        this.joueur = new Joueur(this.cx);
        this.participe = new Participe(this.cx);
        this.faitpartie = new FaitPartie(this.cx);
        
    }
    
    /**
     * Cree un joueur dans la base de donnee
     * @param nom le nom du joueur
     * @param prenom le prenom du joueur
     * @throws SQLException 
     */
    public void creerJoueur(String nom, String prenom) throws SQLException  {
        if(joueur.existe(nom, prenom)) {
            System.out.println("USERERREUR - Le joueur "+prenom+ " "+nom+" existe deja.");
        }else{
            int idJoueur = sequence.getCle("joueur");
            joueur.ajout(idJoueur, nom, prenom);
            System.out.println("SUCCES - Le joueur " + prenom + " " + nom + " a ete ajoute !");
            sequence.ajout((idJoueur + 1), "joueur");
        }
    }
    
    /**
     * Cree un joueur dans la base de donnee et le lie a une equipe
     * @param nom le nom du joueur
     * @param prenom le prenom du joueur
     * @param equipenom Le nom de l'equipe
     * @param numero Le numero de l'equipe
     * @throws SQLException 
     */
    public void creerJoueur(String nom, String prenom, String equipenom, int numero) throws SQLException  {
    	if(!joueur.existe(nom, prenom)) {
            int idJoueur = sequence.getCle("joueur");
            int idEquipe = equipeTable.getId(equipenom);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
            joueur.ajout(idJoueur, nom, prenom);
            faitpartie.ajout(idJoueur, idEquipe, numero, sqlDate, null);
            System.out.println("SUCCES - Le joueur " + prenom + " " + nom + " a ete ajoute et lie a une equipe.");
            sequence.ajout((idJoueur + 1), "joueur");
        } else {
            System.out.println("USERERREUR - Le joueur " + prenom + " "  + nom + " existe deja.");
        }
    }
    
    /**
     * Cree un joueur dans la base de donnee et le lie a une equipe avec une date de debut
     * @param nom le nom du joueur
     * @param prenom le prenom du joueur
     * @param equipenom Le nom de l'equipe
     * @param numero Le numero de l'equipe
     * @param datedebut La date d'entree dans l'equipe du joueur
     * @throws SQLException 
     */
    public void creerJoueur(String nom, String prenom, String equipenom, int numero, Date datedebut) throws SQLException  {
    	if(!joueur.existe(nom, prenom)) {
            int idJoueur = sequence.getCle("joueur");
            int idEquipe = equipeTable.getId(equipenom);
            joueur.ajout(idJoueur, nom, prenom);
            faitpartie.ajout(idJoueur, idEquipe, numero, datedebut, null);
            System.out.println("SUCCES - Le joueur " + prenom + " " + nom + " a ete ajoute et lie a une equipe depuis une certain date.");
            sequence.ajout((idJoueur + 1), "joueur");
        }else{
            System.out.println("USERERREUR - Le joueur " + prenom + " "  + nom + " existe deja.");
        }
    }
    
    /**
     * Affiche la liste des joueurs
     * @throws SQLException 
     */
    public void afficherJoueursEquipe() throws SQLException {
    	LinkedList<String> listeJoueur = joueur.affiche();
    	if(listeJoueur.isEmpty()){
    		System.out.println("Pas de joueur a afficher.");
    	}else{
            System.out.println("Affichage de tous les joueurs : \n");
            listeJoueur.stream().forEach((str) -> {
                System.out.println(""+str);
            });
    	}
    }
    /**
     * Affiche la liste des joueurs
     * @return 
     * @throws SQLException 
     */
    public Vector<TupleJoueur> afficherJoueursEquipeVector() throws SQLException {
    	return joueur.afficheVector();	
    }
    
    /**
     * Affiche la liste des joueurs
     * @param equipenom
     * @return 
     * @throws SQLException 
     */
    public Vector<TupleJoueur> afficherJoueursEquipeVector(String equipenom) throws SQLException {
    	if(equipeTable.existeNom(equipenom)) {
            return joueur.afficheVector(equipenom);	
        }else{
            System.out.println("USERERREUR – L'equipe n'existe pas.");
        }
        return null;
    }
    /**
     * Affiche la liste des joueurs de l'equipe en parametre
     * @param equipenom le nom de l'equipe
     * @throws SQLException 
     */
    public void afficherJoueursEquipe(String equipenom) throws SQLException {
        if(equipeTable.existeNom(equipenom)) {
            LinkedList<String> listeJoueur = joueur.affiche(equipenom);
            if(listeJoueur.isEmpty()){
                System.out.println("Pas de joueur a afficher.");
            }else{
                System.out.println("Affichage des joueurs de l'equipe " +equipenom+" : \n");
                listeJoueur.stream().forEach((str) -> {
                    System.out.println(""+str);
                });
            }
        }else{
            System.out.println("USERERREUR – L'equipe n'existe pas.");
        }
        
    }
    
    /**
     * Supprime un joueur de la base
     * @param nom le nom du joueur
     * @param prenom Le prenom du joueur
     * @throws SQLException 
     */
    public void supprimerJoueur(String nom, String prenom) throws SQLException{
    	int idJoueur = joueur.getId(nom, prenom);
    	if(faitpartie.existeJoueur(idJoueur) || participe.existeJoueur(idJoueur)){
    		//System.out.println("USERWARNING - Le joueur "+nom+" "+prenom+" possede des donnees dans d'autre table, etes-vous sur de vouloir supprimer le joueur ainsi que ses informations ? O|N");
    		Scanner sc = new Scanner(System.in);
                String res = sc.nextLine();
            //if(res.equals("O") || res.equals("o")){
            	faitpartie.suppression(idJoueur);
            	participe.suppression(idJoueur);
                joueur.suppression(idJoueur);
            	System.out.println("SUCCES - Le joueur "+prenom + " " + nom + " a ete supprime.");
            //}else{
            //	System.out.println("Annulation de la suppression.");
            //}
    	}else{
            if(joueur.existe(nom, prenom)){
                joueur.suppression(idJoueur);
                System.out.println("SUCCES - Le joueur "+prenom + " " + nom + " a ete supprime.");
            }else{
                System.out.println("USERERREUR - Le joueur "+prenom + " " + nom + " n'existe pas.");
            }
    	}
    }
    
    public int getNumeroJoueur(int idJoueur) throws SQLException {
        return faitpartie.getNumeroJoueur(idJoueur);
    }
    
    public Date getDateJoueur(int idJoueur) throws SQLException {
        return faitpartie.getDateJoueur(idJoueur);
    }
        
}
























