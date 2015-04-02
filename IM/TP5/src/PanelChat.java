import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


public class PanelChat extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelChat(String contact, final JFrame fen) {
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
		
		
		//bouton video
		final JLabel btnVideo = new JLabel( new ImageIcon( "./images/btnVideo.png"));
		this.add(btnVideo, "cell 4 0,alignx left,aligny center");
		

		
		btnVideo.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
		        	PanelBase m = new PanelBase(fen, "Conversation video", contact);
		        	fen.setContentPane(m);
		        	fen.validate();
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnVideo.setIcon(new ImageIcon( "./images/btnVideoHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnVideo.setIcon(new ImageIcon( "./images/btnVideo.png"));
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
		final JLabel imageChat = new JLabel( new ImageIcon( "./images/chat.png"));
		this.add(imageChat, "cell 0 2 5 1, growx");
		
		//Barre noire 2
		JPanel barreNoireH2 = new JPanel();
		FlowLayout flowLayout2 = (FlowLayout) barreNoireH2.getLayout();
		flowLayout2.setVgap(0);
		flowLayout2.setHgap(0);
		barreNoireH2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		add(barreNoireH2, "cell 0 3 5 1,growx");
		
		class CustomeBorder extends AbstractBorder{
	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y,
	                int width, int height) {
	            // TODO Auto-generated method stubs
	            super.paintBorder(c, g, x, y, width, height);
	            Graphics2D g2d = (Graphics2D)g;
	            g2d.setStroke(new BasicStroke(12));
	            //Color col = new Color(0xECECEC);
	            g2d.setColor(Color.white);
	            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
	        }   
	    }
		
		//Textfield Entrée de texte
		final JTextField message = new JTextField(30);
		TextPrompt tp = new TextPrompt("Texte a envoyer...", message);
		message.setPreferredSize(new Dimension(200, 60));
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		message.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(message, "cell 0 4 4 1, growx");
		
		//Bouton d'envoi
		final JLabel btnEnvoi = new JLabel( new ImageIcon( "./images/btnEnvoi.png"));
		this.add(btnEnvoi);
		btnEnvoi.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
		});     
	}

}
