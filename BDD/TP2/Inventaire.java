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

//On r�cup�re le nom du fichier � modifier ou cr�er
    	boolean bool = true;

        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer le nom du fichier à ouvrir ou à créer : ");
        String fichier = sc.nextLine();
        //On récupère les informations du fichier
        try{
            FileReader f = new FileReader(fichier);
            Scanner scanner = new Scanner(f);
            //On définit le délimiteur pour séparer les informations du fichier d'origine
            scanner.useDelimiter(";");
            int i = 1;
            //Tant qu'il y a des informations à enregistrer
            while (scanner.hasNext()) {
                //On crée un nouvel enregistrement
                Enregistrement enr = new Enregistrement();
                //On r�cup�re la cl� en enlevant les guillemets
                String stmp = scanner.next();
                enr.getJoueur().setCle(stmp.substring(i, stmp.length()-1));
                i = 2; // permet de g�rer les retours chariots dans le fichier
                //On r�cup�re le nom en enlevant les guillemets
                stmp = scanner.next();
                enr.getJoueur().setNomJoueur(stmp.substring(1, stmp.length()-1));
              //On r�cup�re le nombre de carte en enlevant les guillemets
                String s = scanner.next();
                s = s.substring(1, s.length()-1);
                int tmp = Integer.parseInt(s);
                enr.getJoueur().setNombreCartes(tmp);
                while(tmp>0){
                	//On lit chaque information de chaque carte en prenant soin d'enlever les guillemets
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
        //Si le fichier n'existe pas
        catch(FileNotFoundException e){
            if(fichier.substring(fichier.length()-4, fichier.length()).equals(".txt")){
                nomFichier = fichier;   
                //Cr�ation du fichier
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFichier)));
                }
                //Erreur li� � la cr�ation
                catch(IOException exc){

                    System.out.println("Erreur � la creation du fichier. Veuillez recommencer.");
                    remplirVecteur();
                }
            //Le nom rentr� n'a pas l'extension .txt
            }else{
                System.out.println("Merci de rentrer un fichier txt pour la création. Veuillez recommencer. ");
                remplirVecteur();
            }
        }
    }

    /**
     * Demande à l'utilisateur la clé d'identification du joueur
     * @return clé d'identification entrée par l'utilisateur
     */
    public static String demanderCle() {
        //Tant que la clé n'est pas acceptée, on la redemande à l'utilisateur
        System.out.print("Entrez la clé d'identification du joueur : ");
        Scanner sc = new Scanner(System.in);
        String cle = sc.nextLine();

        //On vérifie que l'utilisateur a entré une clé de taille comprise entre 1 et MAXCLE caractères
        if(cle.length() > Joueur.getMaxCle()) {
            System.out.println("Veuillez entrer une clé d'indentification plus petite. ");
            cle = demanderCle();
        } else if(cle.length() == 0) {
            System.out.println("Veuillez entrer une clé d'indentification plus grande. ");
            cle = demanderCle();
        }
        
        return cle;
    }
    
    /**
    * Demande à l'utilisateur le nom du joueur
    * @return nom du joueur entré par l'utilisateur
    */
    public static String demanderNom() {
        //On demande à l'utilisateur le nom du joueur tant qu'il n'a pas fait une bonne entrée
        System.out.print("Entrez le nom du joueur : ");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();

        //On vérifie que l'utilisateur a entré un nom de taille comprise entre 1 et MAXNOM caractères
        if(nom.length() > Joueur.getMaxNom()) {
            System.out.println("Veuillez entrer un nom plus petit. ");
            nom = demanderNom();
        } else if(nom.length() == 0) {
            System.out.println("Veuillez entrer un nom plus grand. ");
            nom = demanderNom();
        }
        
        return nom;
    }
    
    /**
    * Demande à l'utilisateur le nombre de cartes
    * @return nombre de cartes entrées par l'utilisateur
    */
    public static int demanderCartes() {
        Scanner sc = new Scanner(System.in);
        int nbcarte = 0;
        
        //On demande à l'utilisateur le nombres de cartes tant qu'il n'a pas fait une bonne entrée
        System.out.print("Combien de cartes ? : ");
        try {
            nbcarte = Integer.parseInt(sc.nextLine());
            //On vérifie que l'utilisateur à entré un nombre correct de cartes
            if(nbcarte > Enregistrement.getMaxCartes()) {
                System.out.println("Veuillez entrer un nombre de cartes plus petit. ");
                nbcarte = demanderCartes();
            } else if(nbcarte < 0) {
                System.out.println("Veuillez entrer un nombre de cartes plus grand. ");
                nbcarte = demanderCartes();
            }
        } catch (Exception ex) { 
            System.out.println("Veuillez entrer un entier. ");  
        }
        
        return nbcarte;
    }
    
    /**
    * Demande à l'utilisateur le titre de la carte
    * @param i le numéro de la carte
    * @return le titre de la carte entré par l'utilisateur
    */
    public static String demanderTitre(int i) {
        //On demande à l'utilisateur le titre de la carte tant qu'il n'a pas entré un format correct
        System.out.print("Entrez le titre de la carte " + i + " : ");
        Scanner sc = new Scanner(System.in);
        String titre = sc.nextLine();
        if(titre.length() > Carte.getMaxTitre()) {
            System.out.println("Veuillez entrer un titre plus petit.");
            titre = demanderTitre(i);
        } else if (titre.length() <= 0){
            System.out.println("Veuillez entrer un titre plus grand.");
            titre = demanderTitre(i);
        }
        
        return titre;
    }
    
    /**
    * Demande à l'utilisateur le nom de l'équipe de la carte
    * @param i le numéro de la carte
    * @return l'équipe de la carte entré par l'utilisateur
    */
    public static String demanderEquipe(int i) {
        //On demande à l'utilisateur le nom de l'équipe tant qu'il n'a pas entré un format correct
        System.out.print("Entrez l’équipe de la carte " + i + " : ");
        Scanner sc = new Scanner(System.in);
        String equipe = sc.nextLine();
        if(equipe.length()>Carte.getMaxNomEquipe()){
            System.out.println("Veuillez entrer un nom plus petit."); 
            equipe = demanderEquipe(i);
        } else if (equipe.length()<=0){
            System.out.println("Veuillez entrer un nom plus gra,d."); 
            equipe = demanderEquipe(i);
        }
        return equipe;
    }
    
    /**
    * Demande à l'utilisateur l'année de la carte
    * @param i le numéro de la carte
    * @return l'année de la carte entrée par l'utilisateur
    */
    public static int demanderAnnee(int i) {
        //On demande à l'utilisateur l'année tant qu'il n'a pas entré un format correct
        int annee = 0;
        try {
            System.out.print("Entrez l’année de parution de la carte " + i + " : ");
            Scanner sc = new Scanner(System.in);
            annee = Integer.parseInt(sc.nextLine());
        } catch (Exception ex) {
            System.out.println("Veuillez entrer un entier. ");  
            annee = demanderAnnee(i);
        }
        return annee;
    }
    
    /**
    * Permet d'ajouter un nouveau joueur le tableau des enregistrements
    */
    public static void ajoutJoueur(){
        boolean ok = false;
        boolean choix = true;

        String cle = demanderCle();

        //On vérifie que la clé n'est pas déjà présente
        for (Enregistrement e : vect) {
            if(e.getJoueur().getCle().equals(cle)){
                while(!ok) {
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
        //On demande et on enregistre le nom du joueur
        String nom = demanderNom(); 
        e.getJoueur().setNomJoueur(nom);

        //On enregistre le nombre de cartes
        int nbcarte = demanderCartes();
        e.getJoueur().setNombreCartes(nbcarte);
        e.reinitialiseListe();

        //On crée les cartes 
        for(int i = 1; i<nbcarte+1; i++) {
            //On demande à l'utilisateur le titre de la carte
            String titre = demanderTitre(i);

            //On demande à l'utilisateur le nom de l'équipe
            String equipe = demanderEquipe(i);

            //On demande à l'utilisateur l'année
            int annee = demanderAnnee(i);

            //On ajoute la carte crée à l'enregistrement
            e.getListeCarte().add(i-1, new Carte(titre,equipe,annee));
        }
    }
        
    /**
    *Affiche l'enregistrement correspondant à une clé d'identification d'un joueur.
    * @return l'enregistrement affiché.
    */
    public static Enregistrement affichageEnregistrement() {
        return affichage(demanderCle());
    }
     
    /**
    * Affiche l'enregistrement correspondant à une clé d'identification d'un joueur.
    * @return l'enregistrement affiché.
    */
    public static Enregistrement affichage(String str){
        boolean bool = false;
        Enregistrement enr = null;
        for (Enregistrement e : vect) {
            //On cherche la clé correspondante dans l'ensemble des enregistrements
            if(e.getJoueur().getCle().equals(str)){
                    //on affiche l'ensemble des informations du joueur
                System.out.println("Voici l'information sauvegardé de " + e.getJoueur().getNomJoueur() + " : ");
                int nbCartes =  e.getJoueur().getNombreCartes();;
                if(nbCartes > 1){
                    System.out.println("Le joueur a " + nbCartes + " cartes enregistrées.");
                }else{
                    System.out.println("Le joueur a " + nbCartes + " carte enregistrée. ");
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
        //La clé n'a pas de correspondance dans l'ensemble des enregistrements
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
    	//Demande la clé du joueur à modifier
        Enregistrement e = affichage(demanderCle());
        System.out.println("Maintenant entrez les données à modifier : ");
        //Appel de la fonction de modification
        modificationEnregistrement(e);
    }
        
    /**
    * Supprimer l'enregistrement correspondant à une clé d'identification d'un joueur
    */
    public static void supprimerEnregistrement(){
        //On demande la clé du joueur a supprimer, on affiche l'enregistrement et on récupère l'enregistrement concerné
        Enregistrement e = affichageEnregistrement();
        System.out.print("Voulez vous effacer l'information de ce joueur ? (O/N) ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //Réponse positive
        if(str.equals("O") | str.equals("o")){
            int i = -1;
            for(Enregistrement enr : vect){
                i++;
                if(enr.getJoueur().getCle().equals(e.getJoueur().getCle())){
                    //Dès que les 2 clefs correspondent, on supprime l'enregistrement
                    vect.removeElementAt(i);
                    break;
                }
            }
            System.out.println("L'information du joueur "+e.getJoueur().getNomJoueur()+" a été efface du système. ");
        //Réponse négative
        }else if(str.equals("N") | str.equals("n") ){
            System.out.println("Suppression annulée. ");
        //Autre réponse : on boucle
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
        //Affichage à l'écran
        if(str.equals("E") | str.equals("e")){
            for(Enregistrement e : vect) {
                System.out.println("Joueur : " + e.getJoueur().getNomJoueur());
                //Appel de la fonction d'affichage
                affichage(e.getJoueur().getCle());
            }
        //Creation de la liste dans un fichier
        } else if(str.equals("F") | str.equals("f")){
            while(!ok) {
                System.out.print("Quel est le nom du fichier texte que vous voulez créer ? ");
                sc = new Scanner(System.in);
                nom = sc.nextLine();
                ok = true;
                try {
                    //Le fichier doit être un fichier .txt
                    if(!nom.substring(nom.length()-4, nom.length()).equals(".txt")){
                        System.out.println("Merci de rentrer un fichier .txt pour la création. Veuillez recommencer. ");
                        ok = false;
                    }
                //Fichier inférieur à 4 lettres...
                } catch (Exception ex) {
                    System.out.println("Merci de rentrer un fichier .txt pour la création. Veuillez recommencer. ");
                    ok = false;
                }
            }
            try{
                //Création du fichier
                FileWriter fw = new FileWriter (nom);
                //Pour chaque enregistrement, on écrit les informations dans le fichier
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
        //Choix autre que E/F : on boucle
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
            //Création du fichier d'écriture
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
        //On demande le fichier à ouvrir ou à créer et on remplit le vector
        remplirVecteur();
        boolean bool = true;
        boolean ok = false;
        int choix = -1;
        //Tant qu'on ne demande pas à quitter le menu
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
        * @param args la liste des arguments
        */
	public static void main(String [] args){
            afficheMenu();
	}
}