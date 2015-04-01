import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;


public class PanelContact extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContact(String nom, String prenom, String numero, String image) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		setBackground(Color.WHITE);
		setLayout(new MigLayout("","9[10%]5[35%]5[9%][10%][10%][10%][10%][10%][1%]","[50%][50%]"));
		
		JLabel nomContact = new JLabel(nom + " " + prenom);
		nomContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(nomContact, "cell 1 0, alignx left, aligny bottom");
		
		JLabel numeroContact = new JLabel(numero);
		numeroContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(numeroContact, "cell 1 1, alignx left, aligny top");
		
		JLabel imageContact = new JLabel(image);
		this.add(imageContact, "cell 0 0 0 2, alignx left, aligny center");
		
		JLabel comContact = new JLabel(new ImageIcon("./Images/rondComContact.png"));
		this.add(comContact, "cell 2 0 0 2, alignx right, aligny center");
		
		JLabel phoContact = new JLabel(new ImageIcon("./Images/rondPhoContact.png"));
		this.add(phoContact, "cell 3 0 0 2, alignx right, aligny center");
		
		JLabel calContact = new JLabel(new ImageIcon("./Images/rondCalContact.png"));
		this.add(calContact, "cell 4 0 0 2, alignx right, aligny center");
		
		JLabel depContact = new JLabel(new ImageIcon("./Images/rondDepContact.png"));
		this.add(depContact, "cell 5 0 0 2, alignx right, aligny center");
		
		JLabel carContact = new JLabel(new ImageIcon("./Images/rondCarContact.png"));
		this.add(carContact, "cell 6 0 0 2, alignx right, aligny center");
		
		JLabel infoContact = new JLabel(new ImageIcon("./Images/rondComContact.png"));
		this.add(infoContact, "cell 7 0 0 2, alignx right, aligny center");
		
	}

}
