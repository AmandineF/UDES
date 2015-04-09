import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel communication
 * Gere les communications de l'utilisateur
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelCommunication extends JPanel {

	/**
	 * Constructeur du panel de communication
	 * @param fen La fenetre intiale
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
		
		String[] texteTab = {"Salut, ca va ?",
							"Comment s'est pass\u00E9...",
							"Je suis all\u00E9 dans ce...",
							"J'essaie de ne plus...",
							"Pourvu que ca dure...",
							"Je ne sais pas.",
							"A bientot !",
							"Quelle ironie !",
							"Ton pere m'a dit que...",
							"J'espere que nous y ...",
							"T'as vu le chien de Joe ?",
							"Hello :-)",
							"Daniel m'a fait penser...",
							"Je cherche encore.",
							"Super clip ! :-)",
							"Qu'est-il arriv\u00E9 ? ?",
							"La g-m de Daniel est...",
							"Je n'arrive pas a y croire.",
							"Mon sac est chez toi.",
							"J'arrive !",
							":-)",
							"LOL"};
		
		int j = 0;
		for(int i = 0; i < nbContact; i++) {
			final Contact c = listContact.get(i);
			final PanelListe pc = new PanelListe(fen, c, texteTab[i]);
			pc.setToolTipText("Ecrire \u00E0 "+c.getNom());
			pc.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelBase m = new PanelBase(fen, "Messages", c);						
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
