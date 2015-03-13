import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelErreur extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelErreur(final JFrame fen) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{202, 46, 0};
		gridBagLayout.rowHeights = new int[]{14, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		JLabel text = new JLabel("Le nom d'utilisateur ou le mot de passe est invalide");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(4,10,4,10);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(text, gbc_lblNewLabel);
		
		
		JLabel image = new JLabel(new ImageIcon("cancel.png"));
		image.setSize(40,40);
		gbc_lblNewLabel.insets = new Insets(4,4,4,4);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(image, gbc_lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(btnOk,gbc_lblNewLabel);
		
		btnOk.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					fen.dispose();
				}}  );   
		
	}

}
