package ligueBaseballServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methode qui verifie que le fichier XML a importer est valide
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class ValidationXML {
    public boolean validerXML(String fichier) {
        try {
            File f = new File (fichier);
            FileReader fr = new FileReader (f);
            BufferedReader br = new BufferedReader (fr);
            String line = br.readLine();
            if(!line.equals("<?xml version=\"1.0\"?>")) {
                System.out.println("premier");
                return false;
            }

            //Recuperation du nom de l'equipe
            line = br.readLine();
            String patternStr = "\\s*<equipe nom=\"[a-zA-Z]*\">";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(line);
            boolean matchFound = matcher.find();
            if (!matchFound) {
                System.out.println("deuxieme");
                return false;
            }

            //Recuperation du nom et adresse du terrain
            line = br.readLine();
            patternStr = "\\s*<terrain nom=\"(.*)\" adresse=\"(.*)\"/>";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(line);
            matchFound = matcher.find();
            if (matchFound) {
                System.out.println("troisieme");
                line = br.readLine();
            }

            patternStr = "\\s*<joueurs>";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(line);
            matchFound = matcher.find();
            if(!matchFound){
                System.out.println("quatrieme");
                return false;
            }

            //Recuperation des joueurs
            line = br.readLine();
            System.out.println(line);
            String finJoueurs =  "   </joueurs>";
            while(!line.equals(finJoueurs)) {
                patternStr = "\\s*<joueur nom=\"[a-zA-Z]*\" prenom=\"[a-zA-Z]*\" numero=\"[0-9]*\" datedebut=\"[0-9]*-[0-9]*-[0-9]*\"\\s*/>";
            	//patternStr = ".*";
                pattern = Pattern.compile(patternStr);
                matcher = pattern.matcher(line);
                matchFound = matcher.find();
                if (!matchFound) {
                    System.out.println("cinquieme");
                    return false;
                }
                line = br.readLine();
            }
            line = br.readLine();
            patternStr = "\\s*</equipe>";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(line);
            matchFound = matcher.find();
            if (!matchFound) {
                System.out.println("sixieme");
                return false;
            }
            
        }catch(IOException e){
            System.out.println("except");
            return false;
        }
        
        return true;
    }

}
