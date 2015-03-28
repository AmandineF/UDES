import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;


public class PanelInscription extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelInscription(final JFrame fen) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.ipadx = 15;
		c.ipady = 12;
		c.gridx = 0;
		c.gridy = 0;
		this.add(retour, c);
		
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelAwai m = new PanelAwai(fen);						
					fen.setContentPane(m);
					fen.setBounds(100, 100, 500, 500);
					fen.validate();
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
				}  ); 
		
	    class CustomeBorder extends AbstractBorder{
	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y,
	                int width, int height) {
	            // TODO Auto-generated method stubs
	            super.paintBorder(c, g, x, y, width, height);
	            Graphics2D g2d = (Graphics2D)g;
	            g2d.setStroke(new BasicStroke(12));
	            Color col = new Color(0xECECEC);
	            g2d.setColor(col);
	            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
	        }   
	    }
		
		final JTextField pseudoField = new JTextField();
		TextPrompt tp = new TextPrompt("Pseudo", pseudoField);
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		pseudoField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 3;
		c.gridy = 1;
		this.add(pseudoField, c);
		

		
		final JPasswordField passField = new JPasswordField();
		TextPrompt tp2 = new TextPrompt("Mot de Passe", passField);
		tp2.setForeground( Color.GRAY );
		tp2.changeStyle(Font.ITALIC);
		tp2.setShow(TextPrompt.Show.ALWAYS);
		passField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 2;
		this.add(passField, c);

		JLabel btnInscription = new JLabel( new ImageIcon( "./images/inscription.png"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 3;
		this.add(btnInscription, c);
		
		btnInscription.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
				
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
				}  );
		
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		this.add(parametre, c);
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		this.add(aide, c);
		
		aide.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						JFrame f = new JFrame();
				PanelAide m = new PanelAide(f, "inscription");	
				f.setContentPane(m);
				f.setBounds(100, 100, 500, 180);
				f.setVisible(true);
			
			}
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
		}
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getDefaultCursor());
		}
		}  );
	}

}
