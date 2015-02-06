package bdd2;

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
     * Initialise le vecteur en fonction de si on charge un fichier existant ou si on crée un nouveau fichier.
    */
    public static void remplirVecteur() {
        //On récupère le nom du fichier à modifier ou créer
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer le nom du fichier à ouvrir ou à créer : ");
        String fichier = sc.nextLine();
        //On récupère les informations du fichier
        try{
            FileReader f = new FileReader(fichier);
            Scanner scanner = new Scanner(f);
            //On définit le délimiter pour séparer les informations du fichier d'origine
            scanner.useDelimiter(";");
            int i = 1;
            //Tant qu'il y a des informations à enregistrer
            while (scanner.hasNext()) {
                //On crée un nouvel enregistrement
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
                    System.out.println("Erreur à la creation du fichier. ");
                }
            }else{
                System.out.println("Merci de rentrer un fichier txt pour la création. Veuillez recommencer. ");
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
            
            //Tant que la clé n'est pas acceptée, on la redemande à l'utilisateur
            while(!ok) {
                System.out.print("Entrez la clé d'identification du joueur : ");
                Scanner sc = new Scanner(System.in);
                cle = sc.nextLine();
                
                //On vérifie que l'utilisateur a entré une clé de taille comprise entre 1 et MAXCLE caractères
                if(cle.length() > Joueur.getMaxCle()) {
                    System.out.println("Veuillez entrer une clé d'indentification plus petite. ");
                } else if(cle.length() == 0) {
                    System.out.println("Veuillez entrer une clé d'indentification plus grande. ");
                } else {
                        ok =true;
                }
            }
            ok = false;
            
            while(!ok) {
                //On vérifie que la clé n'est pas déjà présente
                for (Enregistrement e : vect) {
                    if(e.getJoueur().getCle().equals(cle)){
                        //Si la clé est présente, on demande à l'utilisateur ce qu'il veut faire 
                        System.out.print("La clé est déjà utilisée, voulez-vous écraser l'ancien enregistrement ? ");
                        Scanner rep = new Scanner(System.in);
                        String str = rep.nextLine();
                        //Si il souhaite effacer l'enregistrement, on l'efface
                        if(str.equals("O") | str.equals("o")){
                            vect.remove(e);
                            System.out.println("L'ancien enregistrement a été effacé du système. ");
                            ok = true;
                        //Sinon on annule l'ajout d'un nouveau joueur
                        }else if(str.equals("N") | str.equals("n") ){
                                System.out.println("Ajout annulé.");
                                choix = false;
                                ok = true;
                                break;
                        //Si le joueur entre une mauvaise réponde on lui demande de recommencer.
                        }else{
                            System.out.println("Réponse fausse. Merci de rééssayer ! ");
                        }
                    }
                }
            }
            ok = false;
            
            //Si le nouvel ajout peut être effectué
            if(choix){ 
                //On crée le joueur
                Joueur joueur = new Joueur();
                joueur.setCle(cle);
                Enregistrement e = new Enregistrement();
                e.setJoueur(joueur);
                modificationEnregistrement(e);

                //On ajoute l'enregistrement à vect au bon endroit
                for(int i = 0; i<vect.size() - 1; i++) {
                    //Cas ou l'élément doit être placé au milieu du vecteur
                    if((vect.elementAt(i).getJoueur().getCle().compareTo(cle) > 0) && (vect.elementAt(i + 1).getJoueur().getCle().compareTo(cle) < 0)) {     
                        vect.add(i + 1, e);
                        ok = true;
                        break;  
                    } else if (vect.elementAt(i).getJoueur().getCle().compareTo(cle) < 0) {
                        //Cas ou l'élément doit être placé en première position
                        vect.add(0, e);
                        ok = true;
                        break;
                    }
                }

                if(!ok) {
                    //Cas ou l'élément doit être placé en dernière position
                    vect.add(e);
                }
                    
                //On informe l'utilisateur que l'enregistrement s'est bien déroulé
                System.out.println("L'enregistrement du joueur a réussi.\n ");
            }
        }
        
        /**
         * Permet de modifier un enregistrement 
         * @param e L'enregistrement à modifier
        */
        public static void modificationEnregistrement(Enregistrement e) {
            int nbcarte = 0;
            int annee = 0;
            String nom = "";
            String titre = "";
            String equipe = "";
            boolean ok = false;
            Scanner sc = null;
            
            //On demande à l'utilisateur le nom du joueur tant qu'il n'a pas fait une bonne entrée
            while(!ok) {
                System.out.print("Entrez le nom du joueur : ");
                sc = new Scanner(System.in);
                nom = sc.nextLine();
                
                //On vérifie que l'utilisateur a entré un nom de taille comprise entre 1 et MAXNOM caractères
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
            
            //On demande à l'utilisateur le nombres de cartes tant qu'il n'a pas fait une bonne entrée
            while(!ok) {
                System.out.print("Combien de cartes ? : ");
                try {
                    nbcarte = Integer.parseInt(sc.nextLine());
                    //On vérifie que l'utilisateur à entré un nombre correct de cartes
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
            
            //On crée les cartes 
            for(int i = 1; i<nbcarte+1; i++) {
                //On demande à l'utilisateur le titre de la carte tant qu'il n'a pas entré un format correct
                while(!ok) {
                    System.out.print("Entrez le titre de la carte " + i + " : ");
                    sc = new Scanner(System.in);
                    titre = sc.nextLine();
                    if(titre.length() < Carte.getMaxTitre() && titre.length() > 0) {
                        ok = true;
                    }
                }
                ok = false;
                
                //On demande à l'utilisateur le nom de l'équipe tant qu'il n'a pas entré un format correct
                while(!ok) {
                    System.out.print("Entrez l’équipe de la carte " + i + " : ");
                    sc = new Scanner(System.in);
                    equipe = sc.nextLine();
                }
                ok = false;
                
                //On demande à l'utilisateur l'année tant qu'il n'a pas entré un format correct
                while(!ok) {
                    try {
                        System.out.print("Entrez l’année de parution de la carte " + i + " : ");
                        sc = new Scanner(System.in);
                        annee = Integer.parseInt(sc.nextLine());
                        ok = true;
                    } catch (Exception ex) {
                        System.out.println("Veuillez entrer un entier. ");  
                    }
                }
                ok = false;
                //On ajoute la carte crée à l'enregistrement
                e.getListeCarte().add(i-1, new Carte(titre,equipe,annee));
            }
        }
        
        /**
         *Affiche l'enregistrement correspondant à une clé d'identification d'un joueur.
         * 
         * @return l'enregistrement affiché.
        */
        public static Enregistrement affichageEnregistrement(){
            System.out.print("Entrez la clé d'identification du joueur : ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            return affichage(str);
	}
     
        /**
         * Affiche l'enregistrement correspondant à une clé d'identification d'un joueur.
         * 
         * @return l'enregistrement affiché.
        */
	public static Enregistrement affichage(String str){
            boolean bool = false;
            Enregistrement enr = null;
            for (Enregistrement e : vect) {
                if(e.getJoueur().getCle().equals(str)){
                    System.out.println("Voici l'information sauvegardé de " + e.getJoueur().getNomJoueur() + " : ");
                    int nbCartes =  e.getJoueur().getNombreCartes();;
                    if(nbCartes > 1){
                            System.out.println("Le joueur a " + nbCartes + " cartes enregistrées.");
                    }else{
                            System.out.println("Le joueur a " + nbCartes + " carte enregistrée. ");
                    }
                    int i = 1;
                    for(Carte c : e.getListeCarte()){
                            System.out.println("Carte "+i+" : ");
                            System.out.println(c.toString());
                            i++;
                    }
                    bool = true;
                    enr = e;
                }
            }

            if(!bool){
                    System.out.println("La clé d'identification rentré n'existe pas. Merci de rééssayer. ");
                    affichageEnregistrement();
            }
            return enr;
	}
        
        /**
         * Permet de mettre à jour un enregistrement
         */
        public static void MAJenr() {           
            System.out.print("Entrez la clé d'identification du joueur : ");
	    Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            Enregistrement e = affichage(str);
            System.out.println("Maintenant entrez les données à modifier : ");
            modificationEnregistrement(e);
        }
        
        /**
         * Supprimer l'enregistrement correspondant à une clé d'identification d'un joueur
        */
	public static void supprimerEnregistrement(){
            Enregistrement e = affichageEnregistrement();
            System.out.print("Voulez vous effacer l'information de ce joueur ? (O/N) ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if(str.equals("O") | str.equals("o")){
                    int i = -1;
                    for(Enregistrement enr : vect){
                            i++;
                            if(enr.getJoueur().getCle().equals(e.getJoueur().getCle())){
                                    //vect.remove(i);
                                vect.removeElementAt(i);
                                break;
                            }
                    }
                    System.out.println("L'information du joueur "+e.getJoueur().getNomJoueur()+" a été efface du système. ");
            }else if(str.equals("N") | str.equals("n") ){
                    System.out.println("Suppression annulée. ");
                    //Retour au menu automatique
            }else{
                    System.out.println("Réponse fausse. Merci de rééssayer ! ");
                    supprimerEnregistrement();
            }
	}
        
       /**
        * Permet l'affichage de tous les joueurs sous forme de liste à l'écran ou dans un fichier texte
       */
        public static void rapport() {
            boolean ok = false;
            String nom = "";
            System.out.print("Voulez-vous creer la liste des joueurs dans un fichier ou l'afficher sur l'ecran ? (F/E) : ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if(str.equals("E") | str.equals("e")){
                for(Enregistrement e : vect) {
                    System.out.println("Joueur : " + e.getJoueur().getNomJoueur());
                    affichage(e.getJoueur().getCle());
                }
            } else if(str.equals("F") | str.equals("f")){
                while(!ok) {
                    System.out.print("Quel est le nom du fichier texte que vous voulez créer ? ");
                    sc = new Scanner(System.in);
                    nom = sc.nextLine();
                    ok = true;
                    try {
                        if(!nom.substring(nom.length()-4, nom.length()).equals(".txt")){
                            System.out.println("Merci de rentrer un fichier .txt pour la création. Veuillez recommencer. ");
                            ok = false;
                        }
                    } catch (Exception ex) {
                        System.out.println("Merci de rentrer un fichier .txt pour la création. Veuillez recommencer. ");
                        ok = false;
                    }
                }
                try{
                    FileWriter fw = new FileWriter (nom);
                    for(Enregistrement enr : vect){
                        fw.write("Clé : "+enr.getJoueur().getCle()+"\n"); // Ajout de la cle
                        fw.write("Joueur : "+enr.getJoueur().getNomJoueur()+"\n"); // Ajout du nom du joueur
                        int nbCartes = enr.getJoueur().getNombreCartes();
                        fw.write("Nombre de cartes : "+nbCartes+"\n"); // Ajout du nombre de carte
                        int i = 0;
                        for(Carte c : enr.getListeCarte()){
                             i++;
                             fw.write("Carte "+i+" :\n");
                             fw.write("Titre : "+c.getTitreCarte()+"\n"); // Ajout du titre de la carte
                             fw.write("Nom de l'équipe : "+c.getNomEquipe()+"\n"); // Ajout du nom de l'equipe
                             fw.write("Année de sortie : "+c.getAnneeSortie()+"\n"); // Ajout de l'annee de parution
                        }
                        fw.write("--------------------------------\n"); // Saut de ligne
                    }
                    fw.close();
                    System.out.println("Le fichier "+nom+" a été créé avec succès.");  
            }
            catch(IOException e){
                    System.out.println("Erreur lors de l'ouverture du fichier");
            }
            } else {
                System.out.println("Réponse fausse. Merci de rééssayer ! ");
                rapport();
            }
        }
        
        /**
         * Permet de sauvergarder le fichier texte
        */
        public static void sauvegarder(){
            try{
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
                System.out.println("Le fichier "+nomFichier+" a été créé avec succès.");
            }
            catch(IOException e){
                System.out.println("Erreur lors de l'ouverture du fichier");
            }
            
        }
	
        /**
         * Permet le chargement ou la création d'un fichier, puis affiche le menu avec les différentes options possibles.
        */
	public static void afficheMenu(){
            remplirVecteur();
            boolean bool = true;
            boolean ok = false;
            int choix = -1;
            while(bool){
                    System.out.println("--------------------------------------------------");
                    System.out.println("Application de gestion de cartes de baseball\n");
                    System.out.println("Voici la liste d'opérations valides : ");
                    System.out.println("1. Ajouter un joueur\n2. Afficher l'information d'un joueur\n3. Mise à jour de l'information d'un joueur\n4. Effacer l'information d'un joueur\n5. Liste des joueurs\n6. Sauvegarde\n0. Sortir");
                    Scanner sc = new Scanner(System.in);
                    //On vérifie que l'utilisateur rentre un choix correct
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
                                    System.out.println("Option sélectionnée : 1. Ajouter un joueur");	
                                    ajoutJoueur(); 
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(2): 
                                    System.out.println("Option sélectionnée : 2. Afficher l'information d'un joueur");	
                                    affichageEnregistrement(); 
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(3):
                                    System.out.println("Option sélectionnée : 3. Mise à jour de l'information d'un joueur");	
                                    MAJenr(); 
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(4):
                                    System.out.println("Option sélectionnée : 4. Effacer l'information d'un joueur");
                                    supprimerEnregistrement();
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(5):
                                    System.out.println("Option sélectionnée : 5. Liste de joueurs");
                                    rapport();
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(6): 
                                    System.out.println("Option sélectionnée : 6. Sauvegarder");
                                    sauvegarder();
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
                                    sc.nextLine();
                                    break;
                            case(0): 
                                    System.out.println("Option sélectionnée : 0. Sortir");
                                    sauvegarder();
                                    System.out.println("Merci d'avoir utilisé le système de gestion d'inventaire de carte.");
                                    bool = false; 
                                    break;
                            default: 
                                    System.out.println("Appuyer sur Entrée pour revenir au menu principal...");
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

