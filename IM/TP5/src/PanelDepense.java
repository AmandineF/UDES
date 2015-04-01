import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * Implementation du panel de gestion des depenses 
 * @author Amandine Fouillet - Frank Chassing
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
