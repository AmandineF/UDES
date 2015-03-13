import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelModificationEtudiant extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelModificationEtudiant(final JFrame fen, final String psdRecu, final String mdpRecu) {
		
		//Identification
		JPanel identification = new JPanel();
		identification.setBorder(BorderFactory.createTitledBorder("Identification"));
		identification.setLayout(new MigLayout("", "[60px][100px][40px][60px][100px]", "[28px]"));
		
		JLabel pseudo = new JLabel("Pseudo :");
		identification.add(pseudo, "cell 0 0,alignx left,aligny center");
		
		final JTextField pseudoRes = new JTextField(""+psdRecu);
		pseudoRes.setPreferredSize(new Dimension(100, 28));
		identification.add(pseudoRes, "cell 1 0,alignx left,aligny top");
		
		JLabel mdp = new JLabel("Mot de passe :");
		identification.add(mdp, "cell 3 0,alignx left,aligny center");
		
		final JPasswordField mdpRes = new JPasswordField(""+mdpRecu);
		mdpRes.setPreferredSize(new Dimension(100, 28));
		identification.add(mdpRes, "cell 4 0,alignx left,aligny top");
		
		identification.setBounds(0,0,500,500);
		
		//Information
		JPanel information = new JPanel();
		information.setBorder(BorderFactory.createTitledBorder("Information"));
		information.setLayout(new MigLayout("", "[60px][100px][40px][60px][100px]", "[28px][28px]"));
		
		JLabel prenom = new JLabel("Prénom :");
		information.add(prenom, "cell 0 0,alignx left,aligny center");
		Etudiant e = EtudiantManager.getInstance().getConnectedEtudiant();
		String prenomEtudiant = e.getPrenom();
		final JTextField prenomRes = new JTextField(""+prenomEtudiant);
		prenomRes.setPreferredSize(new Dimension(100,28));
		information.add(prenomRes, "cell 1 0,alignx left,aligny top");
		
		JLabel nom = new JLabel("Nom :");
		information.add(nom, "cell 3 0,alignx left,aligny center");
		String nomEtudiant = e.getNom();
		final JTextField nomRes = new JTextField(""+nomEtudiant);
		nomRes.setPreferredSize(new Dimension(100, 28));
		information.add(nomRes, "cell 4 0,alignx left,aligny top");
		
		ButtonGroup bg = new ButtonGroup();
		final JRadioButton homme = new JRadioButton("Homme");
		bg.add(homme);
		information.add(homme, "cell 0 1,alignx left,aligny center");
		JRadioButton femme = new JRadioButton("Femme");
		bg.add(femme);
		information.add(femme, "cell 1 1,alignx left,aligny center");
		if(e.getEstHomme()){
			homme.setSelected(true);
		}else{
			femme.setSelected(true);
		}
		
		//Etude
		JPanel etude = new JPanel();
		etude.setBorder(BorderFactory.createTitledBorder("Etude"));
		etude.setLayout(new MigLayout("", "[170.00px][340px]", "[28px][]"));
		
		JLabel programme = new JLabel("Programme :");
		etude.add(programme, "cell 0 0,alignx left,aligny center");
		
		final JComboBox programmeRes = new JComboBox();
		programmeRes.addItem("Bsc.Informatique");
		programmeRes.addItem("Bsc.Sciences de l'image");
		programmeRes.addItem("Bsc.Informatique de Gestion");
		etude.add(programmeRes, "cell 1 0 2 1,grow");
		
		JPanel cours = new JPanel();
		cours.setBorder(BorderFactory.createTitledBorder("Cours"));
		cours.setLayout(new MigLayout("", "[300px][40px]", "[400px][45.00px]"));
		
		ArrayList<Cours> a = e.getCours();
		DefaultListModel listModel = new DefaultListModel();
		for (Cours c : a){
			String id = c.getIdentifiant();
			String name = c.getNom();
			String coursEtudiant = ""+id+" - "+name;
			listModel.addElement(coursEtudiant);
		}; 
		final JList listeCours = new JList(listModel);
		cours.add(listeCours, "cell 0 0 2 1,grow");
		
		JButton enlever = new JButton("Enlever");
		cours.add(enlever, "cell 0 1,alignx right,aligny bottom");
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
		cours.add(ajouter, "cell 1 1,alignx left,aligny bottom");
		ajouter.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					ListModel tabElementCours = listeCours.getModel();
					ArrayList<String> idString = new ArrayList<String>();
					for(int i = 0; i<tabElementCours.getSize(); i++){
						String s = (String)tabElementCours.getElementAt(i);
						String id = s.substring(0,7);
						idString.add(id);
					}
					
					JFrame m = new JFrame();
					m.setBounds(100, 100, 600, 150);
					PanelAjout p = new PanelAjout((MainFrame)fen, m, idString, listeCours);
					m.setContentPane(p);
					m.setVisible(true);
				}}  );
		
		
		
		
		etude.add(cours, "cell 0 1 2 1,grow");	
	
		setLayout(new MigLayout("", "[470.00px]", "[40px][40px][500px][]"));
		
		this.add(identification, "cell 0 0,grow");
		this.add(information, "cell 0 1,grow");
		this.add(etude, "cell 0 2,grow");
		
		JButton annuler = new JButton("Annuler");
		this.add(annuler, "cell 0 3,alignx right,aligny top");
		annuler.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					EtudiantManager em = EtudiantManager.getInstance();
					em.deconnexion(psdRecu, mdpRecu);
					PanelConnexion p = new PanelConnexion(fen);
					fen.setContentPane(p);
					fen.setBounds(100, 100, 450, 300);
					fen.validate();
				}}  );
		
		JButton sauvegarder = new JButton("Sauvegarder");
		this.add(sauvegarder, "cell 0 3,alignx left,aligny top");
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
						String nomCours = s.substring(10, s.length());
						Cours c = new Cours(nomCours, id);
						tabCours.add(c);
					}
					etudiant.setCours(tabCours);
					
					EtudiantManager em = EtudiantManager.getInstance();
					em.deconnexion(psdRecu, mdpRecu);
					PanelConnexion p = new PanelConnexion(fen);
					fen.setContentPane(p);
					fen.setBounds(100, 100, 450, 300);
					fen.validate();
				}}  );
		
	}
	

}
