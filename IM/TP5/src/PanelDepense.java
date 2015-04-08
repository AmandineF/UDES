import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel depense
 * Affichage des depenses de l'utilisateur  
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelDepense extends JPanel {

	/**
	 * Constructeur du panel depenses
	 **/
	public PanelDepense() {
		setLayout(new MigLayout("", "", ""));
		JLabel imgDefault = new JLabel(new ImageIcon("./Images/depenses.png"));
		this.add(imgDefault, "cell 0 0,alignx center,aligny center");
		
	}

}
