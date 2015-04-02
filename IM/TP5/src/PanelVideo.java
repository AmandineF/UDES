import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;


public class PanelVideo extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelVideo(String contact, JFrame fen) {
		
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
		
		JLabel labeTest = new JLabel(contact+" Video");
		add(labeTest);
	}

}
