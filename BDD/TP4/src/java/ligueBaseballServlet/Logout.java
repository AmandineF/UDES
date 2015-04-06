package ligueBaseballServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Classe gérant la deconnexion a la base de donnee
 * @author Amandine Fouillet
 * @author Frank Chassing
 */
public class Logout extends HttpServlet {
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // invalider la session pour liberer les ressources associees a la session
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
