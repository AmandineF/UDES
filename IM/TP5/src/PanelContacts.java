import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelContacts extends JPanel {

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
	 * Create the panel.
	 */
	public PanelContacts() {	
		this.setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		MigLayout ml = new MigLayout("insets 0, gapy 0", "", "");
		final Color bg = this.getBackground();
		sp.setBackground(Color.WHITE);
		sp.setLayout(ml);
		LinkedList<Contact> listContact = UtilisateurManager.getInstance().getConnectedUser().getContact();
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
			
			final PanelContact pc = new PanelContact(listContact.get(i));
			pc.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  //removeAll();  
		        	  //PanelBase m = new PanelBase(fen, "Communication");						
		        	  //fen.setContentPane(m);
		        	  //fen.validate();
		          } 
		          public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
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