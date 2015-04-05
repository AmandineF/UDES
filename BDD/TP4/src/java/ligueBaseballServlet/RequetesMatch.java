package ligueBaseballServlet;

import java.io.IOException;
import java.sql.Date;
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
 *
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
        } else if (request.getParameter("creerMatchAdd") != null) {
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
                    entrerResultatMatch(request, response);
            } else {
                //ERREUR MANQUE PARAMETRES
            }
            
        } else if (request.getParameter("afficherResultatDate") != null) {
            if (!request.getParameter("matchDateAff").equals("")) {
                afficherResultatDate(request, response);
            } else {
                afficherResultat(request, response);
            }
        } else if (request.getParameter("afficherResultatEquipe") != null){
            if (!request.getParameter("nomEquipeAff").equals("")) {
                afficherResultatEquipe(request, response);
            } else {
                afficherResultat(request, response);
            }
        } else if (request.getParameter("afficherMatch") != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
            dispatcher.forward(request, response);
        }else{
            //ERREUR CHOIX INCONNU
        }
    }

    private void creerMatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String startDate = (String) request.getParameter("matchDateAdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDebut = sdf.parse(startDate);
        Date matchDateAdd = new java.sql.Date(dateDebut.getTime());
        String matchHeureAdd = (String) request.getParameter("matchHeureAdd");
        String nomEquipeLocaleAdd = (String) request.getParameter("nomEquipeLocaleAdd");
        String nomEquipeVisiteurAdd = (String) request.getParameter("nomEquipeVisiteurAdd");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                //ligue.gestionMatch.creerMatch(matchDateAdd, matchHeureAdd,nomEquipeLocaleAdd,nomEquipeVisiteurAdd);
            }
            request.getSession().setAttribute("succesMatch", "creationMatch");
            request.getSession().setAttribute("matchDateAdd", matchDateAdd);
            request.getSession().setAttribute("nomEquipeLocaleAdd", nomEquipeLocaleAdd);
            request.getSession().setAttribute("nomEquipeVisiteurAdd", nomEquipeVisiteurAdd);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesMatch.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    private void entrerResultatMatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String matchDateRes = (String) request.getParameter("matchDateRes");
        String matchHeureRes = (String) request.getParameter("matchHeureRes");
        String nomEquipeLocaleRes = (String) request.getParameter("nomEquipeLocaleRes");
        String nomEquipeVisiteurRes = (String) request.getParameter("nomEquipeVisiteurRes");
        int pointsLocalRes = Integer.parseInt(request.getParameter("pointsLocalRes"));
        int pointsVisiteurRes = Integer.parseInt(request.getParameter("pointsVisiteurRes"));
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                //TODO convertir string en date et string en heure
                //ligue.gestionMatch.entrerResultat(matchDateRes, matchHeureRes,nomEquipeLocaleRes,nomEquipeVisiteurRes,pointsLocalRes,pointsVisiteurRes);
            }
            request.getSession().setAttribute("succesMatch", "resultatMatch");
            request.getSession().setAttribute("matchDateRes", matchDateRes);
            request.getSession().setAttribute("nomEquipeLocaleRes", nomEquipeLocaleRes);
            request.getSession().setAttribute("nomEquipeVisiteurRes", nomEquipeVisiteurRes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesMatch.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    private void afficherResultatDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String matchDateAff = (String) request.getParameter("matchDateAff");
        GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                //TODO convertir string en date
                //ligue.gestionMatch.afficherResultatsDate(matchDateAff);
            }
            //A voir ou on affiche les resultats
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/match.jsp");
            //dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    private void afficherResultat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/match.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherResultatEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nomEquipeAff = (String) request.getParameter("nomEquipeAff");
        GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                ligue.gestionMatch.afficherResultatEquipe(nomEquipeAff);
            }
            //A voir ou on affiche les resultats
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/match.jsp");
            //dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

}
