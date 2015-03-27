import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PanelApropos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelApropos(JFrame fen) {
		
		String str = "<html><center>Coucou c'est nous !</center></html>";
		JLabel text = new JLabel(str);
		add(text);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton btnAnnuler = new JButton("Retour");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.insets = new Insets(20,4,20,4);
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		this.add(btnAnnuler, c);
		
		btnAnnuler.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					PanelAwai m = new PanelAwai(fen);						
					fen.setContentPane(m);
					fen.setBounds(100, 100, 500, 500);
					fen.validate();
				}}  );  
	}

}
