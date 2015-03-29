import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelInscription extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelInscription(final JFrame fen) {
		setLayout(new MigLayout("", "[20%]0[60%]0[20%]", "[15%]0[15%]0[15%]0[15%]2[15%]0[16%]"));
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		this.add(retour,  "cell 0 0 3 1,alignx left,aligny top");
		
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelAwai m = new PanelAwai(fen);						
					fen.setContentPane(m);
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
		
		final JTextField pseudoField = new JTextField(30);
		TextPrompt tp = new TextPrompt("Pseudo", pseudoField);
		pseudoField.setPreferredSize(new Dimension(200, 60));
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		pseudoField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(pseudoField, "cell 1 1,alignx center,aligny top");
		
		final JPasswordField passField = new JPasswordField(30);
		passField.setPreferredSize(new Dimension(200, 60));
		TextPrompt tp2 = new TextPrompt("Mot de Passe", passField);
		tp2.setForeground( Color.GRAY );
		tp2.changeStyle(Font.ITALIC);
		tp2.setShow(TextPrompt.Show.ALWAYS);
		passField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(passField, "cell 1 2,alignx center,aligny top");
		
		final JPasswordField mailField = new JPasswordField(30);
		mailField.setPreferredSize(new Dimension(200, 60));
		TextPrompt tp3 = new TextPrompt("Addresse mail", mailField);
		tp3.setForeground( Color.GRAY );
		tp3.changeStyle(Font.ITALIC);
		tp3.setShow(TextPrompt.Show.ALWAYS);
		mailField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(mailField, "cell 1 3,alignx center,aligny top");

		JLabel btnInscription = new JLabel( new ImageIcon( "./images/inscription.png"));
		this.add(btnInscription, "cell 1 4,alignx center,aligny top");
		
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
		parametre.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 PanelParametres m = new PanelParametres(fen, "de l'inscription");	
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
		Border paddingBorder2 = BorderFactory.createEmptyBorder(0,0,0,10);
		parametre.setBorder(paddingBorder2);
		this.add(parametre,  "cell 0 5,alignx trailing,aligny bottom");
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		this.add(aide,  "cell 0 5,alignx trailing,aligny bottom");
		
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
