import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
 * Gestion du panel photo
 * Affiche les albums photos de l'utilisateur
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelPhoto extends JPanel {

	/**
	 * Constructeur du panel photo
	 * @param fen La fenetre d'origine
	 */
	public PanelPhoto(final JFrame fen) {
		setLayout(new MigLayout("insets 0", "", ""));
		JPanel sp = new JPanel();
		sp.setLayout(new MigLayout("insets 0, gapx 0", "[180][180]", "9[120][15][120][15][120][15]"));
		Font ft = new Font("Mockup",Font.PLAIN,12);
		
		JLabel montreal = new JLabel(new ImageIcon("./Images/montreal.png"));
		sp.add(montreal, "cell 0 0, alignx center, aligny center");
		montreal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen,"Album photo", null);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	        } 
            public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
	  		public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel montrealTxt = new JLabel("Montreal");
		montrealTxt.setFont(ft);
		sp.add(montrealTxt, "cell 0 1, alignx center, aligny top");
		
		JLabel newyork = new JLabel(new ImageIcon("./Images/newyork.png"));
		sp.add(newyork, "cell 1 0, alignx center, aligny center");
		newyork.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen,"Album photo", null);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	        } 
            public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
	  		public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel newyorkTxt = new JLabel("New York");
		newyorkTxt.setFont(ft);
		sp.add(newyorkTxt, "cell 1 1, alignx center, aligny top");
		
		JLabel quebec = new JLabel(new ImageIcon("./Images/quebec.png"));
		sp.add(quebec, "cell 0 2, alignx center, aligny center");
		quebec.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen,"Album photo", null);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	        } 
            public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
	  		public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel quebecTxt = new JLabel("Quebec");
		quebecTxt.setFont(ft);
		sp.add(quebecTxt, "cell 0 3, alignx center, aligny top");
		
		JLabel boston = new JLabel(new ImageIcon("./Images/boston.png"));
		sp.add(boston, "cell 1 2, alignx center, aligny center");
		boston.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen,"Album photo", null);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	        } 
            public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
	  		public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel bostonTxt = new JLabel("Boston");
		bostonTxt.setFont(ft);
		sp.add(bostonTxt, "cell 1 3, alignx center, aligny top");
		
		JLabel toronto = new JLabel(new ImageIcon("./Images/toronto.png"));
		sp.add(toronto, "cell 0 4, alignx center, aligny center");
		toronto.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen,"Album photo", null);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	        } 
            public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
	  		public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel torontoTxt = new JLabel("Toronto");
		torontoTxt.setFont(ft);
		sp.add(torontoTxt, "cell 0 5, alignx center, aligny top");
		
		JLabel ottawa = new JLabel(new ImageIcon("./Images/ottawa.png"));
		sp.add(ottawa, "cell 1 4, alignx center, aligny center");
		ottawa.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen,"Album photo", null);						
	        	  fen.setContentPane(m);
	        	  fen.validate();
	        } 
            public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
	  		public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel ottawaTxt = new JLabel("Ottawa");
		ottawaTxt.setFont(ft);
		sp.add(ottawaTxt, "cell 1 5, alignx center, aligny top");
		
		JScrollPane contentScrollPane = new JScrollPane(sp);
		contentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScrollPane.setPreferredSize(new Dimension (440, 405));
		this.add(contentScrollPane, "cell 0 0,alignx center, aligny center");
		
	}

}
