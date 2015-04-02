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
		Contact contact1 = new Contact("Anna","Gaspile","06 23 43 10 90","./Images/contactDefaut.png",true,true,false, true);
		addContact(contact1);
		Contact contact2 = new Contact("Alice","Manceau","06 56 68 96 90","./Images/contactDefaut.png",true,false,true,false);
		addContact(contact2);
		Contact contact3 = new Contact("Arthur","Bouret","06 24 24 54 16","./Images/contactDefaut.png",true,true,true,true);
		addContact(contact3);
		Contact contact4 = new Contact("Baptiste","Hulin","06 25 68 96 90","./Images/contactDefaut.png",false,true,false,true);
		addContact(contact4);
		Contact contact5 = new Contact("Chlo\u00E9","Lonet","06 56 46 96 90","./Images/contactDefaut.png",false,false,false,false);
		addContact(contact5);
		Contact contact6 = new Contact("Cl\u00E9ment","Rond","06 26 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact6);
		Contact contact7 = new Contact("Denis","Scott","06 56 34 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact7);
		Contact contact8 = new Contact("David","Paillat","06 56 68 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact8);
		Contact contact9 = new Contact("Elodie","Prin","06 56 25 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact9);
		Contact contact10 = new Contact("Eloise","Perdit","06 56 68 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact10);
		Contact contact11 = new Contact("F\u00E9lix","Leroux","06 93 68 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact11);
		Contact contact12 = new Contact("Justine","Picherit","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact12);
		Contact contact13 = new Contact("Jade","Olivier","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact13);
		Contact contact14 = new Contact("Lili","Potter","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact14);
		Contact contact15 = new Contact("Maman","","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact15);
		Contact contact16 = new Contact("Mamie","","06 56 68 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact16);
		Contact contact17 = new Contact("Nina","Louiton","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact17);
		Contact contact18 = new Contact("Pauline","Trustin","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact18);
		Contact contact20 = new Contact("Richard","Pennes","06 56 68 96 90","./Images/contactDefaut.png",true, true, false,true);
		addContact(contact20);
		Contact contact21 = new Contact("Valentine","Augereau","06 56 68 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact21);
		Contact contact22 = new Contact("Yannick","Noah","06 56 68 96 90","./Images/contactDefaut.png",false, true, false,true);
		addContact(contact22);
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
