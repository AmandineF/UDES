import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel calendrier
 * @author Amandine
 *
 */
@SuppressWarnings("serial")
public class PanelCalendrier extends JPanel {

	/**
	 * Constructeur du panel calendrier
	 */
	public PanelCalendrier() {
		setLayout(new MigLayout("", "[100%]", "[20%][80%]"));
		
		//Deuxieme ligne affichage du calendrier
		final JLabel imgDefault = new JLabel(new ImageIcon("./Images/calendrier.png"));
		
		
		//Premiere ligne : boutons choix de presentation
		JPanel ligneBtn = new JPanel();
		ligneBtn.setLayout(new MigLayout("", "[20%][18%]3[18%]3[18%][20%]", "[100%]"));
		final JLabel btnSemaine = new JLabel(new ImageIcon("./Images/btnSemaineCal.png"));
		ligneBtn.add(btnSemaine, "cell 1 0, alignx center, aligny center");
		final JLabel btnMois = new JLabel(new ImageIcon("./Images/btnMoisCal.png"));
		ligneBtn.add(btnMois, "cell 2 0, alignx center, aligny center");
		final JLabel btnAnnee = new JLabel(new ImageIcon("./Images/btnAnneeCal.png"));
		ligneBtn.add(btnAnnee, "cell 3 0, alignx center, aligny center");
		
		//Implementation des evenements lors du changement de vue
		btnSemaine.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 btnSemaine.setIcon(new ImageIcon("./Images/btnSemaineCal2.png"));
	        	 btnAnnee.setIcon(new ImageIcon("./Images/btnAnneeCal.png"));
	        	 btnMois.setIcon(new ImageIcon("./Images/btnMoisCal2.png"));
	        	 imgDefault.setIcon(new ImageIcon("./Images/calendrierSemaine.png"));
	         } 
		     public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
		     }
		  	 public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
			 } 
		});
		btnMois.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 btnSemaine.setIcon(new ImageIcon("./Images/btnSemaineCal.png"));
	        	 btnAnnee.setIcon(new ImageIcon("./Images/btnAnneeCal.png"));
	        	 btnMois.setIcon(new ImageIcon("./Images/btnMoisCal.png"));
	        	 imgDefault.setIcon(new ImageIcon("./Images/calendrier.png"));
	         } 
		     public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
		     }
		  	 public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
			 } 
		});
		btnAnnee.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 btnSemaine.setIcon(new ImageIcon("./Images/btnSemaineCal.png"));
	        	 btnAnnee.setIcon(new ImageIcon("./Images/btnAnneeCal2.png"));
	        	 btnMois.setIcon(new ImageIcon("./Images/btnMoisCal2.png"));
	        	 imgDefault.setIcon(new ImageIcon("./Images/calendrierAnnee.png"));
	         } 
		     public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
		     }
		  	 public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
			 } 
		});
		
		//Ajout des lignes boutons et de l'image centrale au layout
		this.add(ligneBtn, "cell 0 0,alignx center,aligny center");
		this.add(imgDefault, "cell 0 1,alignx center,aligny center");
	}

}
