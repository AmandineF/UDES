package ligueBaseballServlet;
import java.sql.SQLException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import ligueBaseball.GestionLigue;
/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class LigueSessionListener implements HttpSessionListener {
    
    /**
     * 
     * @param se 
     */
    @Override
    public void sessionCreated(HttpSessionEvent se){
    }

    /**
     * 
     * @param se 
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("LigueSessionListener " + se.getSession().getId());
        GestionLigue ligue = (GestionLigue) se.getSession().getAttribute("ligue");
        if (ligue != null) {
            System.out.println("Connexion = " + ligue.cx);
            try {ligue.fermer();}
            catch (SQLException e){
              System.out.println("Impossible de fermer la connexion.");
            }
        }else{
            System.out.println("Ligue inaccessible.");
        }
    }
  
  

}
