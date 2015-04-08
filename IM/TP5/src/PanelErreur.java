import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestion du panel erreur
 * Fenetre qui permet d'afficher une erreur lors de la connexion
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelErreur extends JPanel {

	/**
	 * Constructeur du panel
	 * @param fen La fenetre d'origine
	 */
	public PanelErreur(final JFrame fen) {
		//setLayout(new MigLayout("", "[30px][5px][324px]", "[30px][30px]"));
		
		JLabel text = new JLabel("Le nom d'utilisateur ou le mot de passe est invalide");
		add(text, "cell 2 0,alignx center,aligny center");
		
		JLabel image = new JLabel(new ImageIcon("cancel.png"));
		image.setSize(40,40);
		add(image, "cell 0 0,alignx center,aligny center");
		
		JButton btnOk = new JButton("OK");
		add(btnOk, "cell 2 1,alignx center,aligny center");
		
		btnOk.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					fen.dispose();
				}}  );   
	}
}
