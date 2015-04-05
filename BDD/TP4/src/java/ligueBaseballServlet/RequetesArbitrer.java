package ligueBaseballServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ligueBaseball.GestionLigue;

/**
 *
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class RequetesArbitrer extends HttpServlet {

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
        } else if (request.getParameter("arbitrerMatch") != null) {
            if(!request.getParameter("matchDateArb").equals("") &&
                    !request.getParameter("matchHeureArb").equals("") &&
                    !request.getParameter("nomEquipeLocaleArb").equals("") &&
                    !request.getParameter("nomEquipeVisiteurArb").equals("")&&
                    !request.getParameter("nomArbitreArb").equals("") &&
                    !request.getParameter("prenomArbitreArb").equals("")){
                affecterArbitreMatch(request, response);
            }else{
                //ERREUR MANQUE PARAMETRES
            }
        } else if (request.getParameter("afficherArbitrer") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/arbitrer.jsp");
            dispatcher.forward(request, response);
        }else{
            //ERREUR CHOIX INCONNU
        }
    }

    private void affecterArbitreMatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String matchDateArb = (String) request.getParameter("matchDateArb");
        String matchHeureArb = (String) request.getParameter("matchHeureArb");
        String nomEquipeLocaleArb = (String) request.getParameter("nomEquipeLocaleArb");
        String nomEquipeVisiteurArb = (String) request.getParameter("nomEquipeVisiteurArb");
        String nomArbitreArb = (String) request.getParameter("nomArbitreArb");
        String prenomArbitreArb = (String) request.getParameter("prenomArbitreArb");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                //TODO convertir string en date et string en heure
                //ligue.gestionArbitrer.arbitrerMatch(matchDateArb, matchHeureArb,nomEquipeLocaleArb,nomEquipeVisiteurArb,nomArbitreArb,prenomArbitreArb);
            }
            request.getSession().setAttribute("succesMatch", "affecterArbitreMatch");
            request.getSession().setAttribute("matchDateArb", matchDateArb);
            request.getSession().setAttribute("nomEquipeLocaleArb", nomEquipeLocaleArb);
            request.getSession().setAttribute("nomEquipeVisiteurArb", nomEquipeVisiteurArb);
            request.getSession().setAttribute("nomArbitreArb", matchDateArb);
            request.getSession().setAttribute("prenomArbitreArb", nomEquipeLocaleArb);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesArbitrer.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

 
}
