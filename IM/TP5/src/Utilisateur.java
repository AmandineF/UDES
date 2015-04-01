import java.util.LinkedList;

/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
public class Utilisateur{

	private String pseudo;
	private LinkedList<Contact> listContacts;
	private String motDePasse;


	public Utilisateur(String pseudo, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.listContacts = new LinkedList<Contact>();
		ContactParDefaut();
	}
	
	public void ContactParDefaut(){
		Contact maman = new Contact("Maman","","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(maman);
		Contact mamie = new Contact("Mamie","","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(mamie);
		Contact manue = new Contact("Manue","Dupont","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(manue);
		Contact michel = new Contact("Michel","Rochais","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(michel);
		Contact nina = new Contact("Nina","Fouli","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(nina);
		Contact noah = new Contact("Noah","Yannick","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(noah);
		Contact nathan = new Contact("Nathan","Scott","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(nathan);
		Contact maman1 = new Contact("Maman","","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(maman1);
		Contact mamie1 = new Contact("Mamie","","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(mamie1);
		Contact manue1 = new Contact("Manue","Dupont","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(manue1);
		Contact michel1 = new Contact("Michel","Rochais","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(michel1);
		Contact nina1 = new Contact("Nina","Fouli","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(nina1);
		Contact noah1 = new Contact("Noah","Yannick","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(noah1);
		Contact nathan1 = new Contact("Nathan","Scott","06 56 68 96 90","./Images/contactDefaut.png");
		addContact(nathan1);
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
