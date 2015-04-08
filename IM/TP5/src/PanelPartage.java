import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel partage
 * Affiche les contacts avec qui l'utilisateur partage ses donnees
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelPartage extends JPanel {
	/**
	 * Classe interne permettant de modeliser un panel pour un seul contact
	 */
	private class UnContact extends JPanel {
		public UnContact(final Contact contact){
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			setLayout(new MigLayout("","[40][300]",""));
			setBackground(Color.WHITE);
			
			JLabel nomContact = new JLabel(contact.getNom() + " " + contact.getPrenom());
			nomContact.setFont(new Font("Mockup", Font.PLAIN, 12));
			this.add(nomContact, "cell 1 0, alignx left, aligny bottom");
			
			JLabel imageContact = new JLabel(new ImageIcon(contact.getImage()));
			this.add(imageContact, "cell 0 0, alignx left, aligny center");	
		}
	}

	/**
	 * Constructeur du panel partage
	 * @param fen La fenetre d'origine
	 */
	public PanelPartage(final JFrame fen) {
		this.setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		MigLayout ml = new MigLayout("insets 0, gapy 0", "", "");
		sp.setLayout(ml);
		final LinkedList<Contact> listContact = UtilisateurManager.getInstance().getConnectedUser().getContact();
		int nbContact = listContact.size();
		
		int j = 0;
		for(int i = 0; i < nbContact; i++) {
			if(i%2 == 0) {
				UnContact pl = new UnContact(listContact.get(i));
				sp.add(pl, "cell 0 " + j +", alignx center, aligny center");
				j++;
			}	
		}
		
		JScrollPane contentScrollPane = new JScrollPane(sp);
		contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScrollPane.setPreferredSize(new Dimension (440, 405));
		this.add(contentScrollPane, "cell 0 0,alignx center, aligny center");
	}
}
