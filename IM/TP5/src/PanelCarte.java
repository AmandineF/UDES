import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel carte
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelCarte extends JPanel {

	/**
	 * Constructeur du panel carte
	 */
	public PanelCarte() {
		
		setLayout(new MigLayout("insets 0", "", ""));
		JLabel imgDefault = new JLabel(new ImageIcon("./Images/carte.png"));
		this.add(imgDefault, "cell 0 0,alignx left,aligny center");
		
	}

}
