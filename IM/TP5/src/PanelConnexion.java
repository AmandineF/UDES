/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;


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
		Font font = new Font("Arial", Font.BOLD, 40);
		connexion.setFont(font);
		this.add(connexion, c);
		
	    class CustomeBorder extends AbstractBorder{
	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y,
	                int width, int height) {
	            // TODO Auto-generated method stubs
	            super.paintBorder(c, g, x, y, width, height);
	            Graphics2D g2d = (Graphics2D)g;
	            g2d.setStroke(new BasicStroke(12));
	            Color col = new Color(0xECECEC);
	            g2d.setColor(col);
	            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
	        }   
	    }
		
		final JTextField pseudoField = new JTextField();
		TextPrompt tp = new TextPrompt("Pseudo", pseudoField);
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		pseudoField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		this.add(pseudoField, c);
		

		
		final JPasswordField passField = new JPasswordField();
		TextPrompt tp2 = new TextPrompt("Mot de Passe", passField);
		tp2.setForeground( Color.GRAY );
		tp2.changeStyle(Font.ITALIC);
		tp2.setShow(TextPrompt.Show.ALWAYS);
		passField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		this.add(passField, c);
		
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
					if(UtilisateurManager.getInstance().connexion(pseudoRes, mdpRes)){
						PanelMenu m = new PanelMenu();						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					}else{
						JFrame m = new JFrame();
						m.setResizable(false);
						m.setTitle("Erreur");
						m.setBounds(100, 100, 400, 120);
						PanelErreur p = new PanelErreur(m);
						m.setContentPane(p);
						m.setVisible(true);
					}
				}}  );
	}

}
