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
 * Gestion du panel contact
 * Affiche un contact de l'utilisateur
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelContact extends JPanel {

	/**
	 * Construction du panel contact
	 * @param fen La fenetre d'origine
	 * @param contact Le contact a afficher
	 */
	public PanelContact(final JFrame fen, final Contact contact) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		final Color bg = getBackground();
		setBackground(Color.WHITE);
		setLayout(new MigLayout("insets 2","9[50]0[130]0[30]0[30]0[30]0[30]0[30]0[20]","[50%][50%]"));
		
		JLabel nomContact = new JLabel(contact.getNom() + " " + contact.getPrenom());
		nomContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(nomContact, "cell 1 0, alignx left, aligny bottom");
		
		JLabel numeroContact = new JLabel(contact.getNumero());
		numeroContact.setFont(new Font("Mockup", Font.PLAIN, 12));
		this.add(numeroContact, "cell 1 1, alignx left, aligny top");
		
		JLabel imageContact = new JLabel(new ImageIcon(contact.getImage()));
		this.add(imageContact, "cell 0 0 0 2, alignx left, aligny center");
		
		JLabel comContact = new JLabel(new ImageIcon("./Images/rondComContact.png"));
		comContact.setToolTipText("Acc\u00E8der aux Communications avec " + contact.getNom() + ".");
		comContact.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  //removeAll();  
	        	  //PanelBase m = new PanelBase(fen, "Communication");						
	        	  //fen.setContentPane(m);
	        	  //fen.validate();
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
		this.add(comContact, "cell 2 0 0 2, alignx right, aligny center");
		
		JLabel phoContact = new JLabel();
		if(contact.getAccesPhoto()){
			phoContact.setIcon(new ImageIcon("./Images/rondPhoContact.png"));
			phoContact.setToolTipText("Acc\u00E8der aux Photos de " + contact.getNom() + ".");
		phoContact.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Photos");						
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
		}else{
			phoContact.setIcon(new ImageIcon("./Images/rondPhoContactGris.png"));
			phoContact.setToolTipText("Vous n'avez pas acc\u00E8s aux Photos de " + contact.getNom() + ".");
		}
		this.add(phoContact, "cell 3 0 0 2, alignx right, aligny center");
		
		JLabel calContact = new JLabel();
		if(contact.getAccesCal()){
			calContact.setIcon(new ImageIcon("./Images/rondCalContact.png"));
			calContact.setToolTipText("Acc\u00E8der au Calendrier de " + contact.getNom() + ".");
			calContact.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Calendrier");						
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
		}else{
			calContact.setIcon(new ImageIcon("./Images/rondCalContactGris.png"));
			calContact.setToolTipText("Vous n'avez pas acc\u00E8s au Calendrier de " + contact.getNom() + ".");
		}
		this.add(calContact, "cell 4 0 0 2, alignx right, aligny center");
		
		JLabel depContact = new JLabel();
		if(contact.getAccesDep()) {
			depContact.setIcon(new ImageIcon("./Images/rondDepContact.png"));
			depContact.setToolTipText("Acc\u00E8der aux D\u00E9penses de " + contact.getNom() + ".");
			depContact.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Dépenses");						
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
		}else{
			depContact.setIcon(new ImageIcon("./Images/rondDepContactGris.png"));
			depContact.setToolTipText("Vous n'avez pas acc\u00E8s aux D\u00E9penses de " + contact.getNom() + ".");
		}
		this.add(depContact, "cell 5 0 0 2, alignx right, aligny center");
		
		JLabel carContact = new JLabel();
		if(contact.getAccesCarte()) {
			carContact.setIcon(new ImageIcon("./Images/rondCarContact.png"));
			carContact.setToolTipText("Acc\u00E8der à la Carte de " + contact.getNom() + ".");
			carContact.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Carte");						
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
		}else{
			carContact.setIcon(new ImageIcon("./Images/rondCarContactGris.png"));
			carContact.setToolTipText("Vous n'avez pas acc\u00E8s à la Carte de " + contact.getNom() + ".");
		}
		this.add(carContact, "cell 6 0 0 2, alignx right, aligny center");
				
	}

}
