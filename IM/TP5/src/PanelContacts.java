import java.util.LinkedList;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelContacts extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContacts() {
		MigLayout ml = new MigLayout("insets 0", "", "");
		setLayout(ml);
		LinkedList<Contact> listContact = UtilisateurManager.getInstance().getConnectedUser().getContact();
		int nbContact = listContact.size();
		for(int i = 0; i < nbContact; i++) {
			PanelContact pc = new PanelContact(listContact.get(i).getNom(), 
					listContact.get(i).getPrenom(),
					listContact.get(i).getNumero(),
					listContact.get(i).getImage());
			this.add(pc, "cell 0 " + i + ",alignx center, aligny center");		
		}
	}

}
