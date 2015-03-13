import java.util.ArrayList;
import java.util.HashMap;

public class EtudiantManager {

	private static EtudiantManager MANAGER = new EtudiantManager();

	public static EtudiantManager getInstance() {
		return MANAGER;
	}

	private Etudiant connected;

	private final ArrayList<Cours> cours;
	private final HashMap<String, Etudiant> etudiants;
	private final ArrayList<Programme> programmes;

	private EtudiantManager() {
		connected = null;

		cours = new ArrayList<Cours>();
		etudiants = new HashMap<String, Etudiant>();
		programmes = new ArrayList<Programme>();

		loadData();
	}

	private void loadData() {
		Cours ift209 = new Cours("Programmation système", "IFT 209");
		Cours ift215 = new Cours("Interfaces et multimédia", "IFT 215");
		Cours ift232 = new Cours("Méthodes de conception orientées objet",
				"IFT 232");
		Cours ift287 = new Cours("Exploitation de BD relationnelles et OO",
				"IFT 287");

		Cours ift313 = new Cours("Introduction aux langages formels", "IFT 313");
		Cours ift320 = new Cours("Systèmes d'exploitation", "IFT 320");
		Cours ift339 = new Cours("Structures de données", "IFT 339");
		Cours ift359 = new Cours("Programmation fonctionnelle", "IFT 359");

		Cours mat193 = new Cours("Algèbre linéaire", "MAT 193");

		Programme informatique = new Programme("Bsc.Informatique");
		Programme imagerie = new Programme("Bsc.Sciences de l'image");
		Programme gestion = new Programme("Bsc.Informatique de gestion");

		cours.add(ift209);
		cours.add(ift215);
		cours.add(ift232);
		cours.add(ift287);

		cours.add(ift313);
		cours.add(ift320);
		cours.add(ift339);
		cours.add(ift359);

		cours.add(mat193);

		programmes.add(informatique);
		programmes.add(imagerie);
		programmes.add(gestion);

		Etudiant justinTrudeau = new Etudiant("truj1234", "Justin", "Trudeau",
				"13579", true, informatique);
		justinTrudeau.addCours(ift209);
		justinTrudeau.addCours(ift215);
		justinTrudeau.addCours(ift287);

		Etudiant fredPellerin = new Etudiant("pelf1234", "Fred", "Pellerin",
				"13579", true, informatique);
		fredPellerin.addCours(ift215);
		fredPellerin.addCours(ift287);
		fredPellerin.addCours(ift339);
		fredPellerin.addCours(mat193);

		Etudiant liseBissonnette = new Etudiant("bisl1234", "Lise",
				"Bissonnette", "13579", false, informatique);
		liseBissonnette.addCours(ift339);
		liseBissonnette.addCours(ift232);
		liseBissonnette.addCours(ift313);
		liseBissonnette.addCours(mat193);

		Etudiant jeanMichaelle = new Etudiant("jeam1234", "Michaelle", "Jean",
				"13579", false, informatique);
		jeanMichaelle.addCours(ift215);
		jeanMichaelle.addCours(ift313);
		jeanMichaelle.addCours(ift320);
		jeanMichaelle.addCours(ift359);

		etudiants.put(justinTrudeau.getPseudo(), justinTrudeau);
		etudiants.put(fredPellerin.getPseudo(), fredPellerin);
		etudiants.put(liseBissonnette.getPseudo(), liseBissonnette);
		etudiants.put(jeanMichaelle.getPseudo(), jeanMichaelle);

	}

	public Etudiant getConnectedEtudiant() {
		return connected;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Programme> getProgrammes() {
		return (ArrayList<Programme>) programmes.clone();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Cours> getCours() {
		return (ArrayList<Cours>) cours.clone();
	}

	/**
	 * 
	 * @param nomUtilisateur
	 * @param motDePasse
	 * @return Vrai si l'utilisateur est connectÃ©
	 */
	public boolean connexion(String pseudo, String motDePasse) {
		Etudiant etudiant = etudiants.get(pseudo);

		if (etudiant != null) {
			if (etudiant.getMotDePasse().equals(motDePasse)) {
				connected = etudiant;
			}
		}

		return connected != null;
	}

	public void deconnexion(String nomUtilisateur, String motDePasse) {
		this.connected = null;
	}
}
