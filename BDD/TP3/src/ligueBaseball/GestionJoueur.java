package ligueBaseball;
import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionJoueur {
    private Equipe equipeTable;
    private Sequence sequence;
    private Joueur joueur;
    private FaitPartie faitpartie;
    private Participe participe;
    
    
    /**
     * 
     */
    public GestionJoueur() {}
    
    /**
     * Cree un joueur dans la base de donnee
     * @param nom le nom du joueur
     * @param prenom le prenom du joueur
     * @throws SQLException 
     */
    public void creerJoueur(String nom, String prenom) throws SQLException  {
    	int idJoueur = sequence.getCle("joueur");
        joueur.ajout(idJoueur, nom, prenom);
        System.out.println("Joueur Ajoute !");
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
    	int idJoueur = sequence.getCle("joueur");
    	int idEquipe = equipeTable.getId(equipenom);
    	java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
        joueur.ajout(idJoueur, nom, prenom);
        faitpartie.ajout(idJoueur, idEquipe, numero, sqlDate, null);
        System.out.println("Joueur Ajoute et lie a une equipe !");
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
    	int idJoueur = sequence.getCle("joueur");
    	int idEquipe = equipeTable.getId(equipenom);
        joueur.ajout(idJoueur, nom, prenom);
        faitpartie.ajout(idJoueur, idEquipe, numero, datedebut, null);
        System.out.println("Joueur Ajoute et lie a une equipe depuis une certain date !");
    }
    
    /**
     * Affiche la liste des joueurs
     * @throws SQLException 
     */
    public void afficherJoueursEquipe() throws SQLException {
    	LinkedList<String> listeJoueur = joueur.affiche();
    	if(listeJoueur.isEmpty()){
    		System.out.println("Pas de joueur a afficher..");
    	}else{
    		for(String str : listeJoueur){
        		System.out.println(""+str);
        	}
    	}
    }
    
    /**
     * Affiche la liste des joueurs de l'equipe en parametre
     * @param equipenom le nom de l'equipe
     * @throws SQLException 
     */
    public void afficherJoueursEquipe(String equipenom) throws SQLException {
    	LinkedList<String> listeJoueur = joueur.affiche(equipenom);
    	if(listeJoueur.isEmpty()){
    		System.out.println("Pas de joueur a afficher..");
    	}else{
    		for(String str : listeJoueur){
        		System.out.println(""+str);
        	}
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
    		System.out.println("Le joueur "+nom+" "+prenom+" possède des donnees dans d'autre table, etes-vous sur de vouloir supprimer le joueur ainsi que ses informations ? O|N");
    		Scanner sc = new Scanner(System.in);
            String res = sc.nextLine();
            if(res.equals("O") || res.equals("o")){
            	joueur.suppression(idJoueur);
            	faitpartie.suppression(idJoueur);
            	participe.suppression(idJoueur);
            	System.out.println("Suppression réussie !");
            }else{
            	System.out.println("Annulation de la suppression..");
            }
    	}else{
    		joueur.suppression(idJoueur);
    		System.out.println("Suppression réussie !");
    	}
    }
        
}
























