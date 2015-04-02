import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


public class PanelPhoto extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelPhoto() {
		
		setLayout(new MigLayout("insets 0", "", ""));
		JLabel imgDefault = new JLabel(new ImageIcon("./Images/photos.png"));
		this.add(imgDefault, "cell 0 0,alignx center,aligny center");
		
	}

}
