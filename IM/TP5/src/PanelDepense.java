import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel depense
 * Affichage des depenses de l'utilisateur  
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelDepense extends JPanel {

	/**
	 * Constructeur du panel depense
	 **/
	public PanelDepense() {
		setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		sp.setLayout(new MigLayout("insets 9", "", ""));
		JLabel imgDefault = new JLabel(new ImageIcon("./Images/depenses.png"));
		sp.add(imgDefault, "cell 0 0,alignx left,aligny center");
		JScrollPane contentScrollPane = new JScrollPane(sp);
		contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScrollPane.setPreferredSize(new Dimension (440, 405));
		this.add(contentScrollPane, "cell 0 0,alignx center, aligny center");
		
	}

}
