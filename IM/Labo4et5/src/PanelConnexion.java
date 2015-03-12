import java.awt.*;
import java.awt.event.*;

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
	public PanelConnexion(JFrame fen) {
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
		
		JTextField pseudoField = new JTextField();
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
		
		JPasswordField passField = new JPasswordField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.ipady = 5;
		c.gridx = 1;
		c.gridy = 2;
		this.add(passField, c);
		
		
		
		JButton btnAnnuler = new JButton("Annuler");
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
					System.out.println(pseudoRes);
					System.out.println(mdpRes);
					System.out.println(EtudiantManager.getInstance().connexion(pseudoRes, mdpRes));
					if(EtudiantManager.getInstance().connexion(pseudoRes, mdpRes)){
						PanelTabbed tab = new PanelTabbed(pseudoRes, mdpRes);
						fen.setContentPane(tab);
						fen.setBounds(100, 100, 1200, 500);
						fen.revalidate();
					}else{
						JFrame m = new JFrame();
						m.setBounds(100, 100, 600, 150);
						PanelErreur p = new PanelErreur(m);
						m.setContentPane(p);
						m.setVisible(true);
					}
				}}  );
	}

}
