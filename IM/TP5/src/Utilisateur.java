import java.util.LinkedList;

/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
public class Utilisateur{

	private String pseudo;
	private String prenom;
	private String nom;
	private LinkedList<Contact> listContacts;
	private String motDePasse;


	public Utilisateur(String pseudo, String prenom, String nom, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.prenom = prenom;
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.listContacts = new LinkedList<Contact>();
		ContactParDefaut();
	}
	
	public void ContactParDefaut(){
		Contact maman = new Contact("Maman","","","./Images/maman.png");
		addContact(maman);
		Contact mamie = new Contact("Mamie","","","./Images/papa.png");
		addContact(mamie);
		Contact manue = new Contact("Manue","Dupont","","./Images/manue.png");
		addContact(manue);
		Contact michel = new Contact("Michel","Rochais","","./Images/manue.png");
		addContact(michel);
		Contact nina = new Contact("Nina","Fouli","","./Images/nina.png");
		addContact(nina);
		Contact noah = new Contact("Noah","Yannick","","./Images/noah.png");
		addContact(noah);
		Contact nathan = new Contact("Nathan","Scott","","./Images/nathan.png");
		addContact(nathan);
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public LinkedList<Contact> getContact(){
		return this.listContacts;
	}
	
	public void addContact(Contact c){
		this.listContacts.add(c);
	}
}
