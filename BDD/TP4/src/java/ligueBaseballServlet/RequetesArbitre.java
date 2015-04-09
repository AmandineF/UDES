package ligueBaseballServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ligueBaseball.GestionLigue;

/**
 * Servlet pour la gestion des requêtes concernant la table arbitre
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class RequetesArbitre extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
        } else if(request.getParameter("creerArbitre") != null){
            if (!request.getParameter("nomArbitre").equals("") && !request.getParameter("prenomArbitre").equals("")) {
                try {
                    creationArbitre(request, response);
                } catch (SQLException ex) {
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add(ex.toString());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                }
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Il manques des parametres pour l'operation de creation d'arbitre.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
        } else if(request.getParameter("afficherArbitres") != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/arbitres.jsp");
            dispatcher.forward(request, response);
        }else {
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add("Choix non reconnu");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Methode gerant l'execution de la requete de creation d'un arbitre
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException 
     * @throws javax.servlet.ServletException 
     */
    public void creationArbitre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String nomArbitre = (String) request.getParameter("nomArbitre");
        String prenomArbitre = (String) request.getParameter("prenomArbitre");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
        	boolean b;
            synchronized (ligue) {
               b= ligue.gestionArbitre.creerArbitre(nomArbitre, prenomArbitre);
            }
            if(b){
	            request.getSession().setAttribute("succesArbitre", "creationArbitre");
	            request.getSession().setAttribute("nomArbitreAdd", nomArbitre);
	            request.getSession().setAttribute("prenomArbitreAdd", prenomArbitre);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesArbitre.jsp");
	            dispatcher.forward(request, response);
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Impossible de creer car l'arbitre existe deja !");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
        }catch (SQLException | ServletException | IOException e) {
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
    }
}
