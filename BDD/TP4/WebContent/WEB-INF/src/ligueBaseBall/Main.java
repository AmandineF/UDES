package ligueBaseball;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Programme principal, lecture des arguments utilisateur
 * @author Amandine Fouillet - Frank Chassing
 */
public class Main {
	
	private static GestionEquipe gestionequipe;
	private static GestionJoueur gestionjoueur;
	private static GestionMatch gestionmatch;
	private static GestionArbitre gestionarbitre;
	private static GestionArbitrer gestionarbitrer;
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String args[]) throws Exception, SQLException, IOException {
    	boolean bool = true;
        Connexion conn = new Connexion(args[0], args[1], args[2]);
        Main.gestionequipe = new GestionEquipe(conn);
        Main.gestionjoueur = new GestionJoueur(conn);
        Main.gestionmatch = new GestionMatch(conn);
        Main.gestionarbitre = new GestionArbitre(conn);
        Main.gestionarbitrer = new GestionArbitrer(conn);
        int cpt = 0;
        if (args.length == 3) {
            while(bool){
	        	System.out.println("Merci de bien vouloir entrer une fonction ou bien 'E' pour quitter\n");
	        	Scanner sc = new Scanner(System.in);
	            String res = sc.nextLine();
	            if(res.equals("E") || res.equals("e")){
	            	bool = false;
	            }else{
	            	String[] stringTab = res.split(" ");
                        cpt ++;
	            	evaluation(stringTab,cpt);
	            }
            }
        }else if(args.length == 4){
        	BufferedReader lecteurAvecBuffer = null;
            String ligne;
            try{
            	lecteurAvecBuffer = new BufferedReader(new FileReader(args[3]));
            }catch(FileNotFoundException exc){
            	System.out.println("Erreur d'ouverture du fichier");
            }
            
            while ((ligne = lecteurAvecBuffer.readLine()) != null){  
            	String[] stringTab = ligne.split(" ");

                if(!stringTab[0].equals("--")) {
                    cpt++;
                }
            	evaluation(stringTab,cpt);
            }
            
            lecteurAvecBuffer.close();
            
        }else{
        	System.out.println("Erreur dans le nombre d'arguments");
        	
        }
    }

    
    public static void evaluation(String[] stringTab, int cpt) throws Exception, SQLException, IOException {
    	switch(stringTab[0]){
    	case "creerEquipe" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length < 2) {
                System.out.println("USERWARNING – Problème au niveau des paramètres - utilisation de creerEquipe : \n "
                        + "creerEquipe <EquipeNom> [<NomTerrain> AdresseTerrain]");
            } else {
                try {
                    gestionequipe.creerEquipe(stringTab[1], stringTab[2], stringTab[3]);
                } catch(Exception E) {
                    gestionequipe.creerEquipe(stringTab[1]);
                }
            }
            break;
    						
    	case "afficherEquipes" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length > 1) {
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de afficherEquipes : \n "
                        + "afficherEquipes");
            }else{
                gestionequipe.afficherEquipes();
            }
            break;
    						
    	case "supprimerEquipe" : 
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 2) { 
                gestionequipe.supprimerEquipe(stringTab[1]);
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de supprimerEquipe : \n "
                        + "supprimerEquipe <EquipeNom> ");
            }
            break;
    						
    	case "creerJoueur" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length < 3 || stringTab.length == 4) {
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de creerJoueur : \n "
                        + "creerJoueur <JoueurNom> <JoueurPrenom> [<EquipeNom> <Numero> [<DateDebut>]]");
            } else if (stringTab.length == 3) {
                gestionjoueur.creerJoueur(stringTab[1], stringTab[2]);
            } else if (stringTab.length == 5) {
                gestionjoueur.creerJoueur(stringTab[1], stringTab[2], stringTab[3], Integer.parseInt(stringTab[4]));
            } else if (stringTab.length == 6) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(stringTab[5]);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                if(sql.toString().equals(stringTab[5])) {
                    gestionjoueur.creerJoueur(stringTab[1], stringTab[2], stringTab[3], Integer.parseInt(stringTab[4]), sql);
                }else{
                    System.out.println("USERERREUR - Date invalide.");
                }
            }
            break;
    						
    	case "afficherJoueursEquipe" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length < 2) {
                gestionjoueur.afficherJoueursEquipe();
            } else if(stringTab.length == 2){
                gestionjoueur.afficherJoueursEquipe(stringTab[1]);
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de afficherJoueursEquipe : \n "
                        + "afficherJoueursEquipe [<EquipeNom >]");
            }
            break;
    	
    	case "supprimerJoueur" : 
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 3){
                gestionjoueur.supprimerJoueur(stringTab[1], stringTab[2]);
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de supprimerJoueur : \n "
                        + "supprimerJoueur <JoueurNom> <JoueurPrenom>");
            }
            break;
    		
    	case "creerMatch" : 
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 5){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = format.parse(stringTab[1]);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                java.sql.Time timeValue = new java.sql.Time(formatter.parse(stringTab[2]).getTime());
                if(sql.toString().equals(stringTab[1])) {
                    gestionmatch.creerMatch(sql,timeValue,stringTab[3],stringTab[4]);
                } else {
                    System.out.println("USERERREUR - Date invalide.");
                }
                
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de creerMatch : \n"
                        + "creerMatch <MatchDate> <MatchHeure> <EquipeNomLocal> <EquipeNomVisiteur>");
            }
            break;
    		
    	case "creerArbitre" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 3){
                gestionarbitre.creerArbitre(stringTab[1],stringTab[2]);
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de creerArbitre : \n "
                        + "creerArbitre <ArbitreNom> <ArbitrePrenom>");
            }
            break;
    		
    	case "afficherArbitres" : 
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length > 1){
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de afficherArbitres : \n"
                        + "afficherArbitres");
            }else{
                gestionarbitre.afficherArbitre();
            }
            break;
    		
    	case "arbitrerMatch" : 
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 7){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(stringTab[1]);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                java.sql.Time timeValue = new java.sql.Time(formatter.parse(stringTab[2]).getTime());
                if(sql.toString().equals(stringTab[1])) {
                    gestionarbitrer.arbitrerMatch(sql, timeValue, stringTab[3], stringTab[4], stringTab[5], stringTab[6]);
                }else{
                    System.out.println("USERERREUR - Date invalide.");
                }
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de arbitrerMatch : \n "
                        + "arbitrerMatch <MatchDate> <MatchHeure> <EquipeNomLocal> <EquipeNomVisiteur> <ArbitreNom> <ArbitrePrenom>");
            }
            break;
    						
    	case "entrerResultatMatch" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 7){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(stringTab[1]);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                java.sql.Time timeValue = new java.sql.Time(formatter.parse(stringTab[2]).getTime());
                if(sql.toString().equals(stringTab[1])) {
                gestionmatch.entrerResultat(sql, timeValue, stringTab[3], stringTab[4], Integer.parseInt(stringTab[5]),  Integer.parseInt(stringTab[6]));
                }else{
                        System.out.println("USERERREUR - Date invalide.");
                }
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de entrerResultatMatch : \n"
                        + "entrerResultatMatch <MatchDate> <MatchHeure> <EquipeNomLocal> <EquipeNomVisiteur> <PointsLocal> <PointsVisiteur>");
            }
            break;
    		
    	case "afficherResultatsDate" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(stringTab[1]);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                if(sql.toString().equals(stringTab[1])) {
                    gestionmatch.afficherResultatsDate(sql);
                }else{
                    System.out.println("USERERREUR - Date invalide.");
                }
            }else{
                System.out.println("Erreur au niveau des arguments donnes");
            }
            break;
    			
    	case "afficherResultats" :
            System.out.println("\n----------------Operation " + cpt + " ------------------------");
            if(stringTab.length == 2) {
                gestionmatch.afficherResultatEquipe(stringTab[1]);
            }else{
                System.out.println("USERWARNING – Problème au niveau des paramètres, utilisation de afficherResultats : \n"
                        + "afficherResultatsDate [<APartirDate>]");
            }
            break;
    	
    	case "--" : //Ligne de commentaire
            break;
            
    	default : 
            System.out.println("Erreur au niveau des arguments donnes / defaut");
            break;
    	
    	}
    }
}
