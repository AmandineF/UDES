import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel info contact
 * Affichage d'une fiche d'information sur un contact
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelInfoContact extends JPanel {
	/**
	 * Constructeur du panel info contact
	 * @param contact Le contact dont on veut les informations
	 */
	public PanelInfoContact(final JFrame fen, final Contact contact) {
		   setLayout(new MigLayout("insets 0, gapy 0","[20][350]","[10][40]9[40]9[40]9[40]9[40]9[40]9[40]"));
		   
		   JPanel infoNumero = new JPanel();
		   infoNumero.setLayout(new MigLayout("insets 0","[220][30][30][30]",""));
		   add(infoNumero, "cell 1 1, alignx left, aligny center");
		   
		   JLabel txtNumero = new JLabel("Num\u00E9ro de t\u00E9l\u00E9hone - "+ contact.getNumero());
		   txtNumero.setFont(new Font("Mockup", Font.PLAIN, 12));
		   infoNumero.add(txtNumero,"cell 0 0,alignx left, aligny center");
		   final JLabel imgCom = new JLabel(new ImageIcon("./Images/btnChat.png"));
		   infoNumero.add(imgCom,"cell 1 0,alignx center, aligny center");
			imgCom.addMouseListener(
					new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
						PanelBase m = new PanelBase(fen, "Messages", contact);
			        	fen.setContentPane(m);
			        	fen.validate();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
						imgCom.setIcon(new ImageIcon( "./images/btnChatHover.png"));
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
						imgCom.setIcon(new ImageIcon( "./images/btnChat.png"));
					}
			});  
		   final JLabel imgAppel = new JLabel(new ImageIcon("./Images/btnAppel.png"));
		   infoNumero.add(imgAppel,"cell 2 0,alignx center, aligny center");
			imgAppel.addMouseListener(
					new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
						PanelAudio m = new PanelAudio(contact, fen);
			        	fen.setContentPane(m);
			        	fen.validate();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
						imgAppel.setIcon(new ImageIcon( "./images/btnAppelHover.png"));
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
						imgAppel.setIcon(new ImageIcon( "./images/btnAppel.png"));
					}
			}); 
		   final JLabel imgVideo = new JLabel(new ImageIcon("./Images/btnVideo.png"));
		   infoNumero.add(imgVideo,"cell 3 0,alignx center, aligny center");
		   imgVideo.addMouseListener(
					new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
			        	PanelVideo m = new PanelVideo(contact, fen);
			        	fen.setContentPane(m);
			        	fen.validate();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
						imgVideo.setIcon(new ImageIcon( "./images/btnVideoHover.png"));
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
						imgVideo.setIcon(new ImageIcon( "./images/btnVideo.png"));
					}
			}); 
		   
		   JLabel date = new JLabel("Date de naissance - 21 juillet 1994, 20 ans");
		   add(date, "cell 1 2, alignx left, aligny center");
		   date.setFont(new Font("Mockup", Font.PLAIN, 12));
		   
		   JLabel sexe = new JLabel("Sexe - Femme");
		   sexe.setFont(new Font("Mockup", Font.PLAIN, 12));
		   add(sexe,"cell 1 3,alignx left, aligny center");
		   
		   JLabel habite = new JLabel("Habite a� - Sherbrooke, Canada");
		   add(habite, "cell 1 4, alignx left, aligny center");
		   habite.setFont(new Font("Mockup", Font.PLAIN, 12));
		   
		   JLabel origine = new JLabel("De - Rennes, France");
		   origine.setFont(new Font("Mockup", Font.PLAIN, 12));
		   add(origine,"cell 1 5,alignx left, aligny center");
		   
		   JLabel mail = new JLabel("Adresse \u00E9lectronique - " + contact.getNom(). toLowerCase() + contact.getPrenom().toLowerCase()  + "@gmail.com");
		   mail.setFont(new Font("Mockup", Font.PLAIN, 12));
		   add(mail,"cell 1 6,alignx left, aligny center");	
		   
		   JLabel emploi = new JLabel("Emploi - \u00C9tudiante");
		   emploi.setFont(new Font("Mockup", Font.PLAIN, 12));
		   add(emploi,"cell 1 7,alignx left, aligny center");
		   
	}

}
