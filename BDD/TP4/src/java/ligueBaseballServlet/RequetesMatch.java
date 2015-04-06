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
 * Servlet pour la gestion des requêtes concernant la table match
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class RequetesMatch extends HttpServlet {

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
        } else if (request.getParameter("creerMatch") != null) {
            if(!request.getParameter("matchDateAdd").equals("") &&
                    !request.getParameter("matchHeureAdd").equals("") &&
                    !request.getParameter("nomEquipeLocaleAdd").equals("") &&
                    !request.getParameter("nomEquipeVisiteurAdd").equals("")) {
                try {
                    creerMatch(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(RequetesMatch.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //ERREUR MANQUE PARAMETRES
            }
        } else if (request.getParameter("entrerResultatMatch") != null) {
            if(!request.getParameter("matchDateRes").equals("") &&
                    !request.getParameter("matchHeureRes").equals("") &&
                    !request.getParameter("nomEquipeLocaleRes").equals("") &&
                    !request.getParameter("nomEquipeVisiteurRes").equals("") &&
                    !request.getParameter("pointsLocalRes").equals("") &&
                    !request.getParameter("pointsVisiteurRes").equals("")) {
                try {
                    entrerResultatMatch(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(RequetesMatch.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //ERREUR MANQUE PARAMETRES
            }
            
        } else if (request.getParameter("afficherResultatDate") != null) {
            if (!request.getParameter("matchDateAff").equals("")) {
                String startDate = (String) request.getParameter("matchDateAff");
                request.getSession().setAttribute("matchdate", startDate);
                request.getSession().setAttribute("matchequipe", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("matchequipe", "");
                request.getSession().setAttribute("matchdate", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("afficherResultatEquipe") != null){
            if (!request.getParameter("nomEquipeAff").equals("")) {
                String nomEquipeAff = (String) request.getParameter("nomEquipeAff");
                request.getSession().setAttribute("matchequipe", nomEquipeAff);
                request.getSession().setAttribute("matchdate", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("matchequipe", "");
                request.getSession().setAttribute("matchdate", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("afficherMatch") != null){
                request.getSession().setAttribute("matchequipe", "");
                request.getSession().setAttribute("matchdate", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                dispatcher.forward(request, response);
        }else{
            //ERREUR CHOIX INCONNU
        }
    }

    /**
     * Methode gerant la creation d'un match
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException 
     */
    private void creerMatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String startDate = (String) request.getParameter("matchDateAdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDebut = sdf.parse(startDate);
        Date matchDateAdd = new java.sql.Date(dateDebut.getTime());
        String matchHeureAdd = (String) request.getParameter("matchHeureAdd");
        SimpleDateFormat sdft = new SimpleDateFormat("hh:mm:ss");
        long ms = sdft.parse(matchHeureAdd).getTime();
        java.sql.Time timeValue = new java.sql.Time(ms);
        String nomEquipeLocaleAdd = (String) request.getParameter("nomEquipeLocaleAdd");
        String nomEquipeVisiteurAdd = (String) request.getParameter("nomEquipeVisiteurAdd");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        System.out.println("oui");
        try{
            synchronized (ligue) {
                ligue.gestionMatch.creerMatch(matchDateAdd, timeValue,nomEquipeLocaleAdd,nomEquipeVisiteurAdd);
            }
            request.getSession().setAttribute("succesMatch", "creationMatch");
            request.getSession().setAttribute("matchDateAdd", startDate);
            request.getSession().setAttribute("nomEquipeLocaleAdd", nomEquipeLocaleAdd);
            request.getSession().setAttribute("nomEquipeVisiteurAdd", nomEquipeVisiteurAdd);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesMatch.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException | ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    /**
     * Methode gerant l'enregistrement des resultats d'un match
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException 
     */
    private void entrerResultatMatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String startDate = (String) request.getParameter("matchDateRes");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDebut = sdf.parse(startDate);
        Date matchDateRes = new java.sql.Date(dateDebut.getTime());
        String matchHeureAdd = (String) request.getParameter("matchHeureRes");
        SimpleDateFormat sdft = new SimpleDateFormat("hh:mm:ss");
        long ms = sdft.parse(matchHeureAdd).getTime();
        java.sql.Time timeValue = new java.sql.Time(ms);
        String nomEquipeLocaleRes = (String) request.getParameter("nomEquipeLocaleRes");
        String nomEquipeVisiteurRes = (String) request.getParameter("nomEquipeVisiteurRes");
        int pointsLocalRes = Integer.parseInt(request.getParameter("pointsLocalRes"));
        int pointsVisiteurRes = Integer.parseInt(request.getParameter("pointsVisiteurRes"));
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                ligue.gestionMatch.entrerResultat(matchDateRes, timeValue,nomEquipeLocaleRes,nomEquipeVisiteurRes,pointsLocalRes,pointsVisiteurRes);
            }
            request.getSession().setAttribute("succesMatch", "resultatMatch");
            request.getSession().setAttribute("matchDateRes", startDate);
            request.getSession().setAttribute("nomEquipeLocaleRes", nomEquipeLocaleRes);
            request.getSession().setAttribute("nomEquipeVisiteurRes", nomEquipeVisiteurRes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesMatch.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException | ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
