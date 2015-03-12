import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

@SuppressWarnings("serial")
public class PanelConsultationEtudiant extends JPanel {
	/**
	 * Create the panel.
	 */
	public PanelConsultationEtudiant() {
		setLayout(null);
		
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
		
		JLabel pseudoRes = new JLabel("jean");
		pseudoRes.setBounds(145, 94, 46, 14);
		add(pseudoRes);
		
		JLabel prenomRes = new JLabel("Jean de la fontaine");
		prenomRes.setBounds(183, 129, 114, 14);
		add(prenomRes);
		
		JLabel sexeRes = new JLabel("Homme");
		sexeRes.setBounds(132, 162, 46, 14);
		add(sexeRes);
		
		JLabel programmeRes = new JLabel("Informatique");
		programmeRes.setBounds(201, 197, 96, 14);
		add(programmeRes);
		
		JList listeCours = new JList();
		listeCours.setBounds(0, 222, 450, 78);
		
		add(listeCours);

	}
	public void setEtudiant(Etudiant etudiant) {
		// TODO ajouter du code pour changer les elements dans la vue à l'aide du windowsBuilder
	}
}
