import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;


public class PanelVideo extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelVideo(final String contact, final JFrame fen) {
		
		setLayout(new MigLayout("", "[20%][40%][20%][20%]", "[10%][1%][68%][1%][20%]"));
		this.setBackground(Color.white);
		
		//Fleche retour
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		Border paddingBorder2 = BorderFactory.createEmptyBorder(10,10,10,10);
		retour.setBorder(paddingBorder2);
		this.add(retour, "cell 0 0,alignx left,aligny top");
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
		        	PanelBase m = new PanelBase(fen, "Communication", "");
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
		JLabel labelContact = new JLabel(contact);
		labelContact.setFont(font);
		add(labelContact, "cell 1 0, alignx center, aligny center");
		
		//bouton appel
		final JLabel btnAppel = new JLabel( new ImageIcon( "./images/btnAppel.png"));
		this.add(btnAppel, "cell 3 0,alignx center,aligny center");

		
		btnAppel.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
		        	PanelBase m = new PanelBase(fen, "Conversation audio", contact);
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
		this.add(decrocher, "cell 0 4 5 1, alignx center");

	}

}
