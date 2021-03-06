package ligueBaseballServlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ligueBaseball.GestionLigue;
import ligueBaseball.TupleJoueur;
import ligueBaseball.TupleTerrain;

/**
 * Methode qui gere l'exportation d'un fichier XML
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class ExportationXML extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("exporter") != null) {
            try {
                exportation(request, response);
            } catch (SQLException ex) {
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add(ex.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add("Choix non reconnu");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void exportation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String nomEquipe = (String) request.getParameter("nomEquipeXML");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        File f = new File (nomEquipe + ".xml");
        try{
            synchronized (ligue) {
                if(ligue.gestionEquipe.equipeExiste(nomEquipe)) {
                    FileWriter fw = new FileWriter (f);
                    fw.write("<?xml version=\"1.0\"?>\n");
                    fw.write("<equipe nom=\""+nomEquipe+"\">\n");
                    int idTerrain = ligue.gestionEquipe.getIDTerrain(nomEquipe);
                    if(idTerrain > 0) {
                        TupleTerrain tupleTerrain = ligue.gestionEquipe.getTerrain(idTerrain);
                        fw.write("<terrain nom=\""+ tupleTerrain.terrainnom+"\" adresse=\""+tupleTerrain.terrainadresse +"\"/>\n");
                    }
                    Vector<TupleJoueur> listJoueur = ligue.gestionJoueur.afficherJoueursEquipeVector(nomEquipe);
                    fw.write("   <joueurs>\n");
                    for(int i = 0; i<listJoueur.size();i++) {
                        TupleJoueur tupleJoueur = listJoueur.elementAt(i);
                        int numero = ligue.gestionJoueur.getNumeroJoueur(tupleJoueur.idJoueur);
                        Date date = ligue.gestionJoueur.getDateJoueur(tupleJoueur.idJoueur);
                        fw.write("      <joueur nom=\""+tupleJoueur.nom+"\" prenom=\""+tupleJoueur.prenom+"\" numero=\""+ numero+"\" datedebut=\""+date.toString() +"\" />\n");
                    }
                    fw.write("   </joueurs>\n");
                    fw.write("</equipe>");
                    fw.close();
                    ValidationXML val = new ValidationXML();
                    if(!val.validerXML(nomEquipe + ".xml")){
                        List listeMessageErreur = new LinkedList();
                        listeMessageErreur.add("Fichier XML exporte non valide.");
                        request.setAttribute("listeMessageErreur", listeMessageErreur);
                        request.getSession().setAttribute("exportation", "");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        request.getSession().setAttribute("exportation", nomEquipe);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesExportation.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    request.getSession().setAttribute("exportation", "");
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add("L'equipe a exporter n'existe pas.");
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }catch (SQLException e) {
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }

    }


}
