/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ligueBaseball;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Amandine
 */
public class CreerBD {
    public CreerBD(String bd, String user, String pass) throws Exception, SQLException, IOException {
        Connexion cx = new Connexion(bd, user, pass);
        cx.getConnection().setAutoCommit(true);

        Statement stmt = cx.getConnection().createStatement();

        try {
            stmt.executeUpdate("DROP TABLE arbitre CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("CREATE TABLE arbitre ( "
                            + " arbitreid            INT4                 not null,"
                            + " arbitrenom           VARCHAR(64)          not null,"
                            + " arbitreprenom        VARCHAR(64)          not null,"
                            + " constraint pk_arbitre primary key (arbitreid) ");

        try {
            stmt.executeUpdate("DROP TABLE arbitrer CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table arbitrer ( " 
                + " arbitreid            INT4                 not null," 
                + " matchid              INT4                 not null," 
                + " constraint pk_arbitrer primary key (arbitreid, matchid)");

        stmt.executeUpdate("create table equipe ("
                + " equipeid             INT4                 not null,"
                + " terrainid            INT4                 null,"
                + " equipenom            VARCHAR(64)          not null,"
                + " constraint pk_equipe primary key (equipeid)");
        stmt.close();
        cx.fermer();
    }
}
