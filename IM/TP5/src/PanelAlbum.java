import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel album
 * Simulation d'un album photo
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelAlbum extends JPanel {

	/**
	 * Constructeur du panel album 
	 * @param fen La fenêtre d'ou l'on vient
	 */
	public PanelAlbum(final JFrame fen) {
		setLayout(new MigLayout("insets 2", "", ""));
		JPanel sp = new JPanel();
		sp.setLayout(new MigLayout("insets 2", "", ""));
		
		for(int i = 0; i< 4;i++) {
			for(int j = 0; j<10; j++) {
				JLabel img = new JLabel(new ImageIcon("./Images/photoDef.jpg"));
				img.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				sp.add(img, "cell "+i + " "+j+",alignx center, aligny center");
				img.setToolTipText("Voir la photo");
				img.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent me) { 
			        } 
		            public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
			  		public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
					}
				});
			}
		}
		
		JScrollPane contentScrollPane = new JScrollPane(sp);
		contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScrollPane.setPreferredSize(new Dimension (440, 405));
		this.add(contentScrollPane, "cell 0 0,alignx center, aligny center");
		
	}

}
