package ligueBaseball;
import java.sql.*;
import java.util.LinkedList;
/**
 *
 * @author Amandine Fouillet - Frank Chassing
 */
public class GestionEquipe {
    private Equipe equipeTable;
    private Sequence sequence;
    private Terrain terrain;
    private FaitPartie faitpartie;
    
    
    /**
     * 
     */
    public GestionEquipe() {}
    
    /**
     * Cree une equipe dans la base de donnee a partir de son nom
     * @param equipenom le nom de l'equipe a creer
     * @throws SQLException 
     */
    public void creerEquipe(String equipenom) throws SQLException  {
        
        if(equipeTable.existeNom(equipenom)){
        	System.out.println("Erreur : Nom d'equipe deja existant !");
        }else{
        	int idEquipe = sequence.getCle("equipe");
        	//int idTerrain = sequence.getCle("terrain");
        	equipeTable.ajout(idEquipe, (Integer)null, equipenom );
        	System.out.println("Equipe ajoutee ! Elle ne possede pas de terrain..");
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
        	System.out.println("Erreur : Nom d'equipe deja existant !");
        }else{
        	if(terrain.existeNom(nomterrain, adresseterrain)){
        		int idEquipe = sequence.getCle("equipe");
        		int idTerrain = terrain.getId(nomterrain, adresseterrain);
            	equipeTable.ajout(idEquipe, idTerrain, equipenom );
            	System.out.println("Equipe ajoutee !");
        	}else{
        		int idEquipe = sequence.getCle("equipe");
        		int idTerrain = sequence.getCle("terrain");
        		terrain.ajout(idTerrain, nomterrain, adresseterrain);
            	equipeTable.ajout(idEquipe, idTerrain, equipenom );
            	System.out.println("Equipe et Terrain ajoutee !");
        	}
        	
        }
    }
    
    /**
     * Affiche l'ensemble des equipes (id+nom)
     * @throws SQLException 
     */
    public void afficherEquipes() throws SQLException{
    	LinkedList<String> listeEquipe = equipeTable.affiche();
    	if(listeEquipe.isEmpty()){
    		System.out.println("Pas d'equipe..");
    	}else{
	    	for(String str : listeEquipe){
	    		System.out.println(""+str+"\n");
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
    		if(faitpartie.getNbJoueur(idEquipe) != 0){
        		System.out.println("La transaction ne peut avoir lieu car l'equipe possede des joueurs");
        	}else{
        		{
        			equipeTable.suppression(idEquipe);
        			System.out.println("Equipe bien supprimee !");
        		}
        	}
    	}else{
    		System.out.println("Equipe bien supprimee !");
    	}
    	
    }
}













