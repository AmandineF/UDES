import java.io.*;
import java.util.*;

/**
 * @author CHASSING frank 14 153 710 - FOUILLET Amandine 14 130 638
 */
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
    public static void remplirVecteur() {
        //On r�cup�re le nom du fichier � modifier ou cr�er
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer le nom du fichier � ouvrir ou � cr�er : ");
        String fichier = sc.nextLine();
        //On r�cup�re les informations du fichier
        try{
            FileReader f = new FileReader(fichier);
            Scanner scanner = new Scanner(f);
            //On d�finit le d�limiteur pour s�parer les informations du fichier d'origine
            scanner.useDelimiter(";");
            int i = 1;
            //Tant qu'il y a des informations � enregistrer
            while (scanner.hasNext()) {
                //On cr�e un nouvel enregistrement
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
                    System.out.println("Erreur � la creation du fichier. ");
                }
            }else{
                System.out.println("Merci de rentrer un fichier txt pour la cr�ation. Veuillez recommencer. ");
                remplirVecteur();
            }
        }
    }

        /**
         * Permet d'ajouter un nouveau joueur le tableau des enregistrements
        */
        public static void ajoutJoueur(){
            boolean ok = false;
            boolean choix = true;
            String cle = "";
            
            //Tant que la cl� n'est pas accept�e, on la redemande � l'utilisateur
            while(!ok) {
                System.out.print("Entrez la cl� d'identification du joueur : ");
                Scanner sc = new Scanner(System.in);
                cle = sc.nextLine();
                
                //On v�rifie que l'utilisateur a entr� une cl� de taille comprise entre 1 et MAXCLE caract�res
                if(cle.length() > Joueur.getMaxCle()) {
                    System.out.println("Veuillez entrer une cl� d'indentification plus petite. ");
                } else if(cle.length() == 0) {
                    System.out.println("Veuillez entrer une cl� d'indentification plus grande. ");
                } else {
                    ok=true;
                }
            }
            ok = false;
            
            
                //On v�rifie que la cl� n'est pas d�j� pr�sente
                for (Enregistrement e : vect) {
                    if(e.getJoueur().getCle().equals(cle)){
                    	while(!ok) {
                        //Si la cl� est pr�sente, on demande � l'utilisateur ce qu'il veut faire 
                        System.out.print("La cl� est d�j� utilis�e, voulez-vous �craser l'ancien enregistrement ? ");
                        Scanner rep = new Scanner(System.in);
                        String str = rep.nextLine();
                        //Si il souhaite effacer l'enregistrement, on l'efface
                        if(str.equals("O") | str.equals("o")){
                            vect.remove(e);
                            System.out.println("L'ancien enregistrement a �t� effac� du syst�me. ");
                            ok = true;
                        //Sinon on annule l'ajout d'un nouveau joueur
                        }else if(str.equals("N") | str.equals("n") ){
                                System.out.println("Ajout annul�.");
                                choix = false;
                                ok = true;
                                break;
                        //Si le joueur entre une mauvaise r�ponde on lui demande de recommencer.
                        }else{
                            System.out.println("R�ponse fausse. Merci de r��ssayer ! ");
                        }
                    }
                }
            }
            ok = false;
            
            //Si le nouvel ajout peut �tre effectu�
            if(choix){ 
                //On cr�e le joueur
                Joueur joueur = new Joueur();
                joueur.setCle(cle);
                Enregistrement e = new Enregistrement();
                e.setJoueur(joueur);
                modificationEnregistrement(e);

                //On ajoute l'enregistrement � vect au bon endroit
                for(int i = 0; i<vect.size() - 1; i++) {
                    //Cas ou l'�l�ment doit �tre plac� au milieu du vecteur
                    if((vect.elementAt(i).getJoueur().getCle().compareTo(cle) > 0) && (vect.elementAt(i + 1).getJoueur().getCle().compareTo(cle) < 0)) {     
                        vect.add(i + 1, e);
                        ok = true;
                        break;  
                    } else if (vect.elementAt(i).getJoueur().getCle().compareTo(cle) < 0) {
                        //Cas ou l'�l�ment doit �tre plac� en premi�re position
                        vect.add(0, e);
                        ok = true;
                        break;
                    }
                }

                if(!ok) {
                    //Cas ou l'�l�ment doit �tre plac� en derni�re position
                    vect.add(e);
                }
                    
                //On informe l'utilisateur que l'enregistrement s'est bien d�roul�
                System.out.println("L'enregistrement du joueur a r�ussi.\n ");
            }
        }
        
        /**
         * Permet de modifier un enregistrement 
         * @param e L'enregistrement � modifier
        */
        public static void modificationEnregistrement(Enregistrement e) {
            int nbcarte = 0;
            int annee = 0;
            String nom = "";
            String titre = "";
            String equipe = "";
            boolean ok = false;
            Scanner sc = null;
            
            //On demande � l'utilisateur le nom du joueur tant qu'il n'a pas fait une bonne entr�e
            while(!ok) {
                System.out.print("Entrez le nom du joueur : ");
                sc = new Scanner(System.in);
                nom = sc.nextLine();
                
                //On v�rifie que l'utilisateur a entr� un nom de taille comprise entre 1 et MAXNOM caract�res
                if(nom.length() > Joueur.getMaxNom()) {
                    System.out.println("Veuillez entrer un nom plus petit. ");
                } else if(nom.length() == 0) {
                    System.out.println("Veuillez entrer un nom plus grand. ");
                } else {
                        ok =true;
                }
            }
            ok = false;
            //On enregistre le nom du joueur
            e.getJoueur().setNomJoueur(nom);
            
            //On demande � l'utilisateur le nombres de cartes tant qu'il n'a pas fait une bonne entr�e
            while(!ok) {
                System.out.print("Combien de cartes ? : ");
                try {
                    nbcarte = Integer.parseInt(sc.nextLine());
                    //On v�rifie que l'utilisateur � entr� un nombre correct de cartes
                    if(nbcarte > Enregistrement.getMaxCartes()) {
                        System.out.println("Veuillez entrer un nombre de cartes plus petit. ");
                    } else if(nbcarte < 0) {
                        System.out.println("Veuillez entrer un nombre de cartes plus grand. ");
                    } else {
                        ok =true;
                    }
                } catch (Exception ex) { 
                    System.out.println("Veuillez entrer un entier. ");  
                }
            }
            ok = false;
            //On enregistre le nombre de cartes
            e.getJoueur().setNombreCartes(nbcarte);
            e.reinitialiseListe();
            
            //On cr�e les cartes 
            for(int i = 1; i<nbcarte+1; i++) {
                //On demande � l'utilisateur le titre de la carte tant qu'il n'a pas entr� un format correct
                while(!ok) {
                    System.out.print("Entrez le titre de la carte " + i + " : ");
                    sc = new Scanner(System.in);
                    titre = sc.nextLine();
                    if(titre.length() < Carte.getMaxTitre() && titre.length() > 0) {
                        ok = true;
                    }
                }
                ok = false;
                
                //On demande � l'utilisateur le nom de l'�quipe tant qu'il n'a pas entr� un format correct
                while(!ok) {
                    System.out.print("Entrez l��quipe de la carte " + i + " : ");
                    sc = new Scanner(System.in);
                    equipe = sc.nextLine();
                    if(equipe.length()>0 && equipe.length()<Carte.getMaxNomEquipe()){
                    	ok=true;
                    }
                }
                ok = false;
                
                //On demande � l'utilisateur l'ann�e tant qu'il n'a pas entr� un format correct
                while(!ok) {
                    try {
                        System.out.print("Entrez l�ann�e de parution de la carte " + i + " : ");
                        sc = new Scanner(System.in);
                        annee = Integer.parseInt(sc.nextLine());
                        ok = true;
                    } catch (Exception ex) {
                        System.out.println("Veuillez entrer un entier. ");  
                    }
                }
                ok = false;
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
            System.out.print("Entrez la cl� d'identification du joueur : ");
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
            	//On cherche la cl� correspondante dans l'ensemble des enregistrements
                if(e.getJoueur().getCle().equals(str)){
                	//on affiche l'ensemble des informations du joueur
                    System.out.println("Voici l'information sauvegard� de " + e.getJoueur().getNomJoueur() + " : ");
                    int nbCartes =  e.getJoueur().getNombreCartes();;
                    if(nbCartes > 1){
                            System.out.println("Le joueur a " + nbCartes + " cartes enregistr�es.");
                    }else{
                            System.out.println("Le joueur a " + nbCartes + " carte enregistr�e. ");
                    }
                    int i = 1;
                    //Pour chaque carte on affiche ses informations
                    for(Carte c : e.getListeCarte()){
                            System.out.println("Carte "+i+" : ");
                            System.out.println(c.toString());
                            i++;
                    }
                    bool = true;
                    enr = e;
                }
            }
            //La cl� n'a pas de correspondance dans l'ensemble des enregistrements
            if(!bool){
                    System.out.println("La cl� d'identification rentr� n'existe pas. Merci de r��ssayer. ");
                    affichageEnregistrement();
            }
            return enr;
	}
        
        /**
         * Permet de mettre � jour un enregistrement
         */
    public static void MAJenr() {  
    	//Demande la cl� du joueur � modifier
        System.out.print("Entrez la cl� d'identification du joueur : ");
	    Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Enregistrement e = affichage(str);
        System.out.println("Maintenant entrez les donn�es � modifier : ");
        //Appel de la fonction de modification
        modificationEnregistrement(e);
    }
        
        /**
         * Supprimer l'enregistrement correspondant � une cl� d'identification d'un joueur
        */
	public static void supprimerEnregistrement(){
			//On demande la cl� du joueur a supprimer, on affiche l'enregistrement et on r�cup�re l'enregistrement concern�
            Enregistrement e = affichageEnregistrement();
            System.out.print("Voulez vous effacer l'information de ce joueur ? (O/N) ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            //R�ponse positive
            if(str.equals("O") | str.equals("o")){
                    int i = -1;
                    for(Enregistrement enr : vect){
                            i++;
                            if(enr.getJoueur().getCle().equals(e.getJoueur().getCle())){
                                //D�s que les 2 clefs correspondent, on supprime l'enregistrement
                                vect.removeElementAt(i);
                                break;
                            }
                    }
                    System.out.println("L'information du joueur "+e.getJoueur().getNomJoueur()+" a �t� efface du syst�me. ");
            //R�ponse n�gative
            }else if(str.equals("N") | str.equals("n") ){
                    System.out.println("Suppression annul�e. ");
                    //Retour au menu automatique
            //Autre r�ponse : on boucle
            }else{
                    System.out.println("R�ponse fausse. Merci de r��ssayer ! ");
                    supprimerEnregistrement();
            }
	}
        
       /**
        * Permet l'affichage de tous les joueurs sous forme de liste � l'�cran ou dans un fichier texte
       */
        public static void rapport() {
            boolean ok = false;
            String nom = "";
            System.out.print("Voulez-vous creer la liste des joueurs dans un fichier ou l'afficher sur l'ecran ? (F/E) : ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            //Affichage � l'�cran
            if(str.equals("E") | str.equals("e")){
                for(Enregistrement e : vect) {
                    System.out.println("Joueur : " + e.getJoueur().getNomJoueur());
                    //Appel de la fonction d'affichage
                    affichage(e.getJoueur().getCle());
                }
            //Creation de la liste dans un fichier
            } else if(str.equals("F") | str.equals("f")){
                while(!ok) {
                    System.out.print("Quel est le nom du fichier texte que vous voulez cr�er ? ");
                    sc = new Scanner(System.in);
                    nom = sc.nextLine();
                    ok = true;
                    try {
                    	//Le fichier doit �tre un fichier .txt
                        if(!nom.substring(nom.length()-4, nom.length()).equals(".txt")){
                            System.out.println("Merci de rentrer un fichier .txt pour la cr�ation. Veuillez recommencer. ");
                            ok = false;
                        }
                    //Fichier inf�rieur � 4 lettres...
                    } catch (Exception ex) {
                        System.out.println("Merci de rentrer un fichier .txt pour la cr�ation. Veuillez recommencer. ");
                        ok = false;
                    }
                }
                try{
                	//Cr�ation du fichier
                    FileWriter fw = new FileWriter (nom);
                    //Pour chaque enregistrement, on �crit les informations dans le fichier
                    for(Enregistrement enr : vect){
                        fw.write("Cl� : "+enr.getJoueur().getCle()+"\n"); // Ajout de la cle
                        fw.write("Joueur : "+enr.getJoueur().getNomJoueur()+"\n"); // Ajout du nom du joueur
                        int nbCartes = enr.getJoueur().getNombreCartes();
                        fw.write("Nombre de cartes : "+nbCartes+"\n"); // Ajout du nombre de carte
                        int i = 0;
                        for(Carte c : enr.getListeCarte()){
                             i++;
                             fw.write("Carte "+i+" :\n");
                             fw.write("Titre : "+c.getTitreCarte()+"\n"); // Ajout du titre de la carte
                             fw.write("Nom de l'�quipe : "+c.getNomEquipe()+"\n"); // Ajout du nom de l'equipe
                             fw.write("Ann�e de sortie : "+c.getAnneeSortie()+"\n"); // Ajout de l'annee de parution
                        }
                        fw.write("--------------------------------\n"); // Saut de ligne
                    }
                    fw.close();
                    System.out.println("Le fichier "+nom+" a �t� cr�� avec succ�s.");  
            }
            catch(IOException e){
                    System.out.println("Erreur lors de l'ouverture du fichier");
            }
            //Choix autre que E/F : on boucle
            } else {
                System.out.println("R�ponse fausse. Merci de r��ssayer ! ");
                rapport();
            }
        }
        
        /**
         * Permet de sauvergarder le fichier texte
        */
        public static void sauvegarder(){
            try{
            	//Cr�ation du fichier d'�criture
                FileWriter fw = new FileWriter (nomFichier);
                int i = 0;
                for(Enregistrement enr : vect){
                    fw.write("\""+enr.getJoueur().getCle()+"\";"); // Ajout de la cle
                    fw.write("\""+enr.getJoueur().getNomJoueur()+"\";"); // Ajout du nom du joueur
                    int nbCartes = enr.getJoueur().getNombreCartes();
                    fw.write("\""+nbCartes+"\";"); // Ajout du nombre de carte
                    for(Carte c : enr.getListeCarte()){
                         fw.write("\""+c.getTitreCarte()+"\";"); // Ajout du titre de la carte
                         fw.write("\""+c.getNomEquipe()+"\";"); // Ajout du nom de l'equipe
                         fw.write("\""+c.getAnneeSortie()+"\";"); // Ajout de l'annee de parution
                    }
                    i++;
                    if(i<vect.size()){
                            fw.write("\n"); // Saut de ligne
                    }
                }
                fw.close();
                System.out.println("Le fichier "+nomFichier+" a �t� cr�� avec succ�s.");
            }
            catch(IOException e){
                System.out.println("Erreur lors de l'ouverture du fichier");
            }
            
        }
	
        /**
         * Permet le chargement ou la cr�ation d'un fichier, puis affiche le menu avec les diff�rentes options possibles.
        */
	public static void afficheMenu(){
			//On demande le fichier � ouvrir ou � cr�er et on remplit le vector
            remplirVecteur();
            boolean bool = true;
            boolean ok = false;
            int choix = -1;
            //Tant qu'on ne demande pas � quitter le menu
            while(bool){
                    System.out.println("--------------------------------------------------");
                    System.out.println("Application de gestion de cartes de baseball\n");
                    System.out.println("Voici la liste d'op�rations valides : ");
                    System.out.println("1. Ajouter un joueur\n2. Afficher l'information d'un joueur\n3. Mise � jour de l'information d'un joueur\n4. Effacer l'information d'un joueur\n5. Liste des joueurs\n6. Sauvegarde\n0. Sortir");
                    Scanner sc = new Scanner(System.in);
                    //On v�rifie que l'utilisateur rentre un choix correct
                    while(!ok) {
                        System.out.print("Entrer votre choix : ");
                        try {
                            choix = Integer.parseInt(sc.nextLine());
                            if (choix > -1 && choix < 7) {
                                ok = true;
                            } else {
                                System.out.println("Merci de renseigner un des chiffres du menu !");
                            }
                        }catch (Exception ex) {
                            System.out.println("Merci de renseigner un des chiffres du menu !"); 
                        } 
                    }
                    ok = false;
                    switch(choix){
                            case(1):
                                    System.out.println("Option s�lectionn�e : 1. Ajouter un joueur");	
                                    ajoutJoueur(); 
                                    System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(2): 
                                    System.out.println("Option s�lectionn�e : 2. Afficher l'information d'un joueur");	
                                    affichageEnregistrement(); 
                                    System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(3):
                                    System.out.println("Option s�lectionn�e : 3. Mise � jour de l'information d'un joueur");	
                                    MAJenr(); 
                                    System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(4):
                                    System.out.println("Option s�lectionn�e : 4. Effacer l'information d'un joueur");
                                    supprimerEnregistrement();
                                    System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(5):
                                    System.out.println("Option s�lectionn�e : 5. Liste de joueurs");
                                    rapport();
                                    System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(6): 
                                    System.out.println("Option s�lectionn�e : 6. Sauvegarder");
                                    sauvegarder();
                                    System.out.println("Appuyer sur Entr�e pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(0): 
                                    System.out.println("Option s�lectionn�e : 0. Sortir");
                                    sauvegarder();
                                    System.out.println("Merci d'avoir utilis� le syst�me de gestion d'inventaire de carte.");
                                    bool = false; 
                                    break;
                            default: 
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

