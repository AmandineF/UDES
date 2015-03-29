import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelApropos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelApropos(final JFrame fen) {
		setLayout(new MigLayout("", "[50%]0[50%]", "[15%]20[20%]10[20%]0[30%]15[10%]0[25%]"));
		setBounds(0,0,460,500);
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		this.add(retour, "cell 0 0 3 1,alignx left,aligny top");
		retour.addMouseListener(
				new MouseAdapter(){
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
		Font font = new Font("Showcard Gothic", Font.BOLD, 25);
		Color grey = new Color(68,68,68); 

		
		final JLabel imageFrank= new JLabel( new ImageIcon( "./images/frank.png"));
		this.add(imageFrank, "cell 0 1,alignx center,aligny bottom");
		final JLabel texteFrank = new JLabel("Frank");
		final JLabel texteFrank2 = new JLabel("<html><center>21 ans<br> Français <br> Ingénieur</center></html>");
		texteFrank2.setForeground(grey);
		texteFrank2.setFont(new Font("Mockup", Font.BOLD, 20));
		texteFrank.setForeground(grey);
		texteFrank.setFont(font);
		this.add(texteFrank, "cell 0 2,alignx center,aligny bottom");
		this.add(texteFrank2, "cell 0 3,alignx center,aligny center");
		final JLabel imageFrance= new JLabel( new ImageIcon( "./images/france.png"));
		this.add(imageFrance, "cell 0 4,alignx center,aligny bottom");
		
		final JLabel imageAmn= new JLabel( new ImageIcon( "./images/amandine.png"));
		this.add(imageAmn, "cell 1 1,alignx center,aligny bottom");
		final JLabel texteAmn = new JLabel("Amandine");
		final JLabel texteAmn2 = new JLabel("<html><center>20 ans <br> Française <br> Ingénieure</center></html>");
		texteAmn2.setForeground(grey);
		texteAmn2.setFont(new Font("Mockup", Font.BOLD, 20));
		texteAmn.setForeground(grey);
		texteAmn.setFont(font);
		this.add(texteAmn, "cell 1 2,alignx center,aligny bottom");
		this.add(texteAmn2, "cell 1 3,alignx center,aligny center");
		final JLabel imageFrance2= new JLabel( new ImageIcon( "./images/france.png"));
		this.add(imageFrance2, "cell 1 4,alignx center,aligny bottom");
		
  
	}

}
