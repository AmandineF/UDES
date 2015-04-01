import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelAide extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAide(final JFrame fen, String msg) {
		setLayout(new MigLayout("insets 0", "[100%]", "[10%][90%]"));
		String str = "Aide Default";
		if(msg.equals("awai"))
			str = "<html><center>Aide Awai</center></html>";
		if(msg.equals("connexion"))
			str = "<html><center>Aide Connexion</center></html>";
		if(msg.equals("inscription"))
			str = "<html><center>Aide Inscription</center></html>";
		if(msg.equals("menu"))
			str = "<html><center>Aide Menu</center></html>";
		if(msg.equals("Communication"))
			str = "<html><center>Aide Communication à rédiger</center></html>";
		if(msg.equals("Photos"))
			str = "<html><center>Aide Photos à rédiger</center></html>";
		if(msg.equals("Calendrier"))
			str = "<html><center>Aide Calendrier à rédiger</center></html>";
		if(msg.equals("Dépenses"))
			str = "<html><center>Aide Dépenses à rédiger</center></html>";
		if(msg.equals("Carte"))
			str = "<html><center>Aide Carte à rédiger</center></html>";
		if(msg.equals("Contacts"))
			str = "<html><center>Aide Contacts à rédiger</center></html>";
		if(msg.equals("erreurmail"))
			str = "<html><center>Erreur mail</center></html>";
		if(msg.equals("erreurpseudo"))
			str = "<html><center>Erreur Pseudo</center></html>";
		if(msg.equals("erreurmdp"))
			str = "<html><center>Erreur Mot de passe</center></html>";
		if(msg.equals("inscrit"))
			str = "<html><center>Vous �tes bien inscrit ! Veuillez maintenant vous connecter.</center></html>";
		
		JLabel text = new JLabel(str);
		add(text, "cell 0 1,alignx center,aligny top");
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		Border paddingBorder2 = BorderFactory.createEmptyBorder(10,10,10,10);
		retour.setBorder(paddingBorder2);
		this.add(retour, "cell 0 0,alignx left,aligny top");
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					fen.dispose();
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
