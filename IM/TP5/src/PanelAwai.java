import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;


public class PanelAwai extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAwai(final JFrame fen) {
		Font font = new Font("Showcard Gothic", Font.BOLD, 40);
		setLayout(new MigLayout("", "[22.00px][][353.00]", "[50px][65.00][33.00][][][43.00][][][]"));
		

		
		JLabel connexion = new JLabel("Awaï", new ImageIcon( "airplane.png"), SwingConstants.CENTER);
		connexion.setVerticalTextPosition(JLabel.CENTER);
		connexion.setHorizontalTextPosition(JLabel.LEFT);
		connexion.setFont(font);
		this.add(connexion, "cell 2 1,alignx center,aligny center");
		
				
				JLabel imageConnexion = new JLabel( new ImageIcon( "./images/connexion.png"));
				this.add(imageConnexion, "cell 2 3,alignx center,aligny center");
				
				imageConnexion.addMouseListener(
						new MouseAdapter(){
						public void mouseClicked (MouseEvent e){
							
								PanelConnexion m = new PanelConnexion(fen);						
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
		
		
				JLabel imageInscription = new JLabel( new ImageIcon( "./images/inscription.png"));
				this.add(imageInscription, "cell 2 4,alignx center,aligny center");
				
				imageInscription.addMouseListener(
						new MouseAdapter(){
						public void mouseClicked (MouseEvent e){
							
								PanelInscription m = new PanelInscription(fen);						
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
		
		JLabel imageApropos = new JLabel( new ImageIcon( "./images/apropos.png"));
		this.add(imageApropos, "cell 2 5,alignx center,aligny center");
		
		imageApropos.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
						PanelApropos m = new PanelApropos(fen);						
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
		
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		this.add(parametre, "cell 0 8,alignx left,aligny center");
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		this.add(aide, "cell 1 8,alignx left,aligny center");
				
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
