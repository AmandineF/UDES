import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelContact extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContact(Contact contact) {
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
		
		JLabel phoContact = new JLabel(new ImageIcon("./Images/rondPhoContact.png"));
		phoContact.addMouseListener(new MouseAdapter() { 
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
				}
	         }); 
		this.add(phoContact, "cell 3 0 0 2, alignx right, aligny center");
		
		JLabel calContact = new JLabel(new ImageIcon("./Images/rondCalContact.png"));
		calContact.addMouseListener(new MouseAdapter() { 
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
		this.add(calContact, "cell 4 0 0 2, alignx right, aligny center");
		
		JLabel depContact = new JLabel(new ImageIcon("./Images/rondDepContact.png"));
		depContact.addMouseListener(new MouseAdapter() { 
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
		this.add(depContact, "cell 5 0 0 2, alignx right, aligny center");
		
		JLabel carContact = new JLabel(new ImageIcon("./Images/rondCarContact.png"));
		carContact.addMouseListener(new MouseAdapter() { 
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
		this.add(carContact, "cell 6 0 0 2, alignx right, aligny center");
				
	}

}
