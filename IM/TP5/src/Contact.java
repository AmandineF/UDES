public class Contact {
	private String nom;
	private String prenom;
	private String numero;
	private String image;
	private Boolean accesPhoto;
	private Boolean accesCal;
	private Boolean accesDep;
	private Boolean accesCarte;
	
	public Contact(String n, String p, String num, String i, Boolean ap, Boolean ac, Boolean ad, Boolean aca) {
		this.nom = n;
		this.prenom = p;
		this.numero = num;
		this.image = i;
		this.accesCal = ac;
		this.accesPhoto = ap;
		this.accesDep = ad;
		this.accesCarte = aca;
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
	
	public Boolean getAccesPhoto(){
		return this.accesPhoto;
	}
	public Boolean getAccesCarte(){
		return this.accesCarte;
	}
	public Boolean getAccesCal(){
		return this.accesCal;
	}
	public Boolean getAccesDep(){
		return this.accesDep;
	}
}
