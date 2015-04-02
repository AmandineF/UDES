import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;


public class PanelChat extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelChat(String contact, JFrame fen) {
		setLayout(new MigLayout("", "[20%][40%][20%][20%]", "[10%][1%][68%][1%][20%]"));
		
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
		
		JLabel labeTest = new JLabel(contact);
		add(labeTest, "cell 1 0, alignx center, aligny center");
	}

}
