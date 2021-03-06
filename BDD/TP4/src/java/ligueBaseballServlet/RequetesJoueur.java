package ligueBaseballServlet;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
 * Servlet pour la gestion des requêtes concernant la table joueur
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class RequetesJoueur extends HttpServlet {

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
        } else if (request.getParameter("creerJoueur") != null) {
            if(!request.getParameter("nomJoueurAdd").equals("") && !request.getParameter("prenomJoueurAdd").equals("")){
                if(!request.getParameter("nomEquipeAdd").equals("")) {
                    if(!request.getParameter("numeroJoueurAdd").equals("")) {
                        if(!request.getParameter("dateDebutAdd").equals("")) {
                            try {    
                            	if(request.getParameter("dateDebutAdd").length()!=10 || !(request.getParameter("dateDebutAdd").charAt(4) == (char)'-') || !(request.getParameter("dateDebutAdd").charAt(7) == (char)'-') ){
                                    List listeMessageErreur = new LinkedList();
                                    listeMessageErreur.add("Date invalide");
                                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                                    dispatcher.forward(request, response);
                            	}else{
            	                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            	                    java.util.Date parsed = format.parse(request.getParameter("dateDebutAdd"));
            	                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
            	                    if(sql.toString().equals(request.getParameter("dateDebutAdd"))) {
            	                    	creationJoueurDate(request, response);
            	                    } else {
            	                        List listeMessageErreur = new LinkedList();
            	                        listeMessageErreur.add("Date invalide");
            	                        request.setAttribute("listeMessageErreur", listeMessageErreur);
            	                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            	                        dispatcher.forward(request, response);
            	                    }
                            	}
                               // creationJoueurDate(request, response);
                            } catch (ParseException ex) {
                                Logger.getLogger(RequetesJoueur.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            creationJoueurEquipe(request, response);   
                        }
                    }else{
                        List listeMessageErreur = new LinkedList();
                        listeMessageErreur.add("Il manque des parametres pour creer un joueur.");
                        request.setAttribute("listeMessageErreur", listeMessageErreur);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                        dispatcher.forward(request, response);
                    }
                }else{
                    creationJoueur(request, response);
                }      
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Il manque des parametres pour creer un joueur.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("afficherJoueurEquipe") != null) {
            try{
                if(!request.getParameter("nomEquipeAff").equals("")){
                    afficherJoueurEquipe(request, response);  
                }else{
                    request.getSession().setAttribute("nomEquipeAffJE", "");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/joueurs.jsp");
                    dispatcher.forward(request, response);
                }
            }catch (IOException | ServletException e) {
                request.getSession().setAttribute("nomEquipeAffJE", "");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/joueurs.jsp");
                dispatcher.forward(request, response);
            }
        }  else if (request.getParameter("supprimerJoueur") != null) {
            if(!request.getParameter("nomJoueurSup").equals("") && !request.getParameter("prenomJoueurSup").equals("")) {
                supprimerJoueur(request, response);
            } else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Il manque des parametres pour supprimer un joueur.");
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            List listeMessageErreur = new LinkedList();
            listeMessageErreur.add("Choix non reconnu.");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/messageErreur.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Methode gerant la creation d'un joueur a partir d'une certaine date
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException 
     */
    private void creationJoueurDate(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {
        String nomJoueur = (String) request.getParameter("nomJoueurAdd");
        String prenomJoueur = (String) request.getParameter("prenomJoueurAdd");
        String nomEquipe = (String) request.getParameter("nomEquipeAdd");
        int numero = Integer.parseInt(request.getParameter("numeroJoueurAdd"));
        String startDate = (String) request.getParameter("dateDebutAdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDebut = sdf.parse(startDate);
        Date date = new java.sql.Date(dateDebut.getTime());
   
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
        	boolean b;
            synchronized (ligue) {
               b= ligue.gestionJoueur.creerJoueur(nomJoueur,prenomJoueur,nomEquipe,numero,date);
            }
            if(b){
	            request.getSession().setAttribute("succesJoueur", "creationJoueur");
	            request.getSession().setAttribute("nomJoueurAdd", nomJoueur);
	            request.getSession().setAttribute("prenomJoueurAdd", prenomJoueur);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesJoueur.jsp");
	            dispatcher.forward(request, response);
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Impossible de creer le joueur, il existe deja !");
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
     * Methode gerant la creation d'un joueur
     * @param request
     * @param response
     * @throws IOException 
     */
    private void creationJoueur(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomJoueur = (String) request.getParameter("nomJoueurAdd");
        String prenomJoueur = (String) request.getParameter("prenomJoueurAdd");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
        	boolean b = false;
            synchronized (ligue) {
                b = ligue.gestionJoueur.creerJoueur(nomJoueur,prenomJoueur);
            }
            if(b){
	            request.getSession().setAttribute("succesJoueur", "creationJoueur");
	            request.getSession().setAttribute("nomJoueurAdd", nomJoueur);
	            request.getSession().setAttribute("prenomJoueurAdd", prenomJoueur);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesJoueur.jsp");
	            dispatcher.forward(request, response);
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Impossible de creer le joueur, il existe deja !");
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
     * Methode gerant la creation d'un joueur et l'attribution d'une equipe a ce joueur
     * @param request
     * @param response
     * @throws IOException 
     */
    private void creationJoueurEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomJoueur = (String) request.getParameter("nomJoueurAdd");
        String prenomJoueur = (String) request.getParameter("prenomJoueurAdd");
        String nomEquipe = (String) request.getParameter("nomEquipeAdd");
        int numero = Integer.parseInt(request.getParameter("numeroJoueurAdd"));
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
	
        try{
        	boolean b = false;

            synchronized (ligue) {
               b = ligue.gestionJoueur.creerJoueur(nomJoueur,prenomJoueur,nomEquipe,numero);
            }
            if(b){
	            request.getSession().setAttribute("succesJoueur", "creationJoueur");
	            request.getSession().setAttribute("nomJoueurAdd", nomJoueur);
	            request.getSession().setAttribute("prenomJoueurAdd", prenomJoueur);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesJoueur.jsp");
	            dispatcher.forward(request, response);
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Impossible de creer le joueur, il existe deja !");
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
     * Methode gerant l'affichage de tous les joueurs d'une equipe
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    private void afficherJoueurEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomEquipe = (String) request.getParameter("nomEquipeAff");
        request.getSession().setAttribute("nomEquipeAffJE", nomEquipe);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/joueurs.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Methode gerant la suppression d'un joueur
     * @param request
     * @param response
     * @throws IOException 
     */
    private void supprimerJoueur(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomJoueur = (String) request.getParameter("nomJoueurSup");
        String prenomJoueur = (String) request.getParameter("prenomJoueurSup");
        GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
        	boolean b;
            synchronized (ligue) {
               b= ligue.gestionJoueur.supprimerJoueur(nomJoueur,prenomJoueur);
            }
            if(b){
	            request.getSession().setAttribute("succesJoueur", "suppressionJoueur");
	            request.getSession().setAttribute("nomJoueurSup", nomJoueur);
	            request.getSession().setAttribute("prenomJoueurSup", prenomJoueur);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesJoueur.jsp");
	            dispatcher.forward(request, response);
            }else{
                List listeMessageErreur = new LinkedList();
                listeMessageErreur.add("Impossible de supprimer, le joueur n'existe pas");
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
