import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel menu
 * Menu principal de l'application
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelMenu extends JPanel {

	/**
	 * Constructeur du panel menu
	 * @param fen La fenetre d'origine
	 */
	public PanelMenu(final JFrame fen) {
		
		setLayout(new MigLayout("", "[10%]0[28%]0[28%]0[28%]0[8%]", "[100]0[130]0[10]0[130]0[10]0[100]"));
		setBounds(0, 0, 400, 460);

		//Titre
		JLabel connexion = new JLabel("Awa\u00EF");
		Font font = new Font("Showcard Gothic", Font.BOLD, 40);
		Color grey = new Color(68,68,68); 
		connexion.setForeground(grey); 
		connexion.setFont(font);
		JLabel connexionImg = new JLabel(new ImageIcon( "./images/airplane.png"));
		this.add(connexion, "cell 0 0 5 1,alignx center,aligny center");
		this.add(connexionImg, "cell 0 0 5 1,alignx center,aligny center");
		
		//Communication
		final JLabel imageComm = new JLabel( new ImageIcon( "./images/grosRondComm.png"));
		this.add(imageComm, "cell 1 1,alignx center,aligny bottom");
		final JLabel texteComm = new JLabel("Communication");
		this.add(texteComm, "cell 1 2,alignx center,aligny top");
		texteComm.setFont(new Font("Mockup", Font.PLAIN, 10));
		
		imageComm.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){						
						PanelBase m = new PanelBase(fen, "Communication", null);						
						fen.setContentPane(m);
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
				});
		
		//Photo
		final JLabel imagePhoto = new JLabel( new ImageIcon( "./images/grosRondPhoto.png"));
		this.add(imagePhoto, "cell 2 1,alignx center,aligny bottom");
		final JLabel textePhoto = new JLabel("Photos");
		this.add(textePhoto, "cell 2 2,alignx center,aligny top");
		textePhoto.setFont(new Font("Mockup", Font.PLAIN, 10));
		
		//Calendrier
		final JLabel imageCal = new JLabel( new ImageIcon( "./images/grosRondCal.png"));
		this.add(imageCal, "cell 3 1,alignx center,aligny bottom");
		final JLabel texteCal = new JLabel("Calendrier");
		this.add(texteCal, "cell 3 2,alignx center,aligny top");
		texteCal.setFont(new Font("Mockup", Font.PLAIN, 10));
		
		//Depenses
		final JLabel imageDep = new JLabel( new ImageIcon( "./images/grosRondDep.png"));
		this.add(imageDep, "cell 1 3,alignx center,aligny bottom");
		final JLabel texteDep = new JLabel("D\u00E9penses");
		this.add(texteDep, "cell 1 4,alignx center,aligny top");
		texteDep.setFont(new Font("Mockup", Font.PLAIN, 10));
		
		//Carte
		final JLabel imageCarte = new JLabel( new ImageIcon( "./images/grosRondCarte.png"));
		this.add(imageCarte, "cell 2 3,alignx center,aligny bottom");
		final JLabel texteCarte = new JLabel("Carte");
		this.add(texteCarte, "cell 2 4,alignx center,aligny top");
		texteCarte.setFont(new Font("Mockup", Font.PLAIN, 10));
		
		//Contacts
		final JLabel imageContact= new JLabel( new ImageIcon( "./images/grosRondContact.png"));
		this.add(imageContact, "cell 3 3,alignx center,aligny bottom");
		final JLabel texteContact = new JLabel("Contacts");
		this.add(texteContact, "cell 3 4,alignx center,aligny top");
		texteContact.setFont(new Font("Mockup", Font.PLAIN, 10));
		imageContact.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){	
						PanelBase m = new PanelBase(fen, "Contacts", null);						
						fen.setContentPane(m);
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
						PanelBase m = new PanelBase(fen, "Carte", null);						
						fen.setContentPane(m);
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
						PanelBase m = new PanelBase(fen, "D\u00E9penses", null);						
						fen.setContentPane(m);
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
						PanelBase m = new PanelBase(fen, "Calendrier", null);						
						fen.setContentPane(m);	
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
						PanelBase m = new PanelBase(fen, "Photos", null);						
						fen.setContentPane(m);
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
				JPanel paramAide = new JPanel(new MigLayout("", "[20][20][20][340]", "[100%]"));
				this.add(paramAide, "cell 0 5 5 1, alignx center, aligny bottom");
				JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
				parametre.setToolTipText("Param\u00E8tres");
				parametre.addMouseListener(new MouseAdapter() { 
			         public void mousePressed(MouseEvent me) { 
			        	 PanelParametres m = new PanelParametres(fen, "du menu", null);	
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
				paramAide.add(parametre,"cell 0 0, alignx center, aligny bottom");
				
				JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
				aide.setToolTipText("Aide");
				paramAide.add(aide,"cell 1 0, alignx center, aligny bottom");
						
				aide.addMouseListener(
						new MouseAdapter(){
						public void mouseClicked (MouseEvent e){
							JFrame f = new JFrame();
							PanelAide m = new PanelAide(f, "menu");	
							f.setContentPane(m);
							f.setBounds(100, 100, 500, 300);
							f.setVisible(true);
					
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
				}  );
				
				JLabel deco = new JLabel(new ImageIcon("./Images/deco.png"));
		  		deco.setToolTipText("D\u00E9connexion");
		  		deco.addMouseListener(new MouseAdapter() { 
			          public void mousePressed(MouseEvent me) { 
			        	   JFrame decoFrame = new JFrame();	
			        	   decoFrame.setBounds(0, 0, 460, 200);
			        	   decoFrame.setVisible(true);
			        	   decoFrame.setResizable(false);
						   PanelDeconnexion m = new PanelDeconnexion(fen,decoFrame);
			        	   decoFrame.setContentPane(m);
			            } 
				       public void mouseEntered(MouseEvent e) {
								setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
							}
				  		public void mouseExited(MouseEvent e) {
							setCursor(Cursor.getDefaultCursor());
						}
				          });
		  		paramAide.add(deco,"cell 2 0, alignx center, aligny bottom");
	}

}
