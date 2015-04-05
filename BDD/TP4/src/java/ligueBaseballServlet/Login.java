package ligueBaseballServlet;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import ligueBaseball.GestionLigue;

/**
 * Classe
 * <P>
 *
 */

public class Login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                HttpSession session = request.getSession();
                // fermer la session si elle a deje ete ouverte lors d'un appel
                // precedent
                // survient lorsque l'usager recharge la page login.jsp
                if (session.getAttribute("etat") != null) {
                        // pour deboggage seulement : afficher no session et information
                        System.out.println("GestionLigue : session deja cree; id = "+ session.getId());
                        // la methode invalidate appelle le listener
                        session.invalidate();
                        session = request.getSession();
                        System.out.println("GestionLigue: session invalidee");
                }

                // lecture des parametres du formulaire login.jsp
                String userIdOracle = request.getParameter("userIdBD");
                String motDePasseOracle = request.getParameter("motDePasseBD");
                String serveur = request.getParameter("serveur");
                String adresseIP = request.getParameter("adresseIP");
                String bd = request.getParameter("bd");

                // ouvrir une connexion avec la BD et creer les gestionnaires
                System.out.println("Login : session id = " + session.getId());
                GestionLigue ligue = new GestionLigue(bd, userIdOracle, motDePasseOracle);    

                // de l'utilisateur
                session.setAttribute("ligue", ligue);

                //Afficher le menu membre en appelant la page arbitre.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu.jsp");
                dispatcher.forward(request, response);
                session.setAttribute("etat", LigueConstantes.CONNECTE);
            } catch (SQLException e) {
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add("Erreur de connexion au serveur");
                    listeMessageErreur.add(e.toString());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    // pour d�boggage seulement : afficher tout le contenu de
                    // l'exception
                    e.printStackTrace();
            } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e
                                    .toString());
            }
    }

	// Dans les formulaire, on utilise la m�thode POST
	// donc, si le servlet est appel� avec la m�thode GET
	// on retourne un page d'erreur, afin de ne pas permettre
	// � l'utilisateur d'appeler un servler directement
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendError(response.SC_INTERNAL_SERVER_ERROR, "Acc�s
		// invalide");
		doPost(request, response);
	}

} // class
