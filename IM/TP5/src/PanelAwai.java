import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;


public class PanelAwai extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAwai(JFrame fen) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
       // this.setOpaque(true);
       // Color aColor = new Color(0xECECEC);
       // this.setBackground(aColor);
		
		JLabel connexion = new JLabel("AWAI", new ImageIcon( "airplane.png"), SwingConstants.CENTER);
	    connexion.setVerticalTextPosition(JLabel.CENTER);
	    connexion.setHorizontalTextPosition(JLabel.LEFT);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(20,4,20,4);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;		
		Font font = new Font("Arial", Font.BOLD, 40);
		connexion.setFont(font);
		this.add(connexion, c);

		
		JLabel imageConnexion = new JLabel( new ImageIcon( "./images/connexion.png"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.insets = new Insets(4,4,4,4);
		c.gridx = 0;
		c.gridy = 1;
		this.add(imageConnexion, c);
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		this.add(imageInscription, c);
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 3;
		this.add(imageApropos, c);
		
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
						String str = "<html><center>Bienvenue sur notre application !<br><br> Pour vous connecter, Appuyer sur le bouton Connexion.<br>"
								+ "Si vous n'êtes pas inscrit, merci de vous inscrire en cliquant sur le bouton Inscription.<br>"
								+ "Pour en savoir davantage sur nous, Veuillez cliquer sur le bouton A Propos.</center></html>";
						PanelAide m = new PanelAide(f, str);	
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
