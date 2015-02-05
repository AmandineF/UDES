import java.io.*;
import java.util.*;
public class Inventaire {
	private static Vector<Enregistrement> vect = new Vector<Enregistrement>();
	private static String nomFichier = null;
	
	public static void remplirVecteur(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer le nom du fichier à ouvrir ou à créer : ");
		String fichier = sc.nextLine();
	    try{
		    FileReader f = new FileReader(fichier);
		    Scanner scanner = new Scanner(f);
		    scanner.useDelimiter(";");
		    while (scanner.hasNext()) {
		    	Enregistrement enr = new Enregistrement();
		    	enr.getJoueur().setCle(scanner.next());
		    	enr.getJoueur().setNomJoueur(scanner.next());
		    	String s = scanner.next();
		    	s = s.substring(1, s.length()-1);
		    	int tmp = Integer.parseInt(s);
		    	enr.getJoueur().setNombreCartes(tmp);
		    	while(tmp>0){
		    		String titretmp = scanner.next();
		    		String nomtmp = scanner.next();
		    		String anneetmp = scanner.next();
		    		anneetmp = anneetmp.substring(1, anneetmp.length()-1);
		    		int annee = Integer.parseInt(anneetmp);
		    		Carte c = new Carte(titretmp, nomtmp, annee);
		    		enr.getListeCarte().add(c);
		    		tmp--;
		    	}
		    	vect.add(enr);
		    }
		    nomFichier = fichier;
        }
        catch(FileNotFoundException e){
        	nomFichier = fichier;
        	try{
		    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFichier)));
		    }
		    catch(IOException exc){
		    	System.out.println("Erreur à la creation du fichier");
		    }
        }
	}
	
	public static void afficheMenu(){
		remplirVecteur();
		boolean bool = true;
		while(bool){
			System.out.println("Application de gestion de cartes de baseball\n");
			System.out.println("Voici la liste d'opérations valides :");
			System.out.println("1. Ajouter un joueur\n2. Afficher l'information d'un joueur\n3. Mise à jour de l'information d'un joueur\n4. Effacer l'information d'un joueur\n5. Liste des joueurs\n6. Sauvegarde\n0. Sortir");
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer votre choix : ");
			String str = sc.nextLine();
			switch(str){
				case("1"):
				case("2"):
				case("3"):
				case("4"):
				case("5"):
				case("6"): System.out.println("Resultat : " + str); 
				System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
				sc.nextLine();
		        break;
				case("0"): bool = false; break;
				default: System.out.println("Merci de renseigner un des chiffres du menu !"); 
				System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
				sc.nextLine();
				break;
			}
		}
	}
	public static void main(String [] args){
		afficheMenu();
	}
	
}

