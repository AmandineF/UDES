/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.util.HashMap;

public class UtilisateurManager {

	private static UtilisateurManager MANAGER = new UtilisateurManager();

	public static UtilisateurManager getInstance() {
		return MANAGER;
	}

	private Utilisateur connected;

	private final HashMap<String, Utilisateur> utilisateurs;

	private UtilisateurManager() {
		connected = null;

		utilisateurs = new HashMap<String, Utilisateur>();

		loadData();
	}

	private void loadData() {

		Utilisateur frank = new Utilisateur("frank","azerty");
		Utilisateur amandine = new Utilisateur("amandine","azerty");
		Utilisateur blank = new Utilisateur("","");

		utilisateurs.put(frank.getPseudo(), frank);
		utilisateurs.put(amandine.getPseudo(), amandine);
		utilisateurs.put(blank.getPseudo(), blank);

	}
	public HashMap<String, Utilisateur> getUtilisateur() {
		return utilisateurs;
	}

	public Utilisateur getConnectedUser() {
		return connected;
	}


	/**
	 * 
	 * @param nomUtilisateur
	 * @param motDePasse
	 * @return Vrai si l'utilisateur est connect�
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

	public void deconnexion(String nomUtilisateur, String motDePasse) {
		this.connected = null;
	}
}
