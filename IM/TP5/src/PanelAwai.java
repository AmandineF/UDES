import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;


public class PanelAwai extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAwai(final JFrame fen) {
		
		setLayout(new MigLayout("", "[25%]0[50%]0[25%]", "[14%][13%][13%][2%][13%][2%][13%][18%][10%]"));
		

		
		JLabel titre = new JLabel("Awaï", new ImageIcon( "airplane.png"), SwingConstants.CENTER);
		JLabel connexionImg = new JLabel(new ImageIcon( "./images/airplane.png"));
		Font font = new Font("Showcard Gothic", Font.BOLD, 40);
		Color grey = new Color(68,68,68); 
		titre.setForeground(grey);
		titre.setFont(font);
		this.add(titre, "cell 0 0 3 1,alignx center,aligny bottom");
		this.add(connexionImg, "cell 0 0 3 1,alignx center,aligny bottom");
		
				
		JLabel imageConnexion = new JLabel( new ImageIcon( "./images/connexion.png"));
		this.add(imageConnexion, "cell 1 2,alignx center,aligny center");
		
		imageConnexion.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						PanelConnexion m = new PanelConnexion(fen);						
						fen.setContentPane(m);
						fen.validate();
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					//imageConnexion.setIcon(new ImageIcon( "./images/connexionHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					//imageConnexion.setIcon(new ImageIcon( "./images/connexion.png"));
				}
				}  );
		
		
		JLabel imageInscription = new JLabel( new ImageIcon( "./images/inscription.png"));
		this.add(imageInscription, "cell 1 4,alignx center,aligny center");
		
		imageInscription.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						PanelInscription m = new PanelInscription(fen);						
						fen.setContentPane(m);
						fen.validate();
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					//imageInscription.setIcon(new ImageIcon( "./images/inscriptionHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					//imageInscription.setIcon(new ImageIcon( "./images/inscription.png"));
				}
				}  );

		JLabel imageApropos = new JLabel( new ImageIcon( "./images/apropos.png"));
		this.add(imageApropos, "cell 1 6,alignx center,aligny center");
		
		imageApropos.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						PanelApropos m = new PanelApropos(fen);						
						fen.setContentPane(m);
						fen.validate();
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					//imageApropos.setIcon(new ImageIcon( "./images/aproposHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					//imageApropos.setIcon(new ImageIcon( "./images/apropos.png"));
				}
				}  );
		
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		Border paddingBorder2 = BorderFactory.createEmptyBorder(0,0,0,10);
		parametre.setBorder(paddingBorder2);
		this.add(parametre, "cell 0 8,alignx left,aligny center");
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		this.add(aide, "cell 0 8,alignx left,aligny center");
				
		aide.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					JFrame f = new JFrame();
					PanelAide m = new PanelAide(f, "awai");	
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
