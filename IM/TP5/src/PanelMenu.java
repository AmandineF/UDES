import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class PanelMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelMenu(JFrame fen) {
		Font font = new Font("Showcard Gothic", Font.BOLD, 40);
		setLayout(new MigLayout("", "[17.00px][32.00][91.00][148.00][138.00]", "[50px][65.00][33.00][][][43.00][]"));
		

		//titre
		JLabel connexion = new JLabel("Awaï", new ImageIcon( "airplane.png"), SwingConstants.CENTER);
		connexion.setVerticalTextPosition(JLabel.CENTER);
		connexion.setHorizontalTextPosition(JLabel.LEFT);
		connexion.setFont(font);
		this.add(connexion, "cell 3 1,alignx center,aligny center");
		
		//Communication
		JLabel imageComm = new JLabel( new ImageIcon( "./images/grosRondComm.png"));
		this.add(imageComm, "cell 2 3,alignx center,aligny center");
		
		imageComm.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
						PanelBase m = new PanelBase(fen, "Communication");						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					imageComm.setIcon(new ImageIcon( "./images/grosRondCommHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					imageComm.setIcon( new ImageIcon( "./images/grosRondComm.png"));
				}
				}  );
		
		//Photo
		JLabel imagePhoto = new JLabel( new ImageIcon( "./images/grosRondPhoto.png"));
		this.add(imagePhoto, "cell 3 3,alignx center,aligny center");
		
		//Calendrier
		JLabel imageCal = new JLabel( new ImageIcon( "./images/grosRondCal.png"));
		this.add(imageCal, "cell 4 3,alignx center,aligny center");
		
		//Depenses
		JLabel imageDep = new JLabel( new ImageIcon( "./images/grosRondDep.png"));
		this.add(imageDep, "cell 2 4,alignx center,aligny center");
		
		//Carte
		JLabel imageCarte = new JLabel( new ImageIcon( "./images/grosRondCarte.png"));
		this.add(imageCarte, "cell 3 4,alignx center,aligny center");
		
		//Contacts
		JLabel imageContact= new JLabel( new ImageIcon( "./images/grosRondContact.png"));
		this.add(imageContact, "cell 4 4,alignx center,aligny center");
		
		imageContact.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
						PanelBase m = new PanelBase(fen, "Contact");						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					imageContact.setIcon(new ImageIcon( "./images/grosRondContactHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					imageContact.setIcon(new ImageIcon( "./images/grosRondContact.png"));
				}
				}  );
		
		imageCarte.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
						PanelBase m = new PanelBase(fen, "Carte");						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					imageCarte.setIcon(new ImageIcon( "./images/grosRondCarteHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					imageCarte.setIcon(new ImageIcon( "./images/grosRondCarte.png"));
				}
				}  );
		
		imageDep.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
						PanelBase m = new PanelBase(fen, "Depense");						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					imageDep.setIcon(new ImageIcon( "./images/grosRondDepHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					imageDep.setIcon(new ImageIcon( "./images/grosRondDep.png"));
				}
				}  );
		
		imageCal.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
						PanelBase m = new PanelBase(fen, "Calendrier");						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					imageCal.setIcon(new ImageIcon( "./images/grosRondCalHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					imageCal.setIcon(new ImageIcon( "./images/grosRondCal.png"));
				}
				}  );
		
		imagePhoto.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
						PanelBase m = new PanelBase(fen, "Photo");						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					imagePhoto.setIcon(new ImageIcon( "./images/grosRondPhotoHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					imagePhoto.setIcon(new ImageIcon( "./images/grosRondPhoto.png"));
				}
				}  );
		
		//Parametre + Aide
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		this.add(parametre, "cell 0 6");
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		this.add(aide, "cell 1 6");
				
		aide.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					JFrame f = new JFrame();
					PanelAide m = new PanelAide(f, "menu");	
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
