package ligueBaseballServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ligueBaseball.GestionLigue;

/**
 * Servlet pour la gestion des requêtes concernant la table arbitrer
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
                try {
                    affecterArbitreMatch(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(RequetesArbitrer.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    /**
     * Methode gerant l'affectation d'un arbitre a un match
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException 
     */
    private void affecterArbitreMatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String startDate = (String) request.getParameter("matchDateArb");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDebut = sdf.parse(startDate);
        Date matchDateArb = new java.sql.Date(dateDebut.getTime());
        
        String matchHeureArb = (String) request.getParameter("matchHeureArb");
        SimpleDateFormat sdft = new SimpleDateFormat("hh:mm:ss");
        long ms = sdft.parse(matchHeureArb).getTime();
        java.sql.Time timeValue = new java.sql.Time(ms);
        
        String nomEquipeLocaleArb = (String) request.getParameter("nomEquipeLocaleArb");
        String nomEquipeVisiteurArb = (String) request.getParameter("nomEquipeVisiteurArb");
        String nomArbitreArb = (String) request.getParameter("nomArbitreArb");
        String prenomArbitreArb = (String) request.getParameter("prenomArbitreArb");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                ligue.gestionArbitrer.arbitrerMatch(matchDateArb, timeValue,nomEquipeLocaleArb,nomEquipeVisiteurArb,nomArbitreArb,prenomArbitreArb);
            }
            request.getSession().setAttribute("succesArbitrer", "affecterArbitreMatch");
            request.getSession().setAttribute("matchDateArb", startDate);
            request.getSession().setAttribute("nomEquipeLocaleArb", nomEquipeLocaleArb);
            request.getSession().setAttribute("nomEquipeVisiteurArb", nomEquipeVisiteurArb);
            request.getSession().setAttribute("nomArbitreArb", nomArbitreArb);
            request.getSession().setAttribute("prenomArbitreArb", prenomArbitreArb);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesArbitrer.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException | ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

 
}
