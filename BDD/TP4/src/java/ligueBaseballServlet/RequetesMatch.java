package ligueBaseballServlet;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                	if(request.getParameter("matchDateAdd").length()!=10 || !(request.getParameter("matchDateAdd").charAt(4) == (char)'-') || !(request.getParameter("matchDateAdd").charAt(7) == (char)'-') ){
                        List listeMessageErreur = new LinkedList();
                        listeMessageErreur.add("Date invalide");
                        request.setAttribute("listeMessageErreur", listeMessageErreur);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                        dispatcher.forward(request, response);
                	}else{
	                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	                    java.util.Date parsed = format.parse(request.getParameter("matchDateAdd"));
	                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
	                    if(sql.toString().equals(request.getParameter("matchDateAdd"))) {
	                    	if(request.getParameter("matchHeureAdd").length() !=8 ||!(request.getParameter("matchHeureAdd").charAt(2) == (char)':') || !(request.getParameter("matchHeureAdd").charAt(5) == (char)':') ){
	                            List listeMessageErreur = new LinkedList();
	                            listeMessageErreur.add("Heure invalide");
	                            request.setAttribute("listeMessageErreur", listeMessageErreur);
	                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
	                            dispatcher.forward(request, response);
	                    	}else{
		                        creerMatch(request, response);
	                    	}
	                    } else {
	                        List listeMessageErreur = new LinkedList();
	                        listeMessageErreur.add("Date invalide");
	                        request.setAttribute("listeMessageErreur", listeMessageErreur);
	                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
	                        dispatcher.forward(request, response);
	                    }
                	}
                } catch (ParseException ex) {
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add(ex.toString());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Il manque des parametres pour creer un match.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("entrerResultatMatch") != null) {
            if(!request.getParameter("matchDateRes").equals("") &&
                    !request.getParameter("matchHeureRes").equals("") &&
                    !request.getParameter("nomEquipeLocaleRes").equals("") &&
                    !request.getParameter("nomEquipeVisiteurRes").equals("") &&
                    !request.getParameter("pointsLocalRes").equals("") &&
                    !request.getParameter("pointsVisiteurRes").equals("")) {
                try {
                    if(request.getParameter("matchDateRes").length()!=10 || !(request.getParameter("matchDateRes").charAt(4) == (char)'-') || !(request.getParameter("matchDateRes").charAt(7) == (char)'-') ){
                        List listeMessageErreur = new LinkedList();
                        listeMessageErreur.add("Date invalide");
                        request.setAttribute("listeMessageErreur", listeMessageErreur);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                        dispatcher.forward(request, response);
                	}else{
	                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	                    java.util.Date parsed = format.parse(request.getParameter("matchDateRes"));
	                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
	                    if(sql.toString().equals(request.getParameter("matchDateRes"))) {
	                    	if(request.getParameter("matchHeureRes").length() !=8 ||!(request.getParameter("matchHeureRes").charAt(2) == (char)':') || !(request.getParameter("matchHeureRes").charAt(5) == (char)':') ){
	                            List listeMessageErreur = new LinkedList();
	                            listeMessageErreur.add("Heure invalide");
	                            request.setAttribute("listeMessageErreur", listeMessageErreur);
	                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
	                            dispatcher.forward(request, response);
	                    	}else{
		                        entrerResultatMatch(request, response);
	                    	}
	                    } else {
	                        List listeMessageErreur = new LinkedList();
	                        listeMessageErreur.add("Date invalide");
	                        request.setAttribute("listeMessageErreur", listeMessageErreur);
	                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
	                        dispatcher.forward(request, response);
	                    }
                    }
                } catch (ParseException ex) {
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add(ex.toString());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Il manque des parametres pour entrer les resultats d'un match.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
            
        } else if (request.getParameter("afficherResultatDate") != null) {
            if (request.getParameter("matchDateAff").equals("")) {
                request.getSession().setAttribute("matchdate", "");
                request.getSession().setAttribute("matchequipe", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                dispatcher.forward(request, response);
            } else {
                try{
                    if(request.getParameter("matchDateAff").length()!=10 || !(request.getParameter("matchDateAff").charAt(4) == (char)'-') || !(request.getParameter("matchDateAff").charAt(7) == (char)'-') ){
                        List listeMessageErreur = new LinkedList();
                        listeMessageErreur.add("Date invalide");
                        request.setAttribute("listeMessageErreur", listeMessageErreur);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                        dispatcher.forward(request, response);
                        }else{
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date parsed = format.parse(request.getParameter("matchDateAff"));
                            java.sql.Date sql = new java.sql.Date(parsed.getTime());
                            if(sql.toString().equals(request.getParameter("matchDateAff"))) {	                    	
                                request.getSession().setAttribute("matchequipe", "");
                                request.getSession().setAttribute("matchdate", request.getParameter("matchDateAff"));
                                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                                dispatcher.forward(request, response);
                            } else {
                                List listeMessageErreur = new LinkedList();
                                listeMessageErreur.add("Date invalide");
                                request.setAttribute("listeMessageErreur", listeMessageErreur);
                                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                                dispatcher.forward(request, response);
                            }
                        }
                }catch(Exception ex){
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add(ex.toString());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                }
          }
        } else if (request.getParameter("afficherResultatEquipe") != null){
            if (!request.getParameter("nomEquipeAff").equals("")) {
                afficherResultatEquipe(request, response);
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
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add("Choix non reconnu.");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Methode gerant la creation d'un match
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException 
     */
    private void creerMatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {
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
        try{
            int i;
            synchronized (ligue) {
                i= ligue.gestionMatch.creerMatch(matchDateAdd, timeValue,nomEquipeLocaleAdd,nomEquipeVisiteurAdd);
            }
            if(i==0){
	            request.getSession().setAttribute("succesMatch", "creationMatch");
	            request.getSession().setAttribute("matchDateAdd", startDate);
	            request.getSession().setAttribute("nomEquipeLocaleAdd", nomEquipeLocaleAdd);
	            request.getSession().setAttribute("nomEquipeVisiteurAdd", nomEquipeVisiteurAdd);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesMatch.jsp");
	            dispatcher.forward(request, response);
            }else if(i==1){
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("L'equipe visiteur n'existe pas.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }else if(i==2){
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("L'equipe locale n'existe pas.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Les equipes n'existent pas");
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

    /**
     * Methode gerant l'enregistrement des resultats d'un match
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException 
     */
    private void entrerResultatMatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {
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
            int res = -1;
            synchronized (ligue) {
                res =ligue.gestionMatch.entrerResultat(matchDateRes, timeValue,nomEquipeLocaleRes,nomEquipeVisiteurRes,pointsLocalRes,pointsVisiteurRes);
            }
            if(res == 0){ 
            request.getSession().setAttribute("succesMatch", "resultatMatch");
            request.getSession().setAttribute("matchDateRes", startDate);
            request.getSession().setAttribute("nomEquipeLocaleRes", nomEquipeLocaleRes);
            request.getSession().setAttribute("nomEquipeVisiteurRes", nomEquipeVisiteurRes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesMatch.jsp");
            dispatcher.forward(request, response);
            }else if(res == 1) {
                 List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Les points doivent etre superieurs a zero.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            } else {
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Le match n'existe pas");
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

    private void afficherResultatEquipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomEquipeAff = (String) request.getParameter("nomEquipeAff");
        GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                if(ligue.gestionEquipe.equipeExiste(nomEquipeAff)) {
                    request.getSession().setAttribute("matchequipe", nomEquipeAff);
                    request.getSession().setAttribute("matchdate", "");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/matchs.jsp");
                    dispatcher.forward(request, response);
                } else {
                    List listeMessageErreur = new LinkedList();
                    listeMessageErreur.add("L'equipe n'existe pas.");
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                    dispatcher.forward(request, response);
                }
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
