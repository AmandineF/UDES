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
		    int i = 1;
		    while (scanner.hasNext()) {
		    	Enregistrement enr = new Enregistrement();
		    	String stmp = scanner.next();
		    	enr.getJoueur().setCle(stmp.substring(i, stmp.length()-1));
		    	i = 2;
		    	stmp = scanner.next();
		    	enr.getJoueur().setNomJoueur(stmp.substring(1, stmp.length()-1));
		    	String s = scanner.next();
		    	s = s.substring(1, s.length()-1);
		    	int tmp = Integer.parseInt(s);
		    	enr.getJoueur().setNombreCartes(tmp);
		    	while(tmp>0){
		    		String titretmp = scanner.next();
		    		titretmp = titretmp.substring(1, titretmp.length()-1);
		    		String nomtmp = scanner.next();
		    		nomtmp = nomtmp.substring(1, nomtmp.length()-1);
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
        	if(fichier.substring(fichier.length()-4, fichier.length()).equals(".txt")){
        		nomFichier = fichier;        	
	        	try{
			    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFichier)));
			    }
			    catch(IOException exc){
			    	System.out.println("Erreur à la creation du fichier");
			    }
        	}else{
        		System.out.println("Merci de rentrer un fichier txt pour la création. Veuillez recommencer.");
        		remplirVecteur();
        	}
        }
	}
	
	public static Enregistrement affichageEnregistrement(){
		System.out.println("Entrez la clé d'identification du joueur: ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		boolean bool = false;
		Enregistrement enr = null;
		for (Enregistrement e : vect) {
		    if(e.getJoueur().getCle().equals(str)){
		    	System.out.println("Voici l'information sauvegardé de : " + e.getJoueur().getNomJoueur());
		    	int nbCartes = e.getLongueurListe();
		    	if(nbCartes > 1){
		    		System.out.println("Le joueur a "+nbCartes+" cartes enregistrées");
		    	}else{
		    		System.out.println("Le joueur a "+nbCartes+" carte enregistrée");
		    	}
		    	int i = 1;
		    	for(Carte c : e.getListeCarte()){
		    		System.out.println("Carte "+i+" :");
		    		System.out.println(c.toString());
		    		i++;
		    	}
		    	bool = true;
		    	enr = e;
		    }
		}
		
		if(!bool){
			System.out.println("La clé d'identification rentré n'existe pas. Merci de rééssayer.");
			affichageEnregistrement();
		}
		return enr;
	}
	
	public static void supprimerEnregistrement(){
		Enregistrement e = affichageEnregistrement();
		System.out.println("Voulez vous effacer l'information de ce joueur ? (O/N) ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		if(str.equals("O") | str.equals("o")){
			int i = -1;
			for(Enregistrement enr : vect){
				i++;
				if(enr.getJoueur().getCle().equals(e.getJoueur().getCle())){
					vect.remove(i);
				}
			}
			System.out.println("L'information du joueur "+e.getJoueur().getNomJoueur()+" a été efface du système.");
		}else if(str.equals("N") | str.equals("n") ){
			System.out.println("Suppression annulée.");
			//Retour au menu automatique
		}else{
			System.out.println("Réponse fausse. Merci de rééssayer");
			supprimerEnregistrement();
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
					for(Enregistrement e : vect){
						System.out.println(e.getJoueur().getCle());
					}
				break;
				case("2"): 
					System.out.println("Option sélectionné: 2. Afficher l'information d'un joueur");	
					affichageEnregistrement(); 
					System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
					sc.nextLine();
					break;
				case("3"):
				case("4"):
					System.out.println("Option sélectionné: 4.Effacer l'information d'un joueur");
					supprimerEnregistrement();
					System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
					sc.nextLine();
					break;
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

