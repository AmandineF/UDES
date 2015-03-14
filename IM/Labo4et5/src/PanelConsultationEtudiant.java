import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelConsultationEtudiant extends JPanel {
	/**
	 * Create the panel.
	 */
	public PanelConsultationEtudiant(String psd, String mdp) {
		setLayout(new MigLayout("", "[129px][31px][345px]", "[40px][40px][40px][40px][20px][400px]"));
		
		JLabel pseudo = new JLabel("Pseudo :");
		add(pseudo, "cell 0 0,alignx left,aligny center");
		
		JLabel pseudoRes = new JLabel(""+psd);
		add(pseudoRes, "cell 2 0,alignx left,aligny center");
		
		
		Etudiant e = EtudiantManager.getInstance().getConnectedEtudiant();
		String nomEtudiant = e.getNom();
		String prenomEtudiant = e.getPrenom();
		JLabel prenomRes = new JLabel(""+prenomEtudiant+" "+nomEtudiant);
		prenomRes.setBounds(183, 129, 114, 14);
		add(prenomRes, "cell 2 1,alignx left,aligny center");
		
		String sexeEtudiant;
		if(e.getEstHomme()){
			sexeEtudiant = "Homme";
		}else{
			sexeEtudiant = "Femme";
		}
		
		JLabel prenom = new JLabel("Pr\u00E9nom et nom :");
		add(prenom, "cell 0 1,alignx left,aligny center");
		
		JLabel sexe = new JLabel("Sexe :");
		add(sexe, "cell 0 2,alignx left,aligny center");
		
		JLabel sexeRes = new JLabel(""+sexeEtudiant);
		add(sexeRes, "cell 2 2,alignx left,aligny center");
		
		JLabel programme = new JLabel("Programme d'\u00E9tude :");
		add(programme, "cell 0 3,alignx left,aligny center");
		
		String programmeEtudiant = e.getProgramme().getName(); 
		JLabel programmeRes = new JLabel(""+programmeEtudiant);
		add(programmeRes, "cell 2 3,alignx left,aligny center");
		
		JList listeCours = new JList();
		ArrayList<String> tabCours = new ArrayList<String>();
		ArrayList<Cours> a = e.getCours();
		for (Cours c : a){
			String id = c.getIdentifiant();
			String name = c.getNom();
			String cours = ""+id+" - "+name;
			tabCours.add(cours);
		}
		listeCours = new JList(tabCours.toArray());
		add(listeCours, "cell 0 5 3 1,grow");
	}
	
}
