import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

@SuppressWarnings("serial")
public class PanelConsultationEtudiant extends JPanel {
	/**
	 * Create the panel.
	 */
	public PanelConsultationEtudiant(String psd, String mdp) {
		setLayout(null);
		this.setBounds(100, 100, 800, 400);
		JLabel pseudo = new JLabel("Pseudo :");
		pseudo.setBounds(89, 94, 46, 14);
		add(pseudo);
		
		JLabel prenom = new JLabel("Pr\u00E9nom et nom :");
		prenom.setBounds(89, 129, 84, 14);
		add(prenom);
		
		JLabel sexe = new JLabel("Sexe :");
		sexe.setBounds(89, 162, 46, 14);
		add(sexe);
		
		JLabel programme = new JLabel("Programme d'\u00E9tude :");
		programme.setBounds(89, 197, 120, 14);
		add(programme);
		
		JLabel pseudoRes = new JLabel(""+psd);
		pseudoRes.setBounds(145, 94, 46, 14);
		add(pseudoRes);
		
		Etudiant e = EtudiantManager.getInstance().getConnectedEtudiant();
		String nomEtudiant = e.getNom();
		String prenomEtudiant = e.getPrenom();
		JLabel prenomRes = new JLabel(""+prenomEtudiant+""+nomEtudiant);
		prenomRes.setBounds(183, 129, 114, 14);
		add(prenomRes);
		
		String sexeEtudiant;
		if(e.getEstHomme()){
			sexeEtudiant = "Homme";
		}else{
			sexeEtudiant = "Femme";
		}
		JLabel sexeRes = new JLabel(""+sexeEtudiant);
		sexeRes.setBounds(132, 162, 46, 14);
		add(sexeRes);
		
		String programmeEtudiant = e.getProgramme().getName(); 
		JLabel programmeRes = new JLabel(""+programmeEtudiant);
		programmeRes.setBounds(201, 197, 96, 14);
		add(programmeRes);
		
		JList listeCours;
		ArrayList<String> tabCours = new ArrayList<String>();
		ArrayList<Cours> a = e.getCours();
		for (Cours c : a){
			String id = c.getIdentifiant();
			String name = c.getNom();
			String cours = ""+id+" - "+name;
			tabCours.add(cours);
		}
		listeCours = new JList(tabCours.toArray());
		
		
		add(listeCours);

	}
	public void setEtudiant(Etudiant etudiant) {
		// TODO ajouter du code pour changer les elements dans la vue à l'aide du windowsBuilder
	}
}
