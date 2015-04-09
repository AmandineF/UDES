package ligueBaseballServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ligueBaseball.GestionLigue;

/**
 * Methode qui gere l'importation d'un fichier XML
 * @author Amandine Fouillet 
 * @author Frank Chassing
 */
public class ImportationXML extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("importer") != null) {
            importation(request, response);
        } else {
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add("Choix non reconnu");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void importation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("importation");
        String nomFichier = (String) request.getParameter("nomFichierXML");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        ValidationXML val = new ValidationXML(); 
        if(val.validerXML(nomFichier)){
            File f = new File (nomFichier);
            try {
            synchronized (ligue) {
                FileReader fr = new FileReader (f);
                BufferedReader br = new BufferedReader (fr);
                String line = br.readLine();
                //Recuperation du nom de l'equipe
                line = br.readLine();
                String patternStr = "\\s*<equipe nom=\"([a-zA-Z]*)\">";
                String nomEquipe = "";
                Pattern pattern = Pattern.compile(patternStr);
                Matcher matcher = pattern.matcher(line);
                boolean matchFound = matcher.find();
                if (matchFound && matcher.groupCount()>=1) {
                    nomEquipe = matcher.group(1);
                }
                if(ligue.gestionEquipe.equipeExiste(nomEquipe)) {
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add("L'equipe a importer existe deja.");
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                } else {
                
                //Recuperation du nom et adresse du terrain
                line = br.readLine();
                String nomTerrain = "";
                String addTerrain = "";
                patternStr = "\\s*<terrain nom=\"(.*)\" adresse=\"(.*)\"/>";
                pattern = Pattern.compile(patternStr);
                matcher = pattern.matcher(line);
                matchFound = matcher.find();
                if (matchFound && matcher.groupCount()>=1) {
                    nomTerrain = matcher.group(1);
                    addTerrain = matcher.group(2);
                    ligue.gestionEquipe.creerEquipe(nomEquipe, nomTerrain, addTerrain);
                    line = br.readLine();
                } else {
                    ligue.gestionEquipe.creerEquipe(nomEquipe);
                }
                //Recuperation des joueurs
                line = br.readLine();
                String finJoueurs =  "</joueurs>";
                while(!line.equals(finJoueurs)) {
                    String nomJoueur = "";
                    String prenomJoueur = "";
                    int numeroJoueur = -1;
                    String dateDebut = "";
                    Date dateJoueur = null;
                    patternStr = "\\s*<joueur nom=\"(\\s*[a-zA-Z]*)\" prenom=\"(\\s*[a-zA-Z]*)\" numero=\"([0-9]*)\" datedebut=\"([0-9]*-[0-9]*-[0-9]*)\"/>";
                    pattern = Pattern.compile(patternStr);
                    matcher = pattern.matcher(line);
                    matchFound = matcher.find();
                    if (matchFound && matcher.groupCount()>=1) {
                        nomJoueur = matcher.group(1);
                        prenomJoueur = matcher.group(2);
                        numeroJoueur = Integer.parseInt(matcher.group(3));
                        dateDebut = matcher.group(4);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = sdf.parse(dateDebut);
                        dateJoueur = new java.sql.Date(date.getTime());
                    }
                    ligue.gestionJoueur.creerJoueur(nomJoueur, prenomJoueur, nomEquipe, numeroJoueur, dateJoueur);
                    line = br.readLine();
                }
                }
            request.getSession().setAttribute("importation", nomFichier);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesImportation.jsp");
            dispatcher.forward(request, response);
            }
        }catch(IOException | SQLException | ServletException | NumberFormatException | ParseException e) {
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
        }else{
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add("Fichier XML non valide.");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
        
    }


}
