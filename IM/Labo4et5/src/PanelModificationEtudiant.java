import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelModificationEtudiant extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelModificationEtudiant() {
		
		
		//Identification
		JPanel identification = new JPanel();
		identification.setBorder(BorderFactory.createTitledBorder("Identification"));
		
		JLabel pseudo = new JLabel("Pseudo :");
		identification.add(pseudo);
		
		JTextField pseudoRes = new JTextField();
		identification.add(pseudoRes);
		
		JLabel mdp = new JLabel("Mot de passe :");
		identification.add(mdp);
		
		JPasswordField mdpRes = new JPasswordField();
		identification.add(mdpRes);
		
		//Information
		JPanel information = new JPanel();
		information.setBorder(BorderFactory.createTitledBorder("Information"));
		
		JLabel prenom = new JLabel("Prénom :");
		information.add(prenom);
		
		JTextField prenomRes = new JTextField();
		information.add(prenomRes);
		
		JLabel nom = new JLabel("Nom :");
		information.add(nom);
		
		JTextField nomRes = new JTextField();
		information.add(nomRes);
		
		JRadioButton homme = new JRadioButton("Homme");
		information.add(homme);
		JRadioButton femme = new JRadioButton("Femme");
		information.add(femme);
		
		//Etude
		JPanel etude = new JPanel();
		etude.setBorder(BorderFactory.createTitledBorder("Etude"));
		
		JLabel programme = new JLabel("Programme :");
		etude.add(programme);
		
		JComboBox programmeRes = new JComboBox();
		etude.add(programmeRes);
		
		JPanel cours = new JPanel();
		cours.setBorder(BorderFactory.createTitledBorder("Cours"));
		
		
		JList listeCours = new JList();
		cours.add(listeCours);
		JButton enlever = new JButton("Enlever");
		cours.add(enlever);
		JButton ajouter = new JButton("Ajouter");
		cours.add(ajouter);
		
		etude.add(cours);
		
		this.add(identification);
		this.add(information);
		this.add(etude);
		JButton annuler = new JButton("Annuler");
		this.add(annuler);
		JButton sauvegarder = new JButton("Sauvegarder");
		this.add(sauvegarder);
		
		
		// TODO ajouter du code pour changer les elements dans la vue à l'aide du windowsBuilder
	}

}
