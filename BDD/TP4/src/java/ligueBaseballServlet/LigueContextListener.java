package ligueBaseballServlet;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Amandine Fouillet 
 * @author Frank Chassing
 */
public class LigueContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
            System.out.println("Contexte démarré :" + sce.getServletContext().getServletContextName());
            System.out.println("Voici les paramètres du contexte tels que définis dans web.xml");
            Enumeration initParams = sce.getServletContext().getInitParameterNames();
            while (initParams.hasMoreElements()) {
                    String name = (String) initParams.nextElement();
                    System.out.println(name + ":" + sce.getServletContext().getInitParameter(name));
            }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
            System.out.println("Le contexte de l'application GestionLigue vient d'être détruit.");
    }
}
