package ligueBaseball;

import java.sql.*;
/**
 * Gère la connexion vers la base de données
 * @author Amandine Fouillet - Frank Chassing
 */

public class Connexion {
    
    private Connection conn;
    
    /**
     * Ouverture d'une connexion en mode autocommit false et s�rialisable (si support�)
     * @param bd nom de la base de données
     * @param user userid sur le serveur SQL
     * @param pass mot de passe sur le serveur SQL
     * @throws java.sql.SQLException
     */
    public Connexion(String bd, String user, String pass) throws SQLException {
        Driver d;
        try {
            d = (Driver) Class.forName("org.postgresql.Driver").newInstance();
            DriverManager.registerDriver(d);
           // conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433:" + bd, user, pass);
           // conn = DriverManager.getConnection("jdbc:postgresql://localhost:" + bd, user, pass);
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/" + bd,user, pass);
/**
            //Mettre en mode de commit manuel
            conn.setAutoCommit(false);

            //Mettre en mode serialisable si possible
            //(plus haut niveau d'integrité l'acces concurrent aux donnees)
            DatabaseMetaData dbmd = conn.getMetaData();
            if (dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
                conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                System.out.println(
                  "Ouverture de la connexion en mode serialisable :\n" +
                  "Estampille " + System.currentTimeMillis() + " " + conn);
                }
            else
                System.out.println(
                  "Ouverture de la connexion en mode read committed (default) :\n" +
                  "Heure " + System.currentTimeMillis() + " " + conn);
                  **/
        } catch (SQLException e) {throw e;}
        
        catch (Exception e) {
            e.printStackTrace(System.out);
            throw new SQLException("SYSTEMERROR – Problème de connexion à la base de données");
        }
    }
    
    /**
    * Fermeture d'une connexion
     * @throws java.sql.SQLException
    */
    public void fermer() throws SQLException {
        conn.rollback();
        conn.close();
        System.out.println("Connexion fermée" + " " + conn);
    }
    
    /**
     * Commit
     * @throws java.sql.SQLException
     */
    public void commit() throws SQLException {
        conn.commit();
    }

    /**
     * Rollback
     * @throws java.sql.SQLException
     */
    public void rollback() throws SQLException {
        conn.rollback();
    }

    /**
     *retourne la Connection jdbc
     * @return la connexion
     */
    public Connection getConnection() {
        return this.conn;
    }

}
