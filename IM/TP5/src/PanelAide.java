import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PanelAide extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAide(JFrame fen, String msg) {
		JLabel text = new JLabel(msg);
		add(text);

		JButton btnFermer = new JButton("Fermer");
		add(btnFermer);
		
		btnFermer.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					fen.dispose();
				}}  ); 
	}

}
