import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel connexion
 * Permet de gerer l'identification d'un utilisateur
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelConnexion extends JPanel {
	
	/**
	 * Constructeur du panel connexion
	 * @param fen La fenetre d'origine
	 */
	public PanelConnexion(final JFrame fen) {
		setLayout(new MigLayout("", "[20%]0[60%]0[20%]", "[15%]0[20%]0[20%]2[20%]0[20%]"));
		setBounds(0,0,460,500);
	    
		/**
		 * Classe permettant d'arrondir et de colorer un JTextField
		 */
		class CustomeBorder extends AbstractBorder{
	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y,
	                int width, int height) {
	            // TODO Auto-generated method stubs
	            super.paintBorder(c, g, x, y, width, height);
	            Graphics2D g2d = (Graphics2D)g;
	            g2d.setStroke(new BasicStroke(12));
	            Color col = new Color(0xECECEC);
	            g2d.setColor(col);
	            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
	        }   
	    }
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		this.add(retour, "cell 0 0 3 1,alignx left,aligny top");
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelAwai m = new PanelAwai(fen);						
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
		
		final JTextField pseudoField = new JTextField(30);
		TextPrompt tp = new TextPrompt("Pseudo", pseudoField);
		pseudoField.setPreferredSize(new Dimension(200, 60));
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		pseudoField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(pseudoField, "cell 1 1,alignx center,aligny center");
		
		final JPasswordField passField = new JPasswordField(30);
		passField.setPreferredSize(new Dimension(200, 60));
		TextPrompt tp2 = new TextPrompt("Mot de Passe", passField);
		tp2.setForeground( Color.GRAY );
		tp2.changeStyle(Font.ITALIC);
		tp2.setShow(TextPrompt.Show.ALWAYS);
		passField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(passField, "cell 1 2,alignx center,aligny top");
		
		JLabel btnConnexion = new JLabel( new ImageIcon( "./images/connexion.png"));
		this.add(btnConnexion, "cell 1 3,alignx center,aligny top");
		btnConnexion.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					String pseudoRes = pseudoField.getText();
					String mdpRes = String.valueOf(passField.getPassword());
					if(UtilisateurManager.getInstance().connexion(pseudoRes, mdpRes)){
						PanelMenu m = new PanelMenu(fen);						
						fen.setContentPane(m);
						fen.validate();
					}else{
						JFrame m = new JFrame();
						m.setBounds(100, 100, 500, 180);
						m.setResizable(false);
						m.setTitle("Erreur");
						PanelAide p = new PanelAide(fen, "connexionB");
						m.setContentPane(p);
						m.setVisible(true);
					}
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
				}  );
		
		JPanel paramAide = new JPanel(new MigLayout("", "[20][20][360]", "[100%]"));
		this.add(paramAide, "cell 0 4 3 1,alignx left,aligny center");
		
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		paramAide.add(parametre,"cell 0 0, alignx center, aligny bottom");
		parametre.setToolTipText("Param\u00E8tres");
		parametre.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 PanelParametres m = new PanelParametres(fen, "de la connexion",null);	
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
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		aide.setToolTipText("Aide");
		paramAide.add(aide,"cell 1 0, alignx center, aligny bottom");
		aide.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					JFrame f = new JFrame();
					PanelAide m = new PanelAide(f, "connexion");	
					f.setContentPane(m);
					f.setBounds(100, 100, 500, 180);
					f.setVisible(true);
			
			}
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
		}
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getDefaultCursor());
		}
		}  );
	}

}
