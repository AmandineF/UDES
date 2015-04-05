package ligueBaseball;

import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
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

        try {
            stmt.executeUpdate("DROP TABLE equipe CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table equipe ("
                + " equipeid             INT4                 not null,"
                + " terrainid            INT4                 null,"
                + " equipenom            VARCHAR(64)          not null,"
                + " constraint pk_equipe primary key (equipeid)");
        
        try {
            stmt.executeUpdate("DROP TABLE faitpartie CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table faitpartie ("
                + " joueurid          INT4                 not null,"
                + " equipeid          INT4                 null,"
                + " numero            VARCHAR(64)          not null,"
                + " datedebut         DATE          not null,"
                + " datefin           DATE         null,"
                + " constraint pk_faitpartie primary key (joueurid, equipeid)");
        
        try {
            stmt.executeUpdate("DROP TABLE joueur CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table joueur ("
                + " joueurid             INT4                 not null,"
                + " joueurnom            VARCHAR(64)          not null,"
                + " joueurprenom         VARCHAR(64)          not null,"
                + " constraint pk_joueur primary key (joueurid)");
        
        try {
            stmt.executeUpdate("DROP TABLE match CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table match ("
                + " matchid              INT4                 not null,"
                + " equipelocal          INT4                 null,"
                + " equipevisiteur       INT4                 null,"
                + " terrainid            INT4                 null,"
                + " matchdate            DATE                 null,"
                + " matchheure           TIME                 null,"
                + " pointslocal          INT4                 null,"
                + " pointsvisiteur       INT4                 null,"
                + " constraint pk_joueur primary key (joueurid)");
        try {
            stmt.executeUpdate("DROP TABLE participe CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table participe ("
                + " joueurid             INT4                 not null,"
                + " matchid              INT4                 not null,"
                + " commentaireperformance VARCHAR(128)         null,"
                + " constraint pk_participe primary key (joueurid, matchid)");
        
        try {
            stmt.executeUpdate("DROP TABLE terrain CASCADE");
        } catch (SQLException e) {}
        
        stmt.executeUpdate("create table terrain ("
                + " terrainid            INT4                 not null,"
                + " terrainnom           VARCHAR(64)          not null,"
                + " terrainadresse       VARCHAR(128)         not null,"
                + " constraint pk_terrain primary key (terrainid)");
        
        
        stmt.close();
        cx.fermer();
    }
}
