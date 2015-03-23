
public class Controler {
	private Etudiant etudiant;
	protected String nom = "", prenom = "";
	public Controler(Etudiant e){
		this.etudiant = e;
	}
	public Etudiant getEtudiant(){
		return etudiant;
	}
	
	public void setNom(String nom){
		this.nom = nom;
		control();
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
		control();
	}
	public void control(){
		if(this.nom != ""){
			etudiant.setNom(nom);
		}
		if(this.prenom != ""){
			etudiant.setPrenom(prenom);
		}
	}
}
