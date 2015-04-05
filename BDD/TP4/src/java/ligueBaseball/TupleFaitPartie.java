package ligueBaseball;

import java.sql.Date;

/**
 * Gestion du tuple FaitPartie
 * @author Amandine Fouillet - Frank Chassing
 */
public class TupleFaitPartie {
    public int idJoueur;
    public int idEquipe;
    public int numero;
    public Date datedebut;
    public Date datefin;  
    
    /**
     * Méthode pour afficher les renseignements d'une la table faitpartie
     * @return La chaine de caracteres a afficher
    */
    @Override
    public String toString() {
        String s =  "Le joueur " + idJoueur + "fait partie de l'equipe " + idEquipe + "et il porte le numero " + numero + ".";
        if(datedebut != null && datefin != null) {
            s+= "Il est dans l'equipe depuis " + datedebut.toString() + " jusqu'a " + datefin.toString() + ".";
        } else if(datedebut != null) {
            s+= "Il est dans l'equipe depuis " + datedebut.toString() + ".";
        } else if(datefin != null) {
            s+= "Il reste dans l'equipe jusqu'à " + datefin.toString() + ".";
        }
        return s;
    }
}
