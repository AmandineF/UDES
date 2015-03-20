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
 *
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
     */
    public static void main(String args[]) throws Exception, SQLException, IOException {
    	boolean bool = true;
        if (args.length == 3) {
        	Connexion conn = new Connexion(args[0], args[1], args[2]);
            while(bool){
	        	System.out.println("Merci de bien vouloir entrer une fonction ou bien 'E' pour quitter\n");
	        	Scanner sc = new Scanner(System.in);
	            String res = sc.nextLine();
	            if(res.equals("E") || res.equals("e")){
	            	bool = false;
	            }else{
	            	String[] stringTab = res.split(" ");
	            	evaluation(stringTab);
	            }
            }
        }else if(args.length == 4){
        	Connexion conn = new Connexion(args[0], args[1], args[2]);
        	
        	BufferedReader lecteurAvecBuffer = null;
            String ligne;
            try{
            	lecteurAvecBuffer = new BufferedReader(new FileReader(args[4]));
            }catch(FileNotFoundException exc){
            	System.out.println("Erreur d'ouverture du fichier");
            }
            
            while ((ligne = lecteurAvecBuffer.readLine()) != null){
            	String[] stringTab = ligne.split(" ");
            	evaluation(stringTab);
            }
            
            lecteurAvecBuffer.close();
            
        }else{
        	System.out.println("Erreur dans le nombre d'arguments");
        	
        }
    }
    
    public static void evaluation(String[] stringTab) throws Exception, SQLException, IOException{
    	switch(stringTab[0]){
    	case "creerEquipe" : 
    						if(stringTab[2] == null && stringTab[1] != null ){
    							gestionequipe.creerEquipe(stringTab[1]);
    						}else if(stringTab[1] != null && stringTab[2] != null && stringTab[3] != null){
    							gestionequipe.creerEquipe(stringTab[1], stringTab[2], stringTab[3] );
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    						
    	case "afficherEquipes" : 
    						gestionequipe.afficherEquipes();
    						break;
    						
    	case "supprimerEquipe" : 
    						if(stringTab[1] != null){
    							gestionequipe.supprimerEquipe(stringTab[1]);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    						
    	case "creerJoueur" : 
    						if(stringTab[1] != null && stringTab[2] != null && stringTab[3] == null){
    							gestionjoueur.creerJoueur(stringTab[1], stringTab[2]);
    						}else if(stringTab[1] != null && stringTab[2] != null && stringTab[3] != null && stringTab[4] != null && stringTab[5] == null ){
    							gestionjoueur.creerJoueur(stringTab[1], stringTab[2], stringTab[3], Integer.parseInt(stringTab[4]));
    						}else if(stringTab[1] != null && stringTab[2] != null && stringTab[3] != null && stringTab[4] != null && stringTab[5] != null ){
    							SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    							Date parsed = format.parse(stringTab[5]);
    					        java.sql.Date sql = new java.sql.Date(parsed.getTime());
    							gestionjoueur.creerJoueur(stringTab[1], stringTab[2], stringTab[3], Integer.parseInt(stringTab[4]), sql);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    						
    	case "afficherJoueursEquipe" : 
    						if(stringTab[1] == null)
    								gestionjoueur.afficherJoueursEquipe();
    						else if(stringTab[1] != null){
    							gestionjoueur.afficherJoueursEquipe(stringTab[1]);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    	
    	case "supprimerJoueur" : 
    						if(stringTab[1] != null && stringTab[2] != null){
    							gestionjoueur.supprimerJoueur(stringTab[1], stringTab[2]);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    		
    	case "creerMatch" : 
    						if(stringTab[1] != null && stringTab[2] != null && stringTab[3] != null && stringTab[4] != null){
    							SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    							Date parsed = format.parse(stringTab[1]);
    					        java.sql.Date sql = new java.sql.Date(parsed.getTime());
    					        DateFormat formatter = new SimpleDateFormat("HH:mm");
    					        java.sql.Time timeValue = new java.sql.Time(formatter.parse(stringTab[2]).getTime());
    							gestionmatch.creerMatch(sql,timeValue,stringTab[3],stringTab[4]);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    		
    	case "creerArbitre" : 
    						if(stringTab[1] != null && stringTab[2] != null){
    							gestionarbitre.creerArbitre(stringTab[1],stringTab[2]);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    		
    	case "afficherArbitres" : 
    						gestionarbitre.afficherArbitre();
    						break;
    		
    	case "arbitrerMatch" : 
    						if(stringTab[1] != null && stringTab[2] != null && stringTab[3] != null && stringTab[4] != null && stringTab[5] != null && stringTab[6] != null){
    							SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    							Date parsed = format.parse(stringTab[1]);
    					        java.sql.Date sql = new java.sql.Date(parsed.getTime());
    					        DateFormat formatter = new SimpleDateFormat("HH:mm");
    					        java.sql.Time timeValue = new java.sql.Time(formatter.parse(stringTab[2]).getTime());
    							gestionarbitrer.arbitrerMatch(sql, timeValue, stringTab[3], stringTab[4], stringTab[5], stringTab[6]);
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    						
    	case "entrerResultatMatch" : 
    						if(stringTab[1] != null && stringTab[2] != null && stringTab[3] != null && stringTab[4] != null && stringTab[5] != null && stringTab[6] != null){
    							SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    							Date parsed = format.parse(stringTab[1]);
    					        java.sql.Date sql = new java.sql.Date(parsed.getTime());
    					        DateFormat formatter = new SimpleDateFormat("HH:mm");
    					        java.sql.Time timeValue = new java.sql.Time(formatter.parse(stringTab[2]).getTime());
    							gestionmatch.entrerResultat(sql, timeValue, stringTab[3], stringTab[4], Integer.parseInt(stringTab[5]),  Integer.parseInt(stringTab[6]));
    						}else{
    							System.out.println("Erreur au niveau des arguments donnés");
    						}
    						break;
    		
    	case "afficherResultatsDate" : 
		            		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
							Date parsed = format.parse(stringTab[1]);
					        java.sql.Date sql = new java.sql.Date(parsed.getTime());
    						gestionmatch.afficherResultatsDate(sql);
    						break;
    			
    	case "afficherResultats" : 
    						gestionmatch.afficherResultatEquipe(stringTab[1]);
    						break;
    	
    	case "--" : //Ligne de commentaire
    						break;
    	default : 
    			System.out.println("Erreur au niveau des arguments donnés / default");
    			break;
    	
    	}
    }
}
