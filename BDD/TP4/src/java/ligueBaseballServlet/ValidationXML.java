package ligueBaseballServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
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
                return false;
            }

            //Recuperation du nom de l'equipe
            line = br.readLine();
            String patternStr = "\\s*<equipe nom=\"[a-zA-Z]*\">";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(line);
            boolean matchFound = matcher.find();
            if (!matchFound) {
                return false;
            }

            //Recuperation du nom et adresse du terrain
            line = br.readLine();
            patternStr = "\\s*<terrain nom=\"(.*)\" adresse=\"(.*)\"/>";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(line);
            matchFound = matcher.find();
            if (matchFound && matcher.groupCount()>=1) {
                line = br.readLine();
            }

            patternStr = "\\s*<joueurs>";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(line);
            matchFound = matcher.find();
            if(!matchFound){
                return false;
            }

            //Recuperation des joueurs
            line = br.readLine();
            String finJoueurs =  "</joueurs>";
            while(!line.equals(finJoueurs)) {
                patternStr = "\\s*<joueur nom=\"(\\s*[a-zA-Z]*)\" prenom=\"(\\s*[a-zA-Z]*)\" numero=\"([0-9]*)\" datedebut=\"([0-9]*-[0-9]*-[0-9]*)\"/>";
                pattern = Pattern.compile(patternStr);
                matcher = pattern.matcher(line);
                matchFound = matcher.find();
                if (!matchFound) {
                    return false;
                }
            }
            line = br.readLine();
            patternStr = "\\s*</equipe>";
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(line);
            matchFound = matcher.find();
            if (!matchFound) {
                return false;
            }
            
        }catch(IOException e){
            return false;
        }
        
        return true;
    }

}
