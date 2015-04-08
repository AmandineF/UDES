import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel contacts
 * Affiche la liste de tous les contacts de l'utilisateur
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelContacts extends JPanel {
	/**
	 * Classe interne permettant de creer une barre grise contenant une lettre de l'alphabet
	 * Afin de donner une indication lors de la recherche de contact
	 */
	private class PanelLettre extends JPanel {
		public PanelLettre(String l){
			this.setLayout(new MigLayout("insets 0", "", ""));
			this.setPreferredSize(new Dimension (348, 5));
			JLabel lettre = new JLabel(l);
			lettre.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
			lettre.setFont(new Font("Mockup", Font.PLAIN, 10));
			lettre.setForeground(Color.WHITE);
			this.add(lettre, "cell 0 0, alignx left, aligny center");
			this.setBackground(new Color(94,94,94));
		}
	}

	/**
	 * Constructeur du panel contacts
	 * @param fen La fenetre d'origine
	 */
	public PanelContacts(final JFrame fen) {	
		this.setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		MigLayout ml = new MigLayout("insets 0, gapy 0", "", "");
		final Color bg = this.getBackground();
		sp.setBackground(Color.WHITE);
		sp.setLayout(ml);
		final LinkedList<Contact> listContact = UtilisateurManager.getInstance().getConnectedUser().getContact();
		int nbContact = listContact.size();
		
		String old = "Z";
		int j = 0;
		for(int i = 0; i < nbContact; i++) {
			String firstLettre = listContact.get(i).getNom().substring(0, 1);
			if(!(firstLettre.equals(old))){
				PanelLettre pl = new PanelLettre(firstLettre);
				sp.add(pl, "cell 0 " + j + ",alignx left, aligny center");
				j++;
				old = firstLettre;
			}
			final Contact c = listContact.get(i);
			final PanelContact pc = new PanelContact(fen, c);
			pc.setToolTipText("Acc\u00E8der aux informations de " + c.getNom() + ".");
			pc.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelBaseContact m = new PanelBaseContact(fen, c,"Informations");						
		        	  fen.setContentPane(m);
		        	  fen.validate();
		          } 
		          public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						pc.setBackground(bg);
					}	  		
		          public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
						pc.setBackground(Color.WHITE);
					}
		         }); 
			sp.add(pc, "cell 0 " + j + ",alignx center, aligny center");	
			j++;
		}
		
		JScrollPane contentScrollPane = new JScrollPane(sp);
		contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScrollPane.setPreferredSize(new Dimension (440, 405));
		this.add(contentScrollPane, "cell 0 0,alignx center, aligny center");
		
	}

}
