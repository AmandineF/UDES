import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel aide
 * Informations pour guider l'utilisateur dans l'application
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelAide extends JPanel {

	/**
	 * Constructeur du panel aide
	 * @param fen La fenetre d'origine
	 * @param msg Le message d'aide
	 */
	public PanelAide(final JFrame fen, String msg) {
		setLayout(new MigLayout("insets 0", "[100%]", "[10%][90%]"));
		Font f = new Font("Showcard Gothic", Font.PLAIN, 20);
		JLabel titre = new JLabel("Aide");
		titre.setFont(f);
		add(titre, "cell 0 1, alignx center, aligny center");
		String str = "Aide Default";
		if(msg.equals("awai"))
			str = "<html><center>Bienvenue sur notre application Awai !<br> Pour utiliser l'application vous devez vous inscrire.<br>"
					+ " Si vous n'etes pas inscrit, cliquez sur le bouton Inscription.<br> Sinon connectez-vous \u00E0 partir du bouton Connexion."
					+ "<br> Nous vous invitons \u00E0 venir voir qui nous sommes sur le bouton A propos.</center></html>";
		if(msg.equals("connexion"))
			str = "<html><center>Renseignez votre pseudo et votre mot de passe pour vous connectez.<br>"
					+ "En cas d'oubli, merci de consulter la section de parametres.</center></html>";
		if(msg.equals("connexionB"))
			str = "<html><center>Le pseudo ou mot de passe rentr\u00E9 n'est pas valide.</center></html>";
		if(msg.equals("inscription"))
			str = "<html><center>Renseignez les champs pseudo, mot de passe et mail pour vous inscrire.<br>"
					+ "Ces champs vous seront redemander lors de la connexion \u00E0 l'application.</center></html>";
		if(msg.equals("menu"))
			str = "<html><center>Le menu repr\u00E9sente les diff\u00E9rentes fonctionnalit\u00E9s de notre application.<br>"
					+ "Chaque bulle correspond a une fonctionnalit\u00E9s.<br>"
					+ "La premiere correspond \u00E0 la communication et permet de commencer une communication avec un<br>"
					+ "contact de son choix.<br>"
					+ "La 2e permet de cr\u00E9er des albums photos et des les partager avec ses contacts.<br>"
					+ "La 3e permet de g\u00E9rer son emploi du temps et de le partager avec ses contacts.<br>"
					+ "La 4e permet de g\u00E9rer ses depenses et de les partager avec ses contacts.<br>"
					+ "La 5e permet d'informer ses contacts des destinations visit\u00E9es.<br>"
					+ "La 6e permet d'obtenir la liste de ses contacts ainsi que leurs informations.</center></html>";
		if(msg.equals("Communication"))
			str = "<html><center>Vous pouvez naviguer entre les diff\u00E9rents types de communications.<br>"
					+ "Vous pouvez \u00E9crire \u00E0 un contact, l'appeler ou bien effectuer une conversation vid\u00E9o. </center></html>";
		if(msg.equals("Photos"))
			str = "<html><center>Aide Photos.</center></html>";
		if(msg.equals("Calendrier"))
			str = "<html><center>Aide Calendrier.</center></html>";
		if(msg.equals("D\u00E9penses"))
			str = "<html><center>Aide D\u00E9penses.</center></html>";
		if(msg.equals("Carte"))
			str = "<html><center>Aide Carte.</center></html>";
		if(msg.equals("Contacts"))
			str = "<html><center>Vous pouvez consulter les informations d'un contact en cliquant dessus.<br>"
					+ "Vous pouvez sp\u00E9cialiser les informations que vous voulez voir en cliquant sur des bulles.</center></html>";
		if(msg.equals("erreurmail"))
			str = "<html><center>Votre mail ne respecte pas la forme xxx@xxx.xxx ! <br>"
					+ "Merci de bien vouloir recommencer.</center></html>";
		if(msg.equals("erreurpseudo"))
			str = "<html><center>Votre pseudo est vide. Merci de le renseigner !</center></html>";
		if(msg.equals("erreurmdp"))
			str = "<html><center>Votre mot de passe est vide. Merci de le renseigner !</center></html>";
		if(msg.equals("inscrit"))
			str = "<html><center>Vous etes bien inscrit ! Veuillez maintenant vous connecter.</center></html>";
		
		JLabel text = new JLabel(str);
		add(text, "cell 0 2,alignx center,aligny top");    

	}

}
