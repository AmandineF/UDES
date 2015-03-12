
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelTabbed extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelTabbed(String pseudo, String mdp) {
		
		// Interface generale qui contient les onglets de consultation et d'édition des étudiants
		
		JTabbedPane onglet = new JTabbedPane();
		onglet.addTab("Consulter", null,  new PanelConsultationEtudiant(pseudo, mdp));
		onglet.addTab("Editer", null, new PanelModificationEtudiant());
		onglet.setBounds(100, 100, 1200, 500);
		
	    this.add(onglet);
	}
}
