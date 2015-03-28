import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PanelAide extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAide(final JFrame fen, String msg) {
		String str = "Aide Default";
		System.out.println(msg);
		if(msg.equals("awai"))
			str = "<html><center>Aide Awai</center></html>";
		if(msg.equals("connexion"))
			str = "<html><center>Aide Connexion</center></html>";
		if(msg.equals("inscription"))
			str = "<html><center>Aide Inscription</center></html>";
		
		
		JLabel text = new JLabel(str);
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
