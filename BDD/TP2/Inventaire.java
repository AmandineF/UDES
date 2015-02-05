import java.io.*;
import java.util.*;
/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 130 638
 */

import java.io.*;
import java.util.*;
public class Inventaire {
    	/**
     * Le vecteur contenant l'ensemble des enregistrements.
     */
	private static Vector<Enregistrement> vect = new Vector<Enregistrement>();
        	/**
     * Le nom du fichier dans lequel on travaille.
     */
	private static String nomFichier = null;
	
		
	/**
     * Initialise le vecteur en fonction de si on charge un fichier existant ou si on cr�e un nouveau fichier.
     */
	public static void remplirVecteur(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer le nom du fichier � ouvrir ou � cr�er : ");
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
			    	System.out.println("Erreur � la creation du fichier");
			    }
        	}else{
        		System.out.println("Merci de rentrer un fichier txt pour la cr�ation. Veuillez recommencer.");
        		remplirVecteur();
        	}
        }
	}

        /**
         * Permet d'ajouter un nouveau joueur le tableau des enregistrements
         */
        public static void ajoutJoueur(){
            boolean ok = false;
            String cle = "";

            while(!ok) {
                System.out.println("Entrez la cl� d'identification du joueur :");
                Scanner sc = new Scanner(System.in);
                cle = sc.nextLine();
                
                //On v�rifie que l'utilisateur a entr� une cl� de taille comprise entre 1 et MAXCLE caract�res
                if(cle.length() > Joueur.MAXCLE) {
                    System.out.println("Veuillez entrer une cl� d'indentification plus petite.");
                } else if(cle.length() == 0) {
                    System.out.println("Veuillez entrer une cl� d'indentification plus grande.");
                } else {
                        ok =true;
                }
            }
            ok = false;
            
            //V�rification que la cl� n'est pas d�j� pr�sente
            for (Enregistrement e : vect) {
                if(e.getJoueur().getCle().equals(cle)){
                    System.out.println("La cl� est d�j� utilis�e, voulez-vous garder l��craser avec le nouveau ?");
                    Scanner rep = new Scanner(System.in);
                    String str = rep.nextLine();
                    if(str.equals("O") | str.equals("o")){
                        vect.remove(e);
                        System.out.println("L'ancien enregistrement a �t� effac� du syst�me.");
                    }else if(str.equals("N") | str.equals("n") ){
                            System.out.println("Ajout annul�.");
                            //Retour au menu automatique
                    }else{
                            System.out.println("R�ponse fausse. Merci de r��ssayer");
                            ajoutJoueur();
                    }
                }
            }   
            Joueur joueur = new Joueur();
            joueur.setCle(cle);
            Enregistrement e = new Enregistrement();
            e.setJoueur(joueur);
            modificationEnregistrement(e);

            //On ajoute l'enregistrement � vect  
            //Reste � rajouter pour le mettre au bon endroit
            vect.add(e);
            
            System.out.println("L'enregistrement du joueur a r�ussi.");
        }
        
        /**
         * Permet de modifier un enregistrement 
         * @param e L'enregistrement � modifier
         */
        public static void modificationEnregistrement(Enregistrement e) {
            int nbcarte = 0;
            String nom = "";
            boolean ok = false;
            Scanner sc = null;
            
            while(!ok) {
                System.out.println("Entrez le nom du joueur :");
                sc = new Scanner(System.in);
                nom = sc.nextLine();
                
                //On v�rifie que l'utilisateur a entr� un nom de taille comprise entre 1 et MAXNOM caract�res
                if(nom.length() > Joueur.MAXNOM) {
                    System.out.println("Veuillez entrer un nom plus petit.");
                } else if(nom.length() == 0) {
                    System.out.println("Veuillez entrer un nom plus grand.");
                } else {
                        ok =true;
                }
            }
            ok = false;
            e.getJoueur().setNomJoueur(nom);
            
            while(!ok) {
                System.out.println("Combien de cartes? :");
                nbcarte = Integer.parseInt(sc.nextLine());
                //On v�rifie que l'utilisateur � entr� un nombre correct de cartes
                if(nbcarte > Enregistrement.MAXCARTES) {
                    System.out.println("Veuillez entrer un nombre de cartes plus petit.");
                } else if(nom.length() == 0) {
                    System.out.println("Veuillez entrer un nombre de cartes plus grand.");
                } else {
                    ok =true;
                }
            }
            e.getJoueur().setNombreCartes(nbcarte);
            e.reinitialiseListe();
            
            for(int i = 1; i<nbcarte+1; i++) {
                System.out.println("Entrez le titre de la carte "+i+" :");
                sc = new Scanner(System.in);
                String titre = sc.nextLine();
                System.out.println("Entrez l��quipe de la carte 1 "+i+" :");
                sc = new Scanner(System.in);
                String equipe = sc.nextLine();
                System.out.println("Entrez l�ann�e de parution de la carte "+i+" :");
                sc = new Scanner(System.in);
                int annee = Integer.parseInt(sc.nextLine());
                //On ajoute la carte cr�e � l'enregistrement
                e.getListeCarte().add(i-1, new Carte(titre,equipe,annee));
            }
        }
        
     /**
     *Affiche l'enregistrement correspondant � une cl� d'identification d'un joueur.
     * 
     * @return l'enregistrement affich�.
     */
        public static Enregistrement affichageEnregistrement(){
		System.out.println("Entrez la cl� d'identification du joueur: ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
                return affichage(str);
	}
     
        /**
     * Affiche l'enregistrement correspondant � une cl� d'identification d'un joueur.
     * 
     * @return l'enregistrement affich�.
     */
	public static Enregistrement affichage(String str){
		boolean bool = false;
		Enregistrement enr = null;
		for (Enregistrement e : vect) {
		    if(e.getJoueur().getCle().equals(str)){
		    	System.out.println("Voici l'information sauvegard� de : " + e.getJoueur().getNomJoueur());
		    	int nbCartes = e.getLongueurListe();
		    	if(nbCartes > 1){
		    		System.out.println("Le joueur a "+nbCartes+" cartes enregistr�es");
		    	}else{
		    		System.out.println("Le joueur a "+nbCartes+" carte enregistr�e");
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
			System.out.println("La cl� d'identification rentr� n'existe pas. Merci de r��ssayer.");
			affichageEnregistrement();
		}
		return enr;
	}
        
        /**
         * Permet de mettre � jour un enregistrement
         */
        public static void MAJenr() {           
            System.out.println("Entrez la cl� d'identification du joueur : ");
	    Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            Enregistrement e = affichage(str);
            System.out.println("Maintenant entrez les donn�es � modifier : ");
            modificationEnregistrement(e);
        }
        
        	/**
     * Supprimer l'enregistrement correspondant � une cl� d'identification d'un joueur
     */
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
			System.out.println("L'information du joueur "+e.getJoueur().getNomJoueur()+" a �t� efface du syst�me.");
		}else if(str.equals("N") | str.equals("n") ){
			System.out.println("Suppression annul�e.");
			//Retour au menu automatique
		}else{
			System.out.println("R�ponse fausse. Merci de r��ssayer");
			supprimerEnregistrement();
		}
	}
        
      /**
       * Permet l'affichage de tous les joueurs sous forme de liste � l'�cran ou dans un fichier texte
       */
        public static void rapport() {
            System.out.println("Voulez-vous creer la liste des joueurs dans un fichier ou l'afficher sur l'ecran ? (F/E) :");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if(str.equals("E") | str.equals("E")){
                for(Enregistrement e : vect) {
                    System.out.println("Joueur : " + e.getJoueur().getNomJoueur());
                    affichage(e.getJoueur().getCle());
                }
            } else if(str.equals("F") | str.equals("f")){
                System.out.println("Quel est le nom du fichier texte que vous voulez cr�er ?");
                sc = new Scanner(System.in);
                String nom = sc.nextLine();
                
                //� FINIR
            } else {
                System.out.println("R�ponse fausse. Merci de r��ssayer");
                rapport();
            }
        }
	
		/**
     * Permet le chargement ou la cr�ation d'un fichier, puis affiche le menu avec les diff�rentes options possibles.
     */
	public static void afficheMenu(){
		remplirVecteur();
		boolean bool = true;
		while(bool){
			System.out.println("Application de gestion de cartes de baseball\n");
			System.out.println("Voici la liste d'op�rations valides :");
			System.out.println("1. Ajouter un joueur\n2. Afficher l'information d'un joueur\n3. Mise � jour de l'information d'un joueur\n4. Effacer l'information d'un joueur\n5. Liste des joueurs\n6. Sauvegarde\n0. Sortir");
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer votre choix : ");
			String str = sc.nextLine();
			switch(str){
				case("1"):
                                    	System.out.println("Option s�lectionn�: 1. Ajouter un joueur");	
					ajoutJoueur(); 
					System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
					sc.nextLine();
                                        break;
				case("2"): 
					System.out.println("Option s�lectionn�: 2. Afficher l'information d'un joueur");	
					affichageEnregistrement(); 
					System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
					sc.nextLine();
					break;
				case("3"):
                                        System.out.println("Option s�lectionn�: 3. Mise � jour de l'information d'un joueur");	
					MAJenr(); 
					System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
					sc.nextLine();
					break;
				case("4"):
					System.out.println("Option s�lectionn�: 4.Effacer l'information d'un joueur");
					supprimerEnregistrement();
					System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
					sc.nextLine();
					break;
				case("5"):
                                        System.out.println("Option s�lectionn�: 5. Liste de joueurs");
					rapport();
					System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
					sc.nextLine();
					break;
				case("6"): 
                                        System.out.println("Option s�lectionn�: 6. Sauvegarder");
                                        System.out.println("Resultat : " + str); 
                                        System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                        sc.nextLine();
                                        break;
				case("0"): 
                                        System.out.println("Option s�lectionn�: 0. Sortir");
                                        bool = false; 
                                        break;
                                default: System.out.println("Merci de renseigner un des chiffres du menu !"); 
				System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
				sc.nextLine();
				break;
			}
		}
	}
        
        	/**
     * La fonction main permettant de lancer le programme.
     */
	public static void main(String [] args){
		afficheMenu();
	}
	
}
