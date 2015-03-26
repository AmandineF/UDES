package ligueBaseball;

/**
 * Gestion du tuple Participe
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleParticipe {
    public int joueurid;
    public int matchid;
    public String commentaireperformance;
    
    /**
     * Méthode pour afficher les renseignements d'une participation
     * @return La chaine de caracteres a afficher
     */
    @Override
    public String toString() {
        String s =  "Le joueur " + joueurid +
                "a participe au match " + matchid + ".";
        if(!commentaireperformance.equals("")) {
            s+= "\nCommentaires sur sa performance : " + commentaireperformance;
        }
        return s;
    }
}
