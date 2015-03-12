import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelModificationEtudiant extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelModificationEtudiant(JFrame fen, String psdRecu, String mdpRecu) {
		
		
		//Identification
		JPanel identification = new JPanel();
		identification.setBorder(BorderFactory.createTitledBorder("Identification"));
		
		JLabel pseudo = new JLabel("Pseudo :");
		identification.add(pseudo);
		
		JTextField pseudoRes = new JTextField(""+psdRecu);
		identification.add(pseudoRes);
		
		
		JLabel mdp = new JLabel("Mot de passe :");
		identification.add(mdp);
		
		JPasswordField mdpRes = new JPasswordField(""+mdpRecu);
		identification.add(mdpRes);
		
		//Information
		JPanel information = new JPanel();
		information.setBorder(BorderFactory.createTitledBorder("Information"));
		
		JLabel prenom = new JLabel("Prénom :");
		information.add(prenom);
		
		Etudiant e = EtudiantManager.getInstance().getConnectedEtudiant();
		String prenomEtudiant = e.getPrenom();
		JTextField prenomRes = new JTextField(""+prenomEtudiant);
		information.add(prenomRes);
		
		JLabel nom = new JLabel("Nom :");
		information.add(nom);
		
		String nomEtudiant = e.getNom();
		JTextField nomRes = new JTextField(""+nomEtudiant);
		information.add(nomRes);
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton homme = new JRadioButton("Homme");
		bg.add(homme);
		information.add(homme);
		JRadioButton femme = new JRadioButton("Femme");
		bg.add(femme);
		information.add(femme);
		
		//Etude
		JPanel etude = new JPanel();
		etude.setBorder(BorderFactory.createTitledBorder("Etude"));
		
		JLabel programme = new JLabel("Programme :");
		etude.add(programme);
		
		JComboBox<String> programmeRes = new JComboBox<String>();
		programmeRes.addItem("Bsc.Informatique");
		programmeRes.addItem("Bsc.Sciences de l'image");
		programmeRes.addItem("Bsc.Informatique de Gestion");
		etude.add(programmeRes);
		
		JPanel cours = new JPanel();
		cours.setBorder(BorderFactory.createTitledBorder("Cours"));
		
		
		
		ArrayList<Cours> a = e.getCours();
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (Cours c : a){
			String id = c.getIdentifiant();
			String name = c.getNom();
			String coursEtudiant = ""+id+" - "+name;
			listModel.addElement(coursEtudiant);
		};
		JList listeCours = new JList(listModel);
		cours.add(listeCours);
		
		JButton enlever = new JButton("Enlever");
		cours.add(enlever);
		enlever.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					DefaultListModel model = (DefaultListModel) listeCours.getModel();
					int selectedIndex = listeCours.getSelectedIndex();
					if (selectedIndex != -1) {
					    model.remove(selectedIndex);
					}
				}}  );
		
		JButton ajouter = new JButton("Ajouter");
		cours.add(ajouter);
		
		etude.add(cours);
		
		this.add(identification);
		this.add(information);
		this.add(etude);
		
		JButton annuler = new JButton("Annuler");
		this.add(annuler);
		
		annuler.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					EtudiantManager em = EtudiantManager.getInstance();
					em.deconnexion(psdRecu, mdpRecu);
					PanelConnexion p = new PanelConnexion(fen);
					fen.setContentPane(p);
					fen.setBounds(100, 100, 450, 300);
					fen.revalidate();
				}}  );
		
		JButton sauvegarder = new JButton("Sauvegarder");
		this.add(sauvegarder);
		
		sauvegarder.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					Etudiant etudiant = EtudiantManager.getInstance().getConnectedEtudiant();
					etudiant.setPseudo(pseudoRes.getText());
					System.out.println(pseudoRes.getText());
					etudiant.setMotDePasse(String.valueOf(mdpRes.getPassword()));
					etudiant.setPrenom(prenomRes.getText());
					etudiant.setNom(nomRes.getText());
					if(homme.isSelected()){
						etudiant.setEstHomme(true);
					}else{
						etudiant.setEstHomme(false);
					}
					Programme prog = new Programme((String)programmeRes.getSelectedItem());
					etudiant.setProgramme(prog);
					ListModel tabElement = listeCours.getModel();
					ArrayList<Cours> tabCours = new ArrayList<Cours>();
					for(int i = 0; i<tabElement.getSize(); i++){
						String s = (String)tabElement.getElementAt(i);
						String id = s.substring(0,6);
						String nomCours = s.substring(10, s.length()-1);
						Cours c = new Cours(nomCours, id);
						tabCours.add(c);
					}
					etudiant.setCours(tabCours);
					
					EtudiantManager em = EtudiantManager.getInstance();
					em.deconnexion(psdRecu, mdpRecu);
					PanelConnexion p = new PanelConnexion(fen);
					fen.setContentPane(p);
					fen.setBounds(100, 100, 450, 300);
					fen.revalidate();
				}}  );
		
		
	}

}
