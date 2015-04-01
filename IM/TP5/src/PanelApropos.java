import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel a propos
 * @author Amandine Fouillet - Frank Chassing
 *
 */
@SuppressWarnings("serial")
public class PanelApropos extends JPanel {
	/**
	 * Constructeur du panel a propos
	 * @param fen la fenêtre initiale
	 */
	public PanelApropos(final JFrame fen) {
		//Mise en place d'un migLayout pour disposer les elements sur la fenetre
		setLayout(new MigLayout("", "[50%]0[50%]", "[15%]20[20%]10[20%]0[30%]15[10%]0[25%]"));

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
		this.add(imageAmandine, "cell 1 1,alignx center,aligny bottom");
		//Mise en place du nom
		JLabel texteAmandine = new JLabel("Amandine");
		texteAmandine.setForeground(new Color(68,68,68));
		texteAmandine.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
		this.add(texteAmandine, "cell 1 2,alignx center,aligny bottom");
		//Mise en place de la description
		JLabel descriptionAmandine = new JLabel("<html><center>20 ans <br> Française <br> Ingénieure</center></html>");
		descriptionAmandine.setForeground(new Color(68,68,68));
		descriptionAmandine.setFont(new Font("Mockup", Font.BOLD, 20));
		this.add(descriptionAmandine, "cell 1 3,alignx center,aligny center");
		
		//Mise en place de la colonne Frank
		//Mise en place de l'image
		JLabel imageFrank= new JLabel( new ImageIcon( "./images/frank.png"));
		this.add(imageFrank, "cell 0 1,alignx center,aligny bottom");
		//Mise en place du nom
		JLabel texteFrank = new JLabel("Frank");
		texteFrank.setForeground(new Color(68,68,68));
		texteFrank.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
		this.add(texteFrank, "cell 0 2,alignx center,aligny bottom");
		//Mise en place de la description
		JLabel descriptionFrank = new JLabel("<html><center>21 ans<br> Français <br> Ingénieur</center></html>");
		descriptionFrank.setForeground(new Color(68,68,68));
		descriptionFrank.setFont(new Font("Mockup", Font.BOLD, 20));
		this.add(descriptionFrank, "cell 0 3,alignx center,aligny center");

	}
}
