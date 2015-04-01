public class Contact {
	private String nom;
	private String prenom;
	private String numero;
	private String image;
	
	public Contact(String n, String p, String num, String i) {
		this.nom = n;
		this.prenom = p;
		this.numero = num;
		this.image = i;
	}

	public void setNom(String n){
		this.nom = n;
	}
	
	public void setPrenom(String p){
		this.prenom = p;
	}
	
	public void setNumero(String num){
		this.numero = num;
	}
	
	public void setImage(String i){
		this.prenom = i;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public String getNumero(){
		return this.numero;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public String getImage(){
		return this.image;
	}
}
