import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel a propos
 * Informations sur les auteurs de l'application
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelApropos extends JPanel {
	/**
	 * Constructeur du panel a propos
	 * @param fen la fenêtre d'origine
	 */
	public PanelApropos(final JFrame fen) {
		//Mise en place d'un migLayout pour disposer les elements sur la fenetre
		setLayout(new MigLayout("", "[33%]0[33%]0[33%]", "[20%]20[20%]10[20%]0[20%]15[20%]"));

		//Mise en place de la fleche de retour en haut a gauche de la fenetre
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		this.add(retour, "cell 0 0 3 1,alignx left,aligny top");
		retour.addMouseListener(
				new MouseAdapter(){
				//Au clic sur la fleche on retourne a la premiere fenetre de l'application
				public void mouseClicked (MouseEvent e){
					PanelAwai m = new PanelAwai(fen);						
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
		
		//Mise en place de la colonne Amandine
		//Mise en place de l'image
		JLabel imageAmandine= new JLabel( new ImageIcon( "./images/amandine.png"));
		this.add(imageAmandine, "cell 0 1,alignx center,aligny bottom");
		//Mise en place du nom
		JLabel texteAmandine = new JLabel("Amandine");
		texteAmandine.setForeground(new Color(68,68,68));
		texteAmandine.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
		this.add(texteAmandine, "cell 0 2,alignx center,aligny bottom");
		//Mise en place de la description
		JLabel descriptionAmandine = new JLabel(new ImageIcon("./Images/france.png"));
		this.add(descriptionAmandine, "cell 0 3,alignx center,aligny center");
		
		//Mise en place de la colonne Laurent
		//Mise en place de l'image
		JLabel imageLaurent= new JLabel( new ImageIcon( "./images/laurent.png"));
		this.add(imageLaurent, "cell 1 1,alignx center,aligny bottom");
		//Mise en place du nom
		JLabel texteLaurent = new JLabel("Laurent");
		texteLaurent.setForeground(new Color(68,68,68));
		texteLaurent.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
		this.add(texteLaurent, "cell 1 2,alignx center,aligny bottom");
		//Mise en place de la description
		JLabel descriptionLaurent = new JLabel(new ImageIcon("./Images/canada.png"));
		this.add(descriptionLaurent, "cell 1 3,alignx center,aligny center");
		
				//Mise en place de la colonne Frank
		//Mise en place de l'image
		JLabel imageFrank= new JLabel( new ImageIcon( "./images/frank.png"));
		this.add(imageFrank, "cell 2 1,alignx center,aligny bottom");
		//Mise en place du nom
		JLabel texteFrank = new JLabel("Frank");
		texteFrank.setForeground(new Color(68,68,68));
		texteFrank.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
		this.add(texteFrank, "cell 2 2,alignx center,aligny bottom");
		//Mise en place de la description
		JLabel descriptionFrank = new JLabel(new ImageIcon("./Images/france.png"));
		this.add(descriptionFrank, "cell 2 3,alignx center,aligny center");

	}
}
