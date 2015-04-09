import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel liste
 * Permet de creer le panel pour un echange de message avec un contact
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelListe extends JPanel {

	/**
	 * Constructeur du panel liste
	 * @param fen La fenetre d'origine
	 * @param contact Le contact avec qui l'utilisateur echange des messages 
	 * @param texte Le dernier message echange
	 */
	public PanelListe(final JFrame fen, final Contact contact, String texte) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		setBackground(Color.WHITE);
		setLayout(new MigLayout("insets 2","9[50]0[150]0[60]0[30]0[30]0[60]","[50%][50%]"));
		
		JLabel nomContact = new JLabel(contact.getNom() + " " + contact.getPrenom());
		nomContact.setFont(new Font("Mockup", Font.BOLD, 12));
		this.add(nomContact, "cell 1 0, alignx left, aligny bottom");
				
		JLabel texteContact = new JLabel(texte);
		texteContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(texteContact, "cell 1 1, alignx left, aligny top");
		
		JLabel imageContact = new JLabel(new ImageIcon(contact.getImage()));
		this.add(imageContact, "cell 0 0 0 2, alignx left, aligny center");
		
		JLabel btnAppel = new JLabel(new ImageIcon("./Images/btnAppel.png"));
		btnAppel.setToolTipText("Appeler  " + contact.getNom() + ".");
		btnAppel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelAudio m = new PanelAudio(contact, fen);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	          } 
	          public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}	  		
	          public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					setBackground(Color.WHITE);
				}
	         }); 
		this.add(btnAppel, "cell 3 0 0 2, alignx left, aligny center");
		
		JLabel btnVideo = new JLabel();
		
		btnVideo.setIcon(new ImageIcon("./Images/btnVideo.png"));
		btnVideo.setToolTipText("Faire un appel video avec  " + contact.getNom() + ".");
		btnVideo.addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) { 
        	  removeAll();  
        	  PanelVideo m = new PanelVideo(contact, fen);						
        	  fen.setContentPane(m);
        	  fen.validate();
          } 
          public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}	  		
          public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
         }); 
		
		
		this.add(btnVideo, "cell 4 0 0 2, alignx left, aligny center");
		
		
	}

}
