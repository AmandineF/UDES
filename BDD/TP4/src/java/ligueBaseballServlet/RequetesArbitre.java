package ligueBaseballServlet;
import java.io.IOException;
import java.sql.SQLException;
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
                    Logger.getLogger(RequetesArbitre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                //ERREUR MANQUE PARAMETRES
            }
        } else if(request.getParameter("afficherArbitres") != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/arbitres.jsp");
            dispatcher.forward(request, response);
        }else {
            // CHOIX INCONNU
        }
    }

    public void creationArbitre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nomArbitre = (String) request.getParameter("nomArbitre");
        String prenomArbitre = (String) request.getParameter("prenomArbitre");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                ligue.gestionArbitre.creerArbitre(nomArbitre, prenomArbitre);
            }
            request.getSession().setAttribute("succesArbitre", "creationArbitre");
            request.getSession().setAttribute("nomArbitreAdd", nomArbitre);
            request.getSession().setAttribute("prenomArbitreAdd", prenomArbitre);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesArbitre.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
