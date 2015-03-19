package ligueBaseball;
import java.sql.*;

/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class Sequence {
    private final PreparedStatement stmtGetCle;
    private final PreparedStatement stmtSetCle;
    Connexion cx;
    
    public Sequence(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtGetCle = cx.getConnection().prepareStatement("select nextcle from sequence where nomTable = ?");
        stmtSetCle = cx.getConnection().prepareStatement("update sequence set nextcle = ? where nomTable = ?");
    }
    
    public int getCle(String nomTable) throws SQLException {
        stmtGetCle.setString(1, nomTable);
        int res = -1;
        try (ResultSet rset = stmtGetCle.executeQuery()) {
            if(rset.next()) {
                res = rset.getInt(1);
            }
        }
        return res;
    }
    
    public void ajout(int nextCle, String nomTable) throws SQLException {
        stmtSetCle.setInt(1,nextCle);
        stmtSetCle.setString(2,nomTable);
        stmtSetCle.executeUpdate();
    }
}
