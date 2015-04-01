import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelContacts extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContacts() {	
		this.setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		MigLayout ml = new MigLayout("insets 0, gapy 0", "", "");
		sp.setBackground(Color.WHITE);
		sp.setLayout(ml);
		LinkedList<Contact> listContact = UtilisateurManager.getInstance().getConnectedUser().getContact();
		int nbContact = listContact.size();
		
		JPanel barreLettre = new JPanel();
		barreLettre.setLayout(new MigLayout("insets 0", "", ""));
		barreLettre.setPreferredSize(new Dimension (348, 5));
		JLabel lettre = new JLabel("A");
		lettre.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		lettre.setFont(new Font("Mockup", Font.PLAIN, 10));
		lettre.setForeground(Color.WHITE);
		barreLettre.add(lettre, "cell 0 0, alignx left, aligny center");
		barreLettre.setBackground(new Color(68,68,68));
		String old = "Z";
		int j = 0;
		for(int i = 0; i < nbContact; i++) {
			/*
			String firstLettre = listContact.get(i).getNom().substring(0, 1);
			if(!(firstLettre.equals(old))){
				lettre.setText(firstLettre);
				sp.add(barreLettre, "cell 0 " + j + ",alignx left, aligny center");
				j++;
				old = firstLettre;
			}
			*/
			PanelContact pc = new PanelContact(listContact.get(i).getNom(), 
					listContact.get(i).getPrenom(),
					listContact.get(i).getNumero(),
					listContact.get(i).getImage());
			sp.add(pc, "cell 0 " + i + ",alignx center, aligny center");	
		}
		
		JScrollPane contentScrollPane = new JScrollPane(sp);
		contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScrollPane.setPreferredSize(new Dimension (440, 405));
		this.add(contentScrollPane, "cell 0 0,alignx center, aligny center");
		
	}

}
