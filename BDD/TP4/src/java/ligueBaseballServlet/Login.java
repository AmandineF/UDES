package ligueBaseballServlet;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ligueBaseball.GestionLigue;

/**
 * Classe gerant la connexion a la base de donnee et la redirection vers le menu
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class Login extends HttpServlet {
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("etat") != null) {
                    System.out.println("GestionLigue : session deja cree; id = "+ session.getId());
                    session.invalidate();
                    session = request.getSession();
                    System.out.println("GestionLigue : session invalidee");
            }

            String userId = request.getParameter("userIdBD");
            String motDePasse = request.getParameter("motDePasseBD");
            String bd = request.getParameter("bd");

            // ouvrir une connexion avec la BD et creer les gestionnaires
            System.out.println("Login : session id = " + session.getId());
            GestionLigue ligue = new GestionLigue(bd, userId, motDePasse);    

            // de l'utilisateur
            session.setAttribute("ligue", ligue);

            //Afficher le menu membre en appelant la page menu.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu.jsp");
            dispatcher.forward(request, response);
            session.setAttribute("etat", 0);
        } catch (SQLException e) {
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Erreur de connexion au serveur");
                listeMessageErreur.add(e.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    // Dans les formulaire, on utilise la methode POST
    // donc, si le servlet est appele avec la methode GET
    // on retourne un page d'erreur, afin de ne pas permettre
    // a l'utilisateur d'appeler un servler directement
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }

} 
