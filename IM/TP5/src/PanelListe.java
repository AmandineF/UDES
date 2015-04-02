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


@SuppressWarnings("serial")
public class PanelListe extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelListe(final JFrame fen, final Contact contact) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		final Color bg = getBackground();
		setBackground(Color.WHITE);
		setLayout(new MigLayout("insets 2","9[50]0[150]0[60]0[60]0[60]","[50%][50%]"));
		
		JLabel nomContact = new JLabel(contact.getNom() + " " + contact.getPrenom());
		nomContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(nomContact, "cell 1 0, alignx left, aligny bottom");
		
		/*JLabel numeroContact = new JLabel(contact.getNumero());
		numeroContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(numeroContact, "cell 1 1, alignx left, aligny top");*/
		
		JLabel texteContact = new JLabel("texte...");
		this.add(texteContact, "cell 1 1, alignx left, aligny top");
		
		JLabel imageContact = new JLabel(new ImageIcon(contact.getImage()));
		this.add(imageContact, "cell 0 0 0 2, alignx left, aligny center");
		
		JLabel btnAppel = new JLabel(new ImageIcon("./Images/btnAppel.png"));
		btnAppel.setToolTipText("Acc\u00E8der aux Communications avec " + contact.getNom() + ".");
		btnAppel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Conversation audio", contact.getPrenom());						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	          } 
	          public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					setBackground(bg);
				}	  		
	          public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					setBackground(Color.WHITE);
				}
	         }); 
		this.add(btnAppel, "cell 2 0 0 2, alignx right, aligny center");
		
		JLabel btnVideo = new JLabel();
		
		btnVideo.setIcon(new ImageIcon("./Images/btnVideo.png"));
		btnVideo.addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) { 
        	  removeAll();  
        	  PanelBase m = new PanelBase(fen, "Conversation video", contact.getPrenom());						
        	  fen.setContentPane(m);
        	  fen.validate();
          } 
          public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				setBackground(bg);
			}	  		
          public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
         }); 
		
		
		this.add(btnVideo, "cell 3 0 0 2, alignx right, aligny center");
		
		
	}

}
