package ligueBaseball;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception, SQLException, IOException {
        if (args.length < 2) {
            System.out.println("Usage: java Main <bd> <user> <password>");
            return;
        }
        CreerBD bd = new CreerBD(args[0],args[1],args[2]);
    }
}
