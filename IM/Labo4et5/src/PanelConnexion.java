import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelConnexion extends JPanel {
	/**
	 * Create the panel.
	 */
	public PanelConnexion(final JFrame fen) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel connexion = new JLabel("Connexion");
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(20,4,20,4);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;		
		Font font = new Font("Arial", Font.BOLD, 20);
		connexion.setFont(font);
		this.add(connexion, c);
		
		JLabel pseudoLabel = new JLabel("Pseudo : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.insets = new Insets(4,4,4,4);
		c.ipady = 5;
		c.gridx = 0;
		c.gridy = 1;
		this.add(pseudoLabel, c);
		
		final JTextField pseudoField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		this.add(pseudoField, c);
		
		JLabel passLabel = new JLabel("Mot de passe : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.ipady = 25;
		c.gridx = 0;
		c.gridy = 2;
		this.add(passLabel, c);
		
		final JPasswordField passField = new JPasswordField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.ipady = 5;
		c.gridx = 1;
		c.gridy = 2;
		this.add(passField, c);
		
		JButton btnAnnuler = new JButton("Quitter");
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
				System.exit(0);
				}}  );      
		
		JButton btnConnexion = new JButton("Connexion");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.insets = new Insets(20,4,20,4);
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 3;
		this.add(btnConnexion, c);
		
		btnConnexion.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					
					String pseudoRes = pseudoField.getText();
					String mdpRes = String.valueOf(passField.getPassword());
					if(EtudiantManager.getInstance().connexion(pseudoRes, mdpRes)){
						PanelTabbed tab = new PanelTabbed(fen, pseudoRes, mdpRes);
						fen.setContentPane(tab);
						fen.setBounds(100, 100, 500, 620);
						fen.validate();
					}else{
						JFrame m = new JFrame();
						m.setTitle("Erreur");
						m.setBounds(100, 100, 400, 120);
						PanelErreur p = new PanelErreur(m);
						m.setContentPane(p);
						m.setVisible(true);
					}
				}}  );
	}

}
