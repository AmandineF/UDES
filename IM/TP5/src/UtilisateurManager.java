/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.util.HashMap;

/**
 * Management d'un utilisateur
 * @author Frank Chassing 14 153 710
 * @author Amandine Fouillet 14 130 638
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
public class UtilisateurManager {

	private static UtilisateurManager MANAGER = new UtilisateurManager();

	/**
	 * Mise en place du singleton
	 * @return l'instance la classe UtilisateurManager
	 */
	public static UtilisateurManager getInstance() {
		return MANAGER;
	}

	private Utilisateur connected;

	private final HashMap<String, Utilisateur> utilisateurs;

	/**
	 * Constructeur de la classe utilisateur manager
	 */
	private UtilisateurManager() {
		connected = null;
		utilisateurs = new HashMap<String, Utilisateur>();
		loadData();
	}

	/**
	 * Mise en place de la fausse base de donnees utilisateur
	 */
	private void loadData() {
		Utilisateur frank = new Utilisateur("frank","azerty");
		Utilisateur amandine = new Utilisateur("amandine","azerty");
		Utilisateur blank = new Utilisateur("","");

		utilisateurs.put(frank.getPseudo(), frank);
		utilisateurs.put(amandine.getPseudo(), amandine);
		utilisateurs.put(blank.getPseudo(), blank);
	}
	
	/**
	 * Donne la liste des utilisateurs de la fausse base de données
	 * @return Les utilisateurs
	 */
	public HashMap<String, Utilisateur> getUtilisateur() {
		return utilisateurs;
	}

	/**
	 * Methode permettant de donner l'utilisateur connecte
	 * @return L'utilisateur connecte
	 */
	public Utilisateur getConnectedUser() {
		return connected;
	}


	/**
	 * Methode permettant de savoir si un utilisateur est connecte
	 * @param pseudo Pseudo de l'utilisateur
	 * @param motDePasse Mot de pass de l'utilisateur
	 * @return Vrai si l'utilisateur est connecte
	 */
	public boolean connexion(String pseudo, String motDePasse) {
		
		for(Utilisateur e : utilisateurs.values()){
			if(e.getPseudo().equals(pseudo)){
				if(e.getMotDePasse().equals(motDePasse)){
					connected = e;
				}
			}
		}
		return connected != null;
	}
}
