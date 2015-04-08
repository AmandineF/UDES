import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel video pour simuler un appel video entre l'utilisateur et un contact
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelVideo extends JPanel {

	/**
	 * Constructeur du panel video
	 * @param contact Le contact que l'utilisateur appelle
	 * @param fen La fenetre d'origine
	 */
	public PanelVideo(final Contact contact, final JFrame fen) {
		
		setLayout(new MigLayout("", "[20%][40%][20%][20%]", "[10%][1%][68%][1%][20%]"));
		
		//Fleche retour
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		retour.setToolTipText("Retour \u00E0 la liste des communications");
		Border paddingBorder2 = BorderFactory.createEmptyBorder(10,10,10,10);
		retour.setBorder(paddingBorder2);
		this.add(retour, "cell 0 0,alignx left,aligny top");
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
		        	PanelBase m = new PanelBase(fen, "Communication", null);
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
		
		//Nom contact
		Font font = new Font("Mockup", Font.BOLD, 20);
		JLabel labelContact = new JLabel(contact.getNom());
		labelContact.setToolTipText("Acc\u00E9der \u00E0 la fiche de "+contact.getNom());
		labelContact.setFont(font);
		add(labelContact, "cell 1 0, alignx center, aligny center");
		labelContact.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelBaseContact p = new PanelBaseContact(fen, contact, "Informations");
					fen.setContentPane(p);
					fen.validate();
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
		});
		
		//bouton appel
		final JLabel btnAppel = new JLabel( new ImageIcon( "./images/btnAppel.png"));
		btnAppel.setToolTipText("Appeler "+contact.getNom());
		this.add(btnAppel, "cell 3 0,alignx center,aligny center");

		
		btnAppel.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelAudio m = new PanelAudio(contact, fen);
		        	fen.setContentPane(m);
		        	fen.validate();
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnAppel.setIcon(new ImageIcon( "./images/btnAppelHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnAppel.setIcon(new ImageIcon( "./images/btnAppel.png"));
				}
				}  );
		
		
		//bouton Chat
		final JLabel btnChat = new JLabel( new ImageIcon( "./images/btnChat.png"));
		btnChat.setToolTipText("Ecrire \u00E0 "+contact.getNom());
		this.add(btnChat, "cell 4 0,alignx left,aligny center");
		

		
		btnChat.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
		        	PanelBase m = new PanelBase(fen, "Messages", contact);
		        	fen.setContentPane(m);
		        	fen.validate();
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnChat.setIcon(new ImageIcon( "./images/btnChatHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnChat.setIcon(new ImageIcon( "./images/btnChat.png"));
				}
				}  );
		
		//Barre noire 1
		JPanel barreNoireH = new JPanel();
		FlowLayout flowLayout = (FlowLayout) barreNoireH.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		barreNoireH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		add(barreNoireH, "cell 0 1 5 1,growx");
		
		//Image conversation
		final JLabel imageChat = new JLabel( new ImageIcon( "./images/imgAppel.png"));
		this.add(imageChat, "cell 0 2 5 1, growx");

		
		//Bouton Appel Video
		final JLabel decrocher = new JLabel( new ImageIcon( "./images/videoOn.png"));
		decrocher.setToolTipText("Accepter/D\u00E9cliner l'appel vid\u00E9o");
		this.add(decrocher, "cell 0 4 5 1, alignx center");
		decrocher.addMouseListener(
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

	}

}
