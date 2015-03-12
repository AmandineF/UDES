
import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelTabbed extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelTabbed(JFrame fen, String pseudo, String mdp) {
		
		// Interface generale qui contient les onglets de consultation et d'édition des étudiants
		
		JTabbedPane onglet = new JTabbedPane();
		PanelConsultationEtudiant consult = new PanelConsultationEtudiant(pseudo, mdp);
		consult.setPreferredSize(new Dimension(480, 580));
		onglet.addTab("Consulter", consult);
		onglet.setEnabledAt(0, true);
		PanelModificationEtudiant edit = new PanelModificationEtudiant(fen, pseudo, mdp);
		edit.setPreferredSize(new Dimension(480, 580));
		onglet.addTab("Editer", edit);
		onglet.setPreferredSize(new Dimension(480, 580));
	    this.add(onglet);
	}
}
