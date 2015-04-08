import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel parametres
 * C'est la ou l'utilisateur peut modifier les parametres de l'application
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelParametres extends JPanel {
	/**
	 * Constructeur du panel parametres
	 * @param fen La fenetre d'origine
	 * @param msg String permettant de savoir quels parametres doivent etre affiches
	 * @param c Le contact pour faire un retour sur une fenetre contact
	 */
	public PanelParametres(final JFrame fen, final String msg, final Contact c) {
		setLayout(new MigLayout("insets 0", "[100%]", "[15%][90%]"));

		String str = "Page des param\u00E8tres " + msg;
		JLabel text = new JLabel(str);
		add(text, "cell 0 1,alignx center,aligny top");
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		Border paddingBorder2 = BorderFactory.createEmptyBorder(5,15,0,0);
		retour.setBorder(paddingBorder2);
		this.add(retour, "cell 0 0,alignx left,aligny center");
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					JPanel m;
					if(msg.equals("du menu")){
						m = new PanelMenu(fen);
					} else if(msg.equals("principaux")) {
						m = new PanelAwai(fen);
					} else if(msg.equals("de la connexion")) {
						m = new PanelConnexion(fen);
					} else if(msg.equals("de l'inscription")) {
						m = new PanelInscription(fen);
					} else if(msg.equals("des contacts")) {
						m = new PanelBaseContact(fen, c, "Informations");
					}else {
						m = new PanelBase(fen, msg, null);	
					}
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
	}

}
