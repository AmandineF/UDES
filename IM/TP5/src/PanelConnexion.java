/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelConnexion extends JPanel {
	/**
	 * Create the panel.
	 */
	public PanelConnexion(final JFrame fen) {
		setLayout(new MigLayout("", "[31.00px][37.00px][343.00px,grow]", "[59.00px][60.00px][69.00px][83.00px][59.00px]"));
		Font font = new Font("Showcard Gothic", Font.BOLD, 40);
		
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
		
		final JTextField pseudoField = new JTextField();
		TextPrompt tp = new TextPrompt("Pseudo", pseudoField);
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		this.add(retour, "cell 0 0,alignx center,aligny center");
		
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelAwai m = new PanelAwai(fen);						
					fen.setContentPane(m);
					fen.setBounds(100, 100, 500, 500);
					fen.validate();
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
				}  );      
		
		JLabel connexion = new JLabel("Connexion");	
		connexion.setFont(font);
		pseudoField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(pseudoField, "cell 2 1,alignx center,aligny center");
		

		
		final JPasswordField passField = new JPasswordField();
		TextPrompt tp2 = new TextPrompt("Mot de Passe", passField);
		tp2.setForeground( Color.GRAY );
		tp2.changeStyle(Font.ITALIC);
		tp2.setShow(TextPrompt.Show.ALWAYS);
		passField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(passField, "cell 2 2,alignx center,aligny center");
		
		JLabel btnConnexion = new JLabel( new ImageIcon( "./images/connexion.png"));
		this.add(btnConnexion, "cell 2 3,alignx center,aligny center");
		
		btnConnexion.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
					String pseudoRes = pseudoField.getText();
					String mdpRes = String.valueOf(passField.getPassword());
					if(UtilisateurManager.getInstance().connexion(pseudoRes, mdpRes)){
						PanelMenu m = new PanelMenu(fen);						
						fen.setContentPane(m);
						fen.setBounds(100, 100, 500, 500);
						fen.validate();
					}else{
						JFrame m = new JFrame();
						m.setResizable(false);
						m.setTitle("Erreur");
						m.setBounds(100, 100, 400, 120);
						PanelErreur p = new PanelErreur(m);
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
		
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		this.add(parametre, "cell 0 4,alignx center,aligny center");
		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		this.add(aide, "cell 1 4,alignx trailing,aligny center");
		
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
