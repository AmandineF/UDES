import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelCommunication extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCommunication(final JFrame fen) {	
		this.setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		MigLayout ml = new MigLayout("insets 0, gapy 0", "", "");
		final Color bg = this.getBackground();
		sp.setBackground(Color.WHITE);
		sp.setLayout(ml);
		final LinkedList<Contact> listContact = UtilisateurManager.getInstance().getConnectedUser().getContact();
		int nbContact = listContact.size();
		
		int j = 0;
		for(int i = 0; i < nbContact; i++) {
			final Contact c = listContact.get(i);
			final PanelListe pc = new PanelListe(fen, c);
			pc.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelBase m = new PanelBase(fen, "Messages", c.getPrenom());						
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
