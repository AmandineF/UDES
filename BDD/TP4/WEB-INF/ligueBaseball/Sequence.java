package ligueBaseball;
import java.sql.*;

/**
 * Gère les requêtes SQL vers la classe Sequence
 * @author Amandine Fouillet - Frank Chassing
 */
public class Sequence {
    private final PreparedStatement stmtGetCle;
    private final PreparedStatement stmtSetCle;
    Connexion cx;
    
    /**
     * Constructeur de la classe
     * @param cx La connexion a la base de donnee
     * @throws SQLException 
     */
    public Sequence(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtGetCle = cx.getConnection().prepareStatement("select nextcle from sequence where nomtable = ?");
        stmtSetCle = cx.getConnection().prepareStatement("update sequence set nextcle = ? where nomtable = ?");
    }
    
    /**
     * Methode qui permet de recuperer la prochaine cle primaire d'une table
     * @param nomTable Le nom de la table dont on veut la cle 
     * @return La prochaine cle primaire
     * @throws SQLException 
     */
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
    
    /**
     * Methode qui permet de modifier la valeur de la prochaine cle primaire d'une table
     * @param nextCle La valeur de la prochaine cle
     * @param nomTable Le nom de la table
     * @throws SQLException 
     */
    public void setCle(int nextCle, String nomTable) throws SQLException {
        stmtSetCle.setInt(1,nextCle);
        stmtSetCle.setString(2,nomTable);
        stmtSetCle.executeUpdate();
    }
}
