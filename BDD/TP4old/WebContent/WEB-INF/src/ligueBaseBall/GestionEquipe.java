package ligueBaseball;
import java.sql.*;
import java.util.Vector;
/**
 * Gestion des demandes utilisateurs vers la table equipe
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionEquipe {
    private final Equipe equipeTable;
    private final Sequence sequence;
    private final Terrain terrain;
    private final FaitPartie faitpartie;
    private final Connexion cx;
    
    
    /**
     * Constructeur de la classe GestionEquipe
     * @param co La connexion a la base de donnee
     * @throws SQLException
     */
    public GestionEquipe(Connexion co) throws SQLException {
        this.cx = co;
        this.equipeTable = new Equipe(this.cx);
        this.sequence = new Sequence(this.cx);
        this.terrain = new Terrain(this.cx);
        this.faitpartie = new FaitPartie(this.cx);
    }
    
    /**
     * Cree une equipe dans la base de donnee a partir de son nom
     * @param equipenom le nom de l'equipe a creer
     * @throws SQLException 
     */
    public void creerEquipe(String equipenom) throws SQLException  {
        
        if(equipeTable.existeNom(equipenom)){
        	System.out.println("USERERREUR - L'equipe existe deja.");
        }else{
            int idEquipe = sequence.getCle("equipe");
            equipeTable.ajout(idEquipe, -1, equipenom );
            System.out.println("SUCCES - Equipe ajoutee, elle ne possede pas de terrain.");
            this.sequence.ajout((idEquipe +1), "equipe");
        }
        
    }
    
    /**
     * Cree une equipe dans la base de donnee a partir de son nom et lui attribue un terrain ; cree le terrain s'il n'existe pas
     * @param equipenom le nom de l'equipe a creer
     * @param nomterrain le nom du terrain a attribue
     * @param adresseterrain l'adresse du terrain attribue
     * @throws SQLException 
     */
    public void creerEquipe(String equipenom, String nomterrain ,String adresseterrain) throws SQLException  {
    	if(equipeTable.existeNom(equipenom)){
        	System.out.println("USERERREUR - L'equipe existe deja.");
        }else{
            int idEquipe = sequence.getCle("equipe");
            int idTerrain;
            if(terrain.existeNom(nomterrain, adresseterrain)){
                idTerrain = terrain.getId(nomterrain, adresseterrain);
                equipeTable.ajout(idEquipe, idTerrain, equipenom );
                System.out.println("SUCCES - Equipe ajoutee.");
            }else{
                idTerrain = sequence.getCle("terrain");
                terrain.ajout(idTerrain, nomterrain, adresseterrain);
                equipeTable.ajout(idEquipe, idTerrain, equipenom );
                System.out.println("SUCCES - Equipe et terrain ajoutes.");
            }
        }
    }
    
    /**
     * Affiche l'ensemble des equipes (id+nom)
     * @throws SQLException 
     */
    public void afficherEquipes() throws SQLException{
    	Vector<TupleEquipe>  listeEquipe = equipeTable.affiche();
    	if(listeEquipe.isEmpty()){
    		System.out.println("Il n'y a pas d'equipe pour le moment.");
    	}else{
            System.out.println("Affichage des equipes : \n");
            for(TupleEquipe te : listeEquipe){
                    System.out.println(te.toStringWT() + "\n");
            }
    	}
    }
    
    /**
     * Supprime une equipe donnee en parametre si elle n'a pas de joueurs, renvoie un message d'erreur sinon
     * @param equipenom le nom de l'equipe a supprimer
     * @throws SQLException 
     */
    public void supprimerEquipe(String equipenom) throws SQLException{
    	if(equipeTable.existeNom(equipenom)){
    		int idEquipe = equipeTable.getId(equipenom);
    		if(faitpartie.getNbJoueur(idEquipe) > 0){
                    System.out.println("USERERREUR - L'equipe " + equipenom + "ne peut être supprimee car elle possede des joueurs.");
        	}else{
                    equipeTable.suppression(idEquipe);
                    System.out.println("SUCCES - Equipe " + equipenom + " a ete supprimee.");
        	}
    	}else{
    		System.out.println("USERERREUR – L'equipe " + equipenom + " a supprimer n'existe pas.");
    	}
    	
    }
}












